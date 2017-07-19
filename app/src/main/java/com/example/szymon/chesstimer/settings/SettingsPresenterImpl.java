package com.example.szymon.chesstimer.settings;

import com.example.szymon.chesstimer.base.BasePresenter;

/**
 * Created by Szymon on 19.07.2017.
 */

public class SettingsPresenterImpl extends BasePresenter<SettingsView> implements SettingsPresenter {
    @Override
    public void openMainActivity() {
        getView().openMain();
    }

    @Override
    public void onStart(SettingsView settingsView) {
        attachView(settingsView);
    }

    @Override
    public void onStop() {
        detachView(false);
    }
}
