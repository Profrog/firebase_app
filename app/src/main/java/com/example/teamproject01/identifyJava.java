package com.example.teamproject01;

import android.content.Intent;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class identifyJava extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identifyjava);
    }


    public void clicksaving(View v){

        final EditText username1 = (EditText) findViewById(R.id.username);
        final EditText usercode1 = (EditText) findViewById(R.id.usercode);
        final EditText classcode1 = (EditText) findViewById(R.id.classcode);

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = DB1.getReference("check").child(classcode1.getText().toString()).child(usercode1.getText().toString());

        myRef.setValue(username1.getText().toString());

        ((MainActivity)MainActivity.forstatic).changingUsername(username1.getText().toString());
        ((MainActivity)MainActivity.forstatic).changingUsercode(usercode1.getText().toString());
        ((MainActivity)MainActivity.forstatic).changingUsercode(classcode1.getText().toString());
    }


    public void clickcancel(View v) {

        final EditText username1 = (EditText) findViewById(R.id.username);
        final EditText usercode1 = (EditText) findViewById(R.id.usercode);
        final EditText classcode1 = (EditText) findViewById(R.id.classcode);

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = DB1.getReference("check").child(classcode1.getText().toString()).child(usercode1.getText().toString());

        myRef.setValue(username1.toString());
        myRef.removeValue();

        ((MainActivity) MainActivity.forstatic).changingUsername(username1.getText().toString());
        ((MainActivity) MainActivity.forstatic).changingUsercode(usercode1.getText().toString());
    }


    public void clickupdate(View v) {

        final EditText username1 = (EditText) findViewById(R.id.username);
        final EditText usercode1 = (EditText) findViewById(R.id.usercode);
        final EditText classcode1 = (EditText) findViewById(R.id.classcode);
        final EditText updatefield1 = (EditText) findViewById(R.id.updatefield);

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = DB1.getReference("check").child(classcode1.getText().toString()).child("ALARM").child(username1.getText().toString());
        myRef.setValue(updatefield1.getText().toString());
    }


    public void clickchat(View v) {
        Intent intent1 = new Intent(getApplicationContext(), chat.class);
        startActivity(intent1);
    }

}
