package com.example.szymon.chesstimer.main;

import com.example.szymon.chesstimer.base.BasePresenter;

/**
 * Created by Szymon on 19.07.2017.
 */

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    @Override
    public void openSettingsActivity() {
        getView().openSettings();
    }

    @Override
    public void onStart(MainView mainView) {
        attachView(mainView);
    }

    @Override
    public void onStop() {
        detachView(false);
    }
}
