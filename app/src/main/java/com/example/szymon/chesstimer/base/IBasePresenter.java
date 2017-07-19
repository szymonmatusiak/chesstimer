package com.example.szymon.chesstimer.base;

public interface IBasePresenter<V extends IBaseView> {

    /**
     * Attach view.
     *
     * @param view the view
     */
    void attachView(V view);

    /**
     * Detach view.
     *
     * @param retainInstance the retain instance
     */
    void detachView(boolean retainInstance);

}