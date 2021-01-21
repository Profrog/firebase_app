package com.example.teamproject01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


public class MainData2 extends AppCompatActivity {


    private String username ="";
    private String usercode = "";
    private String classcode = "";
    private String chatstate = "";
    private int purpose_count = 0;
    public static Context forstatic2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forstatic2 = this;

        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);

    }


    public void changingUsername(String data) { username = data; }
    public void changingUsercode(String data) { usercode = data; }
    public void changingClasscode(String data) { classcode = data; }
    public void changingchatState(String data) { chatstate = data; }
    public void changingpurpose(int count){ purpose_count = count; };

    public String returnUsername() {return username;}
    public String returnUsercode() {return usercode;}
    public String returnClasscode() {return classcode;}
    public String returnchatState() {return chatstate;}
    public int returnpurpose(){ return purpose_count; };


}