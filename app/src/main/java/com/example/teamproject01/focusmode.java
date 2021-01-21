package com.example.teamproject01;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

public class focusmode extends AppCompatActivity {
    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    private Button mButtonSet;
    private Button mButtonStartPause;
    //private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;

    @Override
    // 뒤로가기 키 막기
    public void onBackPressed()
    {
        Toast.makeText(this, "집중하세요!", Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    // 메뉴 키 막기
    protected void onPause()
    {
        super.onPause();
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
        Toast.makeText(this, "집중하세요!", Toast.LENGTH_SHORT).show();
    }


    // 홈 키 막기
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        Intent intent1 = new Intent(getApplicationContext(), focusmode.class);
        startActivity(intent1);

        Toast.makeText(this, "집중하세요!", Toast.LENGTH_SHORT).show();
        return;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.focusmode);
        mEditTextInput = findViewById(R.id.edit_text_input);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonSet = findViewById(R.id.button_set);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditTextInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(focusmode.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(focusmode.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(millisInput);
                mEditTextInput.setText("");
            }
        });
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

    }
    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }
    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateWatchInterface();

                finish();
            }
        }.start();
        mTimerRunning = true;
        updateWatchInterface();
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }
    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }
    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        mTextViewCountDown.setText(timeLeftFormatted);
    }
    private void updateWatchInterface() {
        if (mTimerRunning) {
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        } else {
            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");
            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }
            if (mTimeLeftInMillis < mStartTimeInMillis) {
                //mButtonReset.setVisibility(View.VISIBLE);
            } else {
                //mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        updateWatchInterface();
        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }
        }
    }
}










/*
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class focusmode extends AppCompatActivity implements TimePicker.OnTimeChangedListener {
    private Button btn_move;
    private EditText mode_focus;
    private EditText warning;
    TimePicker tp;
    TextView tv;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.focusmode);

        mode_focus = findViewById(R.id.mode_focus);

        warning = findViewById(R.id.warning);

        btn_move = findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(focusmode.this, focusmode2.class);
                startActivity(intent);  // 액티비티 이동
            }
        });


        // TimePicker
        c = Calendar.getInstance();
        int hourOfDay =c.get(Calendar.HOUR_OF_DAY);    // 현재 시각
        int minute = c.get(Calendar.MINUTE);           // 현재 분
        // int second = c.get(Calendar.SECOND);           // 현재 초

        tp = findViewById(R.id.tp);
        tp.setOnTimeChangedListener(this);

        tv = findViewById(R.id.tv);

        tv.setText(c.get(Calendar.HOUR_OF_DAY) + " : " + c.get(Calendar.MINUTE) + "까지 핸드폰을 사용하실 수 없습니다.");
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        System.out.println(c.get(Calendar.HOUR_OF_DAY) + " : " + c.get(Calendar.MINUTE) + "까지 핸드폰을 사용하실 수 없습니다.");
    }
}
 */