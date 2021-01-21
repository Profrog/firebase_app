package com.example.teamproject01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class jkgoalcheck0 extends AppCompatActivity {

    private TextView username;
    private TextView usercode;

    private String usercode1;

    private TextView goal1;
    private TextView goal2;
    private TextView goal3;
    private TextView goal4;

    CheckBox check1;
    CheckBox check2;
    CheckBox check3;
    CheckBox check4;

    private FirebaseDatabase DB1;
    private DatabaseReference myref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jkgoalcheck);

        username = (TextView)findViewById(R.id.name);
        usercode = (TextView)findViewById(R.id.code);

        goal1 = (TextView)findViewById(R.id.text1);
        goal2 = (TextView)findViewById(R.id.text2);
        goal3 = (TextView)findViewById(R.id.text3);
        goal4 = (TextView)findViewById(R.id.text4);

        username.setText(((MainActivity)MainActivity.forstatic).returnUsername());
        usercode.setText(((MainActivity)MainActivity.forstatic).returnUsercode());

        usercode1 = usercode.getText().toString();

        DB1 = FirebaseDatabase.getInstance();
        myref = DB1.getReference("check").child(usercode1);

        setGoal("goal1",goal1);
        setGoal("goal2",goal2);
        setGoal("goal3",goal3);
        setGoal("goal4",goal4);

        check1 = (CheckBox)findViewById(R.id.checkBox1);
        check2 = (CheckBox)findViewById(R.id.checkBox2);
        check3 = (CheckBox)findViewById(R.id.checkBox3);
        check4 = (CheckBox)findViewById(R.id.checkBox4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        username.setText(((MainActivity)MainActivity.forstatic).returnUsername());
        usercode.setText(((MainActivity)MainActivity.forstatic).returnUsercode());

        usercode1 = usercode.getText().toString();
        setGoal("goal1", goal1);
        setGoal("goal2", goal2);
        setGoal("goal3", goal3);
        setGoal("goal4", goal4);
    }

    public void setGoal(String goals,final TextView egoal){
        myref.child(goals).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists() ) {
                    egoal.setText("목표가 비어있습니다.");
                }
                else egoal.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void changegoal(View v) {
        final int[] tmp = new int[1];
        tmp[0]=0;
        if(check1.isChecked() && !goal1.getText().toString().equals("목표가 비어있습니다.")){
            myref.child("goal1num").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    tmp[0] = Integer.valueOf(snapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            tmp[0]+=1;
            myref.child("goal1num").setValue(tmp[0]);
        }
        if(check2.isChecked() && !goal2.getText().toString().equals("목표가 비어있습니다.")){
            myref.child("goal2num").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    tmp[0] = Integer.valueOf(snapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            tmp[0]+=1;
            myref.child("goal2num").setValue(tmp[0]);
        }
        if(check3.isChecked() && !goal3.getText().toString().equals("목표가 비어있습니다.")){
            myref.child("goal3num").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    tmp[0] = Integer.valueOf(snapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            tmp[0]+=1;
            myref.child("goal3num").setValue(tmp[0]);
        }
        if(check4.isChecked() && !goal4.getText().toString().equals("목표가 비어있습니다.")){
            myref.child("goal4num").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    tmp[0] = Integer.valueOf(snapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            tmp[0]+=1;
            myref.child("goal4num").setValue(tmp[0]);
        }
        Toast.makeText(jkgoalcheck0.this, "반영되었습니다!", Toast.LENGTH_LONG).show();
    }



}
