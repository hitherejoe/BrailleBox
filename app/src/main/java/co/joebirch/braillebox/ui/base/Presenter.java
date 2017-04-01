package co.joebirch.braillebox.ui.base;

/**
 * Interface class to act as a base for any class that is to take the role of the Presenter in the
 * Model-View-Presenter pattern.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}