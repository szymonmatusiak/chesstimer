package com.example.szymon.chesstimer.main;

/**
 * Created by Szymon on 19.07.2017.
 */

public interface MainPresenter {
    void openSettingsActivity();
    void onStart(final MainView mainView);
    void onStop();
}
