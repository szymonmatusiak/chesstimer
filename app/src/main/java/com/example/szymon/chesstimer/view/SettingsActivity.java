package com.example.szymon.chesstimer.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.szymon.chesstimer.R;
import com.example.szymon.chesstimer.TimerValues;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.szymon.chesstimer.view.MainActivity.TIMER_KEY;

public class SettingsActivity extends AppCompatActivity {
    @BindView(R.id.time_player_one)
    EditText timePlayerOne;
    @BindView(R.id.time_player_two)
    EditText timePlayerTwo;
    @BindView(R.id.time_addon)
    EditText addon;
    @BindView(R.id.start)
    Button button;

    private TimerValues timer;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        timer = new TimerValues();
    }

    public void setTime() {
        if (!TextUtils.isEmpty(timePlayerOne.getText()) && !TextUtils.isEmpty(timePlayerTwo.getText()) && !TextUtils.isEmpty(addon.getText())) {
            toast("if");
            timer.setTimes(
                    Integer.valueOf(timePlayerOne.getText().toString()),
                    Integer.valueOf(timePlayerTwo.getText().toString()),
                    Integer.valueOf(addon.getText().toString()));
        }
    }

    @OnClick(R.id.start)
    public void openMainActivity() {
        //toast();
        setTime();
        //toast();    //remove later
        Intent intent = new Intent();
        intent.putExtra(TIMER_KEY, timer);
        setResult(RESULT_OK, intent);
        finish();

    }

    private void toast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void toast() {
        Context context = getApplicationContext();
        CharSequence text = timer.toString();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
