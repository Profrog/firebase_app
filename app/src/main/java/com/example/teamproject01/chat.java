package com.example.teamproject01;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class chat extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat01);
    }

    public void clicksaving1(View v){

        String username1 = ((MainActivity) MainActivity.forstatic).returnUsername();
        String usercode1 = ((MainActivity) MainActivity.forstatic).returnUsercode();
        String classcode1 = ((MainActivity) MainActivity.forstatic).returnClasscode();
        final EditText chatdata1 = (EditText) findViewById(R.id.enroll_data);

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        Date currentTime = Calendar.getInstance().getTime();

        DatabaseReference myRef = DB1.getReference("check").child(classcode1).child("chat").child(currentTime.toString());
        myRef.setValue( username1  + ":"  +  chatdata1.getText().toString());
        //Map<String, Object> childUpdates = new HashMap<>();
        //childUpdates.put(chatdata1.getText().toString() , username1);
        //childUpdates.put("/user-posts/" + userId + "/" + key, postValues);
        //myRef.updateChildren(childUpdates);
    }


    public void clickupdate1(View v){

        String username1 = ((MainActivity) MainActivity.forstatic).returnUsername();
        String usercode1 = ((MainActivity) MainActivity.forstatic).returnUsercode();
        String classcode1 = ((MainActivity) MainActivity.forstatic).returnClasscode();
        final TextView chatupdatedata1 = (TextView) findViewById(R.id.chat_contents);

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = DB1.getReference("check").child(classcode1).child("chat");
        /*
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {


                // A new comment has been added, add it to the displayed list
                chatupdatedata1.setText(dataSnapshot.getValue().toString());

                // ...
            }

            myRef.addChildEventListener(childEventListener);
        }
            */
       chatupdatedata1.setText(myRef.get().toString());
    }

}
