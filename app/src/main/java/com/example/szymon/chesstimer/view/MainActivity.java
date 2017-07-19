package com.example.szymon.chesstimer.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.szymon.chesstimer.R;
import com.example.szymon.chesstimer.TimerValues;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TIMER_KEY = "timer_key";
    @BindView(R.id.top_button) Button playerTop;
    @BindView(R.id.bottom_button) Button playerBottom;

    @BindView(R.id.pause) ImageView pause;
    @BindView(R.id.settings) ImageView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setLayoutToFullscreen();


    }

    public static Intent getIntent(final Context context, final TimerValues timerValues) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TIMER_KEY, timerValues);
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(bundle);
        String text = timerValues.toString();
        int duration = Toast.LENGTH_SHORT;

        //Toast(text, duration);
        return intent;
    }



    @OnClick(R.id.settings)
    public void openSettingsActivity(){
        startActivity(SettingsActivity.getIntent(getBaseContext()));

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
    public void reset(){
    }

}
