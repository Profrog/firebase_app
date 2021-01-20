package com.example.teamproject01;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

public class focusmode2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.focusmode2);
    }

    @Override
    // 뒤로가기 키 막기
    public void onBackPressed()
    {
        Toast.makeText(this, "집중하세요!", Toast.LENGTH_SHORT).show();
        return;
    }

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
        Toast.makeText(this, "집중하세요!", Toast.LENGTH_SHORT).show();
        return;
    }
}