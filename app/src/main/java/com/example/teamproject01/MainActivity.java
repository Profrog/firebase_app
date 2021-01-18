package com.example.teamproject01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private String username ="";
    private String usercode = "";
    private String classcode = "";
    private String chatstate = "";

    private String enroll_date;
    private String returnString;

    public static Context forstatic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forstatic = this;
    }

    public void changingUsername(String data)
    {
        username = data;
    }
    public String returnUsername() {return username;}

    public void changingUsercode(String data) { usercode = data; }
    public String returnUsercode() {return usercode;}

    public void changingClasscode(String data) { classcode = data; }
    public String returnClasscode() {return classcode;}

    public void changingchatState(String data) { chatstate = data; }
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
        Intent intent1 = new Intent(getApplicationContext(), purchasingJava.class);
        startActivity(intent1);
    }

    public void click03(View v){
        Intent intent1 = new Intent(getApplicationContext(), goalcheck.class);
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
        Intent intent1 = new Intent(getApplicationContext(), goalcheck.class);
        startActivity(intent1);
    }

}