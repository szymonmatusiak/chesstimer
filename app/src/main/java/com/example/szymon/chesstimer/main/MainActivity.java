package com.example.szymon.chesstimer.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.szymon.chesstimer.R;
import com.example.szymon.chesstimer.model.TimerValues;
import com.example.szymon.chesstimer.settings.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.szymon.chesstimer.main.MainPresenterImpl.Button;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final String TIMER_KEY = "timer_key";
    public static final String DELAY = "delay";

    private static final int SETTINGS_REQUEST_CODE = 123;

    @BindView(R.id.top_button)
    android.widget.Button playerTop;
    @BindView(R.id.bottom_button)
    android.widget.Button playerBottom;
    @BindView(R.id.pause)
    ImageView pause;
    @BindView(R.id.settings)
    ImageView settings;

    private MainPresenter mainPresenter;

    public static Intent getIntent(final Context context, final TimerValues timerValues) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TIMER_KEY, timerValues);
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenterImpl();
        mainPresenter.setTimer(new TimerValues());

    }

    @Override
    protected void onStart() {
        super.onStart();
        setLayoutToFullscreen();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mainPresenter.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.onStop();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK) {
            final Bundle bundle = data.getExtras();
            if (bundle != null) {
                TimerValues timerValues = bundle.getParcelable(TIMER_KEY);
                toast(timerValues.toString());
                updateValuesOnButtons(timerValues);
                mainPresenter.setTimer(timerValues);
            }
            setButtonsToActive();
        }
    }

    private void setButtonsToActive() {
        playerBottom.setClickable(true);
        playerTop.setClickable(true);
    }

    public void updateValuesOnButton(final String text, final int buttonID) {
        if (buttonID == Button.TOP)
            playerTop.setText(text);
        else
            playerBottom.setText(text);
    }

    public void updateValuesOnButtons(final TimerValues timerValues) {
        playerTop.setText(Integer.toString(timerValues.getFirstPlayerTime()) + ":00");
        playerBottom.setText(Integer.toString(timerValues.getSecondPlayerTime()) + ":00");
    }

    public void toast(final String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.settings)
    public void openSettingsActivity() {
        mainPresenter.openSettingsActivity();
    }

    @OnClick({R.id.top_button, R.id.bottom_button})
    public void onTimerButtonClicked(final View view) {
        switch (view.getId()) {
            case R.id.bottom_button:
                mainPresenter.onTimerButtonClicked(Button.BOTTOM);
                switchActiveButton(Button.BOTTOM);
                break;

            case R.id.top_button:
                mainPresenter.onTimerButtonClicked(Button.TOP);
                switchActiveButton(Button.TOP);
                break;
        }
    }

    private void switchActiveButton(final int button) {
        if (button == Button.BOTTOM) {
            playerBottom.setClickable(false);
            playerTop.setClickable(true);
        } else {
            playerBottom.setClickable(true);
            playerTop.setClickable(false);
        }
    }

    @Override
    public void openSettings() {
        startActivityForResult(SettingsActivity.getIntent(getBaseContext()), SETTINGS_REQUEST_CODE);
    }

    private void setLayoutToFullscreen() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void setTimeToFirst() {
    }

    @Override
    public void setTimeToSecond() {
    }

    @Override
    public void reset() {
    }

}
