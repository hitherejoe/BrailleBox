package co.joebirch.braillebox.ui.main;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Callable;

import co.joebirch.braillebox.data.DataManager;
import co.joebirch.braillebox.util.TestDataFactory;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private MainPresenter mainPresenter;
    @Mock DataManager dataManager;
    @Mock MainMvpView mockMainMvpView;

    @Before
    public void setup() {
        mainPresenter = new MainPresenter(dataManager);
        mainPresenter.attachView(mockMainMvpView);
    }

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                new Function<Callable<Scheduler>, Scheduler>() {
                    @Override
                    public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                        return Schedulers.trampoline();
                    }
                });
    }

    @Test
    public void getArticleSucceedsAndReturnsSequence() {
        String sequence = TestDataFactory.randomUuid();
        stubDataManagerGetArticle(Observable.just(sequence));

        mainPresenter.fetchArticle(TestDataFactory.randomInt());

        verify(mockMainMvpView).showSequence(sequence);
    }

    @Test
    public void getArticleFails() {
        stubDataManagerGetArticle(Observable.<String>error(new RuntimeException()));

        mainPresenter.fetchArticle(TestDataFactory.randomInt());

        verify(mockMainMvpView, never()).showSequence(anyString());
    }

    private void stubDataManagerGetArticle(Observable<String> sequence) {
        when(dataManager.getArticle(anyInt()))
                .thenReturn(sequence);
    }

}
