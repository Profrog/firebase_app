package com.example.teamproject01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class goalcheck extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goalcheck1);

        final TextView username=(TextView)findViewById(R.id.name);
        String username1 = ((MainActivity) MainActivity.forstatic).returnUsername();
        username.setText(username1);

        final TextView usercode=(TextView)findViewById(R.id.code);
        String usercode1 = ((MainActivity) MainActivity.forstatic).returnUsercode();
        usercode.setText(usercode1);

        final TextView userclass=(TextView)findViewById(R.id.classcode);
        String class1 = ((MainActivity) MainActivity.forstatic).returnClasscode();
        userclass.setText(class1);


    }
}

/*
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class goalcheck extends AppCompatActivity {

    private ImageView iv;
    private String text;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);
    }

    public void profile(View v){
        final EditText profile1 = (EditText) findViewById(R.id.getdata);
        String username1 = ((MainActivity) MainActivity.forstatic).returnUsername();

        profile1.setText(username1);
    }
}
*/