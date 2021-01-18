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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class chat extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat01);

        String username1 = ((MainActivity) MainActivity.forstatic).returnUsername();
        String usercode1 = ((MainActivity) MainActivity.forstatic).returnUsercode();
        String classcode1 = ((MainActivity) MainActivity.forstatic).returnClasscode();
        String chatstate1 = ((MainActivity) MainActivity.forstatic).returnchatState();
        final TextView chatupdatedata1 = (TextView) findViewById(R.id.chat_contents);

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = DB1.getReference("check").child(classcode1).child(chatstate1);


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Object newPost = dataSnapshot.getValue();
                chatupdatedata1.setText( chatupdatedata1.getText() + newPost.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                //Object newPost = dataSnapshot.getValue();
                //chatupdatedata1.setText((newPost.toString()));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        final TextView title1 = (TextView) findViewById(R.id.title);
        title1.setText(chatstate1 + " : " + classcode1);

    }



    public void clicksaving1(View v){

        String username1 = ((MainActivity) MainActivity.forstatic).returnUsername();
        String usercode1 = ((MainActivity) MainActivity.forstatic).returnUsercode();
        String classcode1 = ((MainActivity) MainActivity.forstatic).returnClasscode();
        String chatstate1 = ((MainActivity) MainActivity.forstatic).returnchatState();
        final EditText chatdata1 = (EditText) findViewById(R.id.enroll_data);

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        Date currentTime = Calendar.getInstance().getTime();

        DatabaseReference myRef = DB1.getReference("check").child(classcode1).child(chatstate1).child(currentTime.toString());
        myRef.setValue( username1  + ":"  +  chatdata1.getText().toString() + '\n');

    }

    public void clickupdate1(View v){

    }




}
