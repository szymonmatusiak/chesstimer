package com.example.szymon.chesstimer.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.szymon.chesstimer.R;
import com.example.szymon.chesstimer.TimerValues;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    @BindView(R.id.time_player_one) EditText timePlayerOne;
    @BindView(R.id.time_player_two) EditText timePlayerTwo;
    @BindView(R.id.time_addon) EditText addon;
    private TimerValues timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void setTime(){
        timer = new TimerValues(
                Integer.valueOf(timePlayerOne.getText().toString()),
                Integer.valueOf(timePlayerTwo.getText().toString()),
                Integer.valueOf(addon.getText().toString()));
    }
    @OnClick(R.id.start)
    public void openMainActivity(){
        setTime();
        MainActivity.getIntent(getBaseContext(),timer);
        startActivity(MainActivity.getIntent(getBaseContext(),timer));


    }

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        return intent;
    }
}
