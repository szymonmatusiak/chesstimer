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

    private TimerValues timerValues;
    private CountDownTimer countDownTimer;
    private Handler handler;
    private Runnable runnable;
    private int moves = 0;
    private int[] num = new int[2];
    private int lastClickedButton;

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
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if(handler != null)
            handler.removeCallbacks(runnable);
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
                handler.postDelayed(runnable = new Runnable() {
                    @Override
                    public void run() {
                        timerWithSimpleDelay(button);
                    }
                }, timerValues.getAddon());
                break;

            case SettingsActivity.Delay.FISCHER_DELAY:
                timerWithFischerDelay(button);
                break;

            case SettingsActivity.Delay.BRONSTEIN_DELAY:
                break;
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
            if (moves >= 3) {
                timerValues.setFirstPlayerTime(timerValues.getFirstPlayerTime() + timerValues.getAddon());
                getView().updateValuesOnButton(getTimeToShow(timerValues.getFirstPlayerTime()), button);
            }
            startTimer(timerValues.getFirstPlayerTime(), TOP);
        } else {
            if (moves >= 3) {
                timerValues.setSecondPlayerTime(timerValues.getSecondPlayerTime() + timerValues.getAddon());
                getView().updateValuesOnButton(getTimeToShow(timerValues.getSecondPlayerTime()), button);
            }
            startTimer(timerValues.getSecondPlayerTime(), BOTTOM);
        }
    }

    private void startTimer(final int time, @Button final int button) {

        countDownTimer = new CountDownTimer(time, 1000) {

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
        return minutes + ":" + seconds;
    }

    @Override
    public void setTimer(final TimerValues timerValues) {
        this.timerValues = timerValues;
        convertTimeToMinutes(timerValues);
        convertAddonToSeconds(timerValues);

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
