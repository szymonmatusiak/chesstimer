package com.example.szymon.chesstimer.main;


import com.example.szymon.chesstimer.model.TimerValues;

/**
 * Created by Szymon on 19.07.2017.
 */

public interface MainPresenter {
    void openSettingsActivity();

    void onStart(final MainView mainView);

    void onStop();


    void onTimerButtonClicked(int button);

    void setTimer(TimerValues timerValues);

    void stopRunningTimer();
}
