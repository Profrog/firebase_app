package com.example.teamproject01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.sql.Time;
import java.util.Calendar;

import com.example.teamproject01.focusmode2;


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
        setContentView(R.layout.activity_main);

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