package com.example.teamproject01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private String username ="이름";
    private String usercode = "12161538";
    private String classcode = "학수번호";
    private String chatstate = "chat";

    private String enroll_date;
    private String returnString;

    private long backBtnTime = 0;

    public static Context forstatic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forstatic = this;

        Intent intent1 = new Intent(getApplicationContext(), MainData2.class);
        startActivity(intent1);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user==null) {
            Intent intent = new Intent(this, firstWindow.class);
            startActivity(intent);
        }


        username = user.getDisplayName();
        usercode = user.getEmail().substring(0,8);
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            user = null;
            super.onBackPressed();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }

    public void changingUsername(String data)
    {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(data)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "이름 변경 완료!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public String returnUsername() {return username;}

    public void changingUsercode(String data) {
        user.updateEmail(data+"@inha.ac.kr")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "학번 변경 완료!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void changePassword(String data) {
        user.updatePassword(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "비밀번호 변경 완료!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void changingClasscode(String data) { classcode = data; }
    public void changingchatState(String data) { chatstate = data; }

    public String returnUsercode() {return usercode;}
    public String returnClasscode() {return classcode;}
    public String returnchatState() {return chatstate;}



    public void changingDateenroll(String data)
    {
        enroll_date = data;
    }

    public String getReturnString()
    {
        returnString = username + "," + usercode + "," + enroll_date;
        return returnString;
    }


    public void click01(View v){ //firebase 데이타베이스도 여기서 진행
        Intent intent1 = new Intent(getApplicationContext(), identifyJava.class);
        startActivity(intent1);
    }

    public void click02(View v){
        Intent intent1 = new Intent(getApplicationContext(), goalList.class);
        startActivity(intent1);
    }

    public void click03(View v){
        Intent intent1 = new Intent(getApplicationContext(), jkgoalcheck.class);
        startActivity(intent1);
        // 연결된 함수
    }

    public void click04(View v){
        Intent intent1 = new Intent(getApplicationContext(), QRcodeJava.class);
        startActivity(intent1);
    }

    public void click05(View v){
        TextView showing1 = (TextView) findViewById(R.id.showing);
        showing1.setText(getReturnString());
    }

    public void click06(View v){
        Intent intent1 = new Intent(getApplicationContext(), focusmode.class);
        startActivity(intent1);
    }

    public void profile(View v){
        Intent intent1 = new Intent(getApplicationContext(), goalcheck.class);
        startActivity(intent1);
    }




    public void logoutt(View v){
        new AlertDialog.Builder(this)
                .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent i = new Intent(MainActivity.this, Login.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })
                .show();
    }
}