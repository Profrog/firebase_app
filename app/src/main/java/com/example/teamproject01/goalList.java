package com.example.teamproject01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class goalList extends AppCompatActivity {


    String purpose_original;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goallist01);

        String username1 = ((MainActivity) MainActivity.forstatic).returnUsername();
        String usercode1 = ((MainActivity) MainActivity.forstatic).returnUsercode();
        String classcode1 = ((MainActivity) MainActivity.forstatic).returnClasscode();



        final TextView purpose_content = (TextView)findViewById(R.id.purpose_contents);

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = DB1.getReference("check").child(classcode1).child("purpose");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Object newPost = dataSnapshot.getValue();
                purpose_content.setText(newPost.toString());
                purpose_original = purpose_content.getText().toString();

            }

            
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                Object newPost = dataSnapshot.getValue();
                purpose_content.setText(newPost.toString());
                purpose_original = purpose_content.getText().toString();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


    }



    public void clicksav_purpose1(View v) {

        final EditText enroll_purpose = (EditText) findViewById(R.id.enroll_purpose);
        final TextView purpose_content = (TextView)findViewById(R.id.purpose_contents);

        String username1 = ((MainActivity) MainActivity.forstatic).returnUsername();
        String usercode1 = ((MainActivity) MainActivity.forstatic).returnUsercode();
        String classcode1 = ((MainActivity) MainActivity.forstatic).returnClasscode();

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = DB1.getReference("check").child(classcode1).child("purpose").child(usercode1);
        myRef.setValue(enroll_purpose.getText().toString());

        //Map<String, Object> childUpdates = new HashMap<>();
        //childUpdates.put(usercode1,enroll_purpose.getText().toString());

        enroll_purpose.setText("");
    }


    class MyListener implements View.OnClickListener {

        public void onClick(View v){

            Intent intent1 = new Intent(getApplicationContext(), goalcheck.class);
            startActivity(intent1);

        }

    }


}
