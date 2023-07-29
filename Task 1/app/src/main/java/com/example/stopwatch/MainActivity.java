package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int secs = 0;

    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.timerText);
        Time();
    }

    public void onClickStart(View view) {
        isRunning = true;

    }

    public void onClickHold(View view) {
 isRunning = false;
    }

    public void onClickStop(View view) {
isRunning = false;
secs = 0;
    }

    public void Time() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {

        @Override
        public void run () {
            int s = secs % 60;
            int min = secs / 60;
            int h = min / 60;
            if (isRunning) {
                secs++;
            }
            String formatString = String.format(Locale.getDefault(), "%02d:%02d:%02d", h, min, s);

            textView.setText(formatString);
            handler.postDelayed(this, 1000);

        }
    };
    handler.post(runnable);

}}