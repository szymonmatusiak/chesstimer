package com.example.szymon.chesstimer.settings;

import com.example.szymon.chesstimer.main.MainView;

/**
 * Created by Szymon on 19.07.2017.
 */

public interface SettingsPresenter {
    void openMainActivity();
    void onStart(final SettingsView settingsView);
    void onStop();
}
