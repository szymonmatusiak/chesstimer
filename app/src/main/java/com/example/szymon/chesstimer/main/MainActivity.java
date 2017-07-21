package com.example.szymon.chesstimer.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.szymon.chesstimer.R;
import com.example.szymon.chesstimer.model.TimerValues;
import com.example.szymon.chesstimer.settings.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final String TIMER_KEY = "timer_key";
    private static final int SETTINGS_REQUEST_CODE = 123;

    private MainPresenter  mainPresenter;
    private TimerValues timerValues;

    @BindView(R.id.top_button)
    Button playerTop;
    @BindView(R.id.bottom_button)
    Button playerBottom;

    @BindView(R.id.pause)
    ImageView pause;
    @BindView(R.id.settings)
    ImageView settings;

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
        setLayoutToFullscreen();
        mainPresenter = new MainPresenterImpl();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
            Bundle bundle = data.getExtras();
            timerValues = (TimerValues) bundle.getParcelable(TIMER_KEY);
            updateValuesOnButtons();
        }
    }

    private void updateValuesOnButtons() {
        playerTop.setText(Integer.toString(timerValues.getFirstPlayerTime()) + ":00");
        playerBottom.setText(Integer.toString(timerValues.getSecondPlayerTime()) + ":00");
    }

    private void toast(final String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.settings)
    public void openSettingsActivity() {
        mainPresenter.openSettingsActivity();
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
