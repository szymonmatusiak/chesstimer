package com.example.szymon.chesstimer.main;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.example.szymon.chesstimer.base.BasePresenter;
import com.example.szymon.chesstimer.model.TimerValues;
import com.example.szymon.chesstimer.settings.SettingsActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.szymon.chesstimer.main.MainPresenterImpl.Button.BOTTOM;
import static com.example.szymon.chesstimer.main.MainPresenterImpl.Button.TOP;

/**
 * Created by Szymon on 19.07.2017.
 */

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    private static final int ONE_SECOND = 1000;
    private static final int NUMBER_OF_MOVES_BEFORE_ADDING_TIME = 3;
    private TimerValues timerValues;
    private CountDownTimer countDownTimer;
    private Handler handler;
    private Runnable runnable;
    private int lastMoveFirstPlayer;
    private int lastMoveSecondPlayer;
    private int moves = 0;

    @Override
    public void openSettingsActivity() {
        getView().openSettings();
    }

    @Override
    public void onStart(final MainView mainView) {
        attachView(mainView);
        handler = new Handler();
    }

    @Override
    public void onStop() {
        stopRunningTimer();
        detachView(false);
    }

    @Override
    public void onTimerButtonClicked(final int button) {
        moves++;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            handler.removeCallbacks(runnable);
        }
        switch (timerValues.getDelay()) {
            case SettingsActivity.Delay.SIMPLE_DELAY:
                fiveSecondsDelayBeforeBronstein(button);
                break;

            case SettingsActivity.Delay.FISCHER_DELAY:
                timerWithFischerDelay(button);
                break;

            case SettingsActivity.Delay.BRONSTEIN_DELAY:
                timerWithBronsteinDelay(button);
                break;
        }
    }

    private void fiveSecondsDelayBeforeBronstein(final int button) {
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                timerWithSimpleDelay(button);
            }
        }, timerValues.getAddon());
    }

    private void timerWithBronsteinDelay(int button) {
        if (button == TOP) {
            if (lastMoveFirstPlayer - timerValues.getFirstPlayerTime() > timerValues.getAddon()) {
                timerValues.setFirstPlayerTime(timerValues.getFirstPlayerTime() + timerValues.getAddon());
                getView().updateValuesOnButton(getTimeToShow(timerValues.getFirstPlayerTime()), button);
            } else {
                timerValues.setFirstPlayerTime(lastMoveFirstPlayer);
            }
            lastMoveFirstPlayer = timerValues.getFirstPlayerTime();
            startTimer(timerValues.getFirstPlayerTime(), TOP);
        } else {
            if (lastMoveSecondPlayer - timerValues.getSecondPlayerTime() > timerValues.getAddon()) {
                timerValues.setSecondPlayerTime(timerValues.getSecondPlayerTime() + timerValues.getAddon());
                getView().updateValuesOnButton(getTimeToShow(timerValues.getSecondPlayerTime()), button);
            } else {
                timerValues.setSecondPlayerTime(lastMoveSecondPlayer);
            }

            lastMoveSecondPlayer = timerValues.getSecondPlayerTime();
            startTimer(timerValues.getSecondPlayerTime(), BOTTOM);
        }
    }

    private void timerWithSimpleDelay(final int button) {
        if (button == TOP) {
            getView().updateValuesOnButton(getTimeToShow(timerValues.getFirstPlayerTime()), button);
            startTimer(timerValues.getFirstPlayerTime(), TOP);
        } else {
            getView().updateValuesOnButton(getTimeToShow(timerValues.getSecondPlayerTime()), button);
            startTimer(timerValues.getSecondPlayerTime(), BOTTOM);
        }
    }

    private void timerWithFischerDelay(final int button) {
        if (button == TOP) {
            if (moves >= NUMBER_OF_MOVES_BEFORE_ADDING_TIME) {
                timerValues.setFirstPlayerTime(timerValues.getFirstPlayerTime() + timerValues.getAddon());
                getView().updateValuesOnButton(getTimeToShow(timerValues.getFirstPlayerTime()), button);
            }
            startTimer(timerValues.getFirstPlayerTime(), TOP);
        } else {
            if (moves >= NUMBER_OF_MOVES_BEFORE_ADDING_TIME) {
                timerValues.setSecondPlayerTime(timerValues.getSecondPlayerTime() + timerValues.getAddon());
                getView().updateValuesOnButton(getTimeToShow(timerValues.getSecondPlayerTime()), button);
            }
            startTimer(timerValues.getSecondPlayerTime(), BOTTOM);
        }
    }

    private void startTimer(final int time, @Button final int button) {

        countDownTimer = new CountDownTimer(time, ONE_SECOND) {

            public void onTick(long millisUntilFinished) {
                if (button == TOP) {
                    timerValues.setFirstPlayerTime((int) millisUntilFinished);
                } else if (button == BOTTOM) {
                    timerValues.setSecondPlayerTime((int) millisUntilFinished);
                }
                if (getView() != null) {
                    getView().updateValuesOnButton(getTimeToShow(millisUntilFinished), button);
                }
            }

            public void onFinish() {
                if (getView() != null) {
                    getView().updateValuesOnButton("you lost", button);
                }
            }
        }.start();

    }

    @NonNull
    private String getTimeToShow(final long millisUntilFinished) {
        String minutes = "" + (millisUntilFinished / (1000 * 60));
        if (minutes.length() == 1) minutes = "0" + minutes;
        String seconds = "" + (millisUntilFinished / 1000) % 60;
        if (seconds.length() == 1) seconds = "0" + seconds;
        return String.format("%s %s %s", minutes, ":", seconds);

    }

    @Override
    public void setTimer(final TimerValues timerValues) {
        this.timerValues = timerValues;
        convertTimeToMinutes(timerValues);
        convertAddonToSeconds(timerValues);
        lastMoveFirstPlayer = timerValues.getFirstPlayerTime();
        lastMoveSecondPlayer = timerValues.getSecondPlayerTime();
        moves = 0;
    }

    @Override
    public void stopRunningTimer() {
        getView().setButtonsToActive();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (handler != null)
            handler.removeCallbacks(runnable);
    }

    private void convertAddonToSeconds(final TimerValues timerValues) {
        timerValues.setAddon(timerValues.getAddon() * 1000);
    }

    private void convertTimeToMinutes(final TimerValues timerValues) {
        timerValues.setFirstPlayerTime(timerValues.getFirstPlayerTime() * 1000 * 60);
        timerValues.setSecondPlayerTime(timerValues.getSecondPlayerTime() * 1000 * 60);
    }

    @IntDef({BOTTOM, TOP})
    @Retention(RetentionPolicy.SOURCE)
    @interface Button {
        int BOTTOM = 0;
        int TOP = 1;
    }
}
