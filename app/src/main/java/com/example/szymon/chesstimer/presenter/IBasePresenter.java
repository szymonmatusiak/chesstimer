package com.example.szymon.chesstimer.presenter;

import com.example.szymon.chesstimer.view.IBaseView;

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