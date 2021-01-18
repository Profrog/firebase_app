package com.example.teamproject01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


    public class firstWindow extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.firstwindow);
            startLoading();
            Intent intent2 = new Intent(this, Login.class);
            intent2.addFlags(intent2.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
        }

        private void startLoading() {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        }
    }
