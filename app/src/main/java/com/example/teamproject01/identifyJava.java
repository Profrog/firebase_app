package com.example.teamproject01;

import android.content.Intent;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class identifyJava extends AppCompatActivity {

    private EditText classcode1;
    private TextView usercode1;
    private TextView username1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identifyjava);

        username1 = (TextView) findViewById(R.id.username);
        usercode1 = (TextView) findViewById(R.id.usercode);
        classcode1 = (EditText) findViewById(R.id.classcode);

        username1.setText(((MainActivity)MainActivity.forstatic).returnUsername());
        usercode1.setText(((MainActivity)MainActivity.forstatic).returnUsercode());
    }

    public void clicksaving(View v){

        final TextView login_content = (TextView)findViewById(R.id.login_contents);

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = DB1.getReference("check").child(classcode1.getText().toString()).child(usercode1.getText().toString());

        myRef.setValue(username1.getText().toString());

        ((MainActivity)MainActivity.forstatic).changingUsername(username1.getText().toString());
        ((MainActivity)MainActivity.forstatic).changingUsercode(usercode1.getText().toString());
        ((MainActivity)MainActivity.forstatic).changingClasscode(classcode1.getText().toString());

        String showdata = "이름 " + username1.getText().toString() + "\n" + "학번 " + usercode1.getText().toString() + "\n" +  "학수번호 " + classcode1.getText().toString();
        login_content.setText(showdata);

    }


    public void clickcancel(View v) {

        final TextView login_content = (TextView)findViewById(R.id.login_contents);

        username1.setText("이름");
        usercode1.setText("학번");
        classcode1.setText("");

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = DB1.getReference("check").child(classcode1.getText().toString()).child(usercode1.getText().toString());

        myRef.setValue(username1.toString());
        myRef.removeValue();

        ((MainActivity)MainActivity.forstatic).changingUsername("이름");
        ((MainActivity)MainActivity.forstatic).changingUsercode("학번");
        ((MainActivity)MainActivity.forstatic).changingClasscode("");

        String showdata = "이름 " + username1.getText().toString() + "\n" + "학번 " + usercode1.getText().toString() + "\n" +  "학수번호 " + classcode1.getText().toString();
        login_content.setText(showdata);

    }


    public void clickAlarm(View v) {

        ((MainActivity)MainActivity.forstatic).changingUsername(username1.getText().toString());
        ((MainActivity)MainActivity.forstatic).changingUsercode(usercode1.getText().toString());
        ((MainActivity)MainActivity.forstatic).changingClasscode(classcode1.getText().toString());
        ((MainActivity)MainActivity.forstatic).changingchatState("alarm");

        if(((MainActivity)MainActivity.forstatic).getReturnString() != "") {

            FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
            Date currentTime = Calendar.getInstance().getTime();

            DatabaseReference myRef = DB1.getReference("check").child(classcode1.getText().toString()).child("alarm").child(currentTime.toString());
            myRef.setValue(username1.getText().toString() + " 님이 입장하셨습니다" + "\n");

            Intent intent1 = new Intent(getApplicationContext(), chat.class);
            startActivity(intent1);
        }
    }

    public void clickchat(View v) {

        ((MainActivity)MainActivity.forstatic).changingUsername(username1.getText().toString());
        ((MainActivity)MainActivity.forstatic).changingUsercode(usercode1.getText().toString());
        ((MainActivity)MainActivity.forstatic).changingClasscode(classcode1.getText().toString());
        ((MainActivity)MainActivity.forstatic).changingchatState("chat");

        if(((MainActivity)MainActivity.forstatic).getReturnString() != "") {

            FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
            Date currentTime = Calendar.getInstance().getTime();

            DatabaseReference myRef = DB1.getReference("check").child(classcode1.getText().toString()).child("chat").child(currentTime.toString());
            myRef.setValue(username1.getText().toString() + " 님이 입장하셨습니다" + "\n");

            Intent intent1 = new Intent(getApplicationContext(), goalList.class);
            startActivity(intent1);
        }
    }

}