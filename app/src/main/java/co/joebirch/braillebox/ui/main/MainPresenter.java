package co.joebirch.braillebox.ui.main;

import javax.inject.Inject;

import co.joebirch.braillebox.data.DataManager;
import co.joebirch.braillebox.ui.base.BasePresenter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainPresenter extends BasePresenter<MainMvpView> {

    @Inject DataManager dataManager;

    private Disposable articleDisposable;

    @Inject
    MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void detachView() {
        super.detachView();
        cancelCurrentSubscription();
    }

    void fetchArticle(int delay) {
        cancelCurrentSubscription();
        getMvpView().resetSolenoids();
        dataManager.getArticle(delay)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        articleDisposable = disposable;
                    }

                    @Override
                    public void onNext(String sequence) {
                        getMvpView().showSequence(sequence);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Timber.e(error, "There was a problem fetching the news article...");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void cancelCurrentSubscription() {
        if (articleDisposable != null) articleDisposable.dispose();
    }

}