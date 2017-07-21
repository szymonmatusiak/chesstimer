package com.example.szymon.chesstimer.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.szymon.chesstimer.R;
import com.example.szymon.chesstimer.model.TimerValues;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.szymon.chesstimer.main.MainActivity.TIMER_KEY;

public class SettingsActivity extends AppCompatActivity implements SettingsView{
    @BindView(R.id.time_player_one)
    EditText timePlayerOne;
    @BindView(R.id.time_player_two)
    EditText timePlayerTwo;
    @BindView(R.id.time_addon)
    EditText addon;
    @BindView(R.id.start)
    Button button;

    private TimerValues timer;
    private SettingsPresenter settingsPresenter;

    public static Intent getIntent(final Context context) {
        return new Intent(context, SettingsActivity.class); 
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        timer = new TimerValues();
        settingsPresenter = new SettingsPresenterImpl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        settingsPresenter.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        settingsPresenter.onStop();
    }
    public void setTimer() {
        if (!TextUtils.isEmpty(timePlayerOne.getText()) && !TextUtils.isEmpty(timePlayerTwo.getText()) && !TextUtils.isEmpty(addon.getText())) {
            timer.setTimes(
                    Integer.valueOf(timePlayerOne.getText().toString()),
                    Integer.valueOf(timePlayerTwo.getText().toString()),
                    Integer.valueOf(addon.getText().toString()));
        }
    }

    @OnClick(R.id.start)
    public void openMainActivity() {
        settingsPresenter.openMainActivity();
    }

    private void toast(String text) {
        Toast.makeText(this, text, LENGTH_SHORT).show();
    }

    public void openMain() {
        setTimer();
        Intent intent = new Intent();
        intent.putExtra(TIMER_KEY, timer);
        setResult(RESULT_OK, intent);
        finish();
    }
}
