package com.example.szymon.chesstimer.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    private WeakReference<V> viewReference;

    @Override
    public void attachView(V view) {
        viewReference = new WeakReference<>(view);
    }

    /**
     * Gets view.
     *
     * @return the view
     */
    protected V getView() {
        return viewReference.get();
    }

    /**
     * Is view attached boolean.
     *
     * @return the boolean
     */
    protected boolean isViewAttached() {
        return viewReference != null && viewReference.get() != null;
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }
}