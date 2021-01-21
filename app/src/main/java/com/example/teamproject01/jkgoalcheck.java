package com.example.teamproject01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class jkgoalcheck extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private String usercode;
    private Button btn;
    private FirebaseDatabase DB1;
    private DatabaseReference myref;



    EditText goal1;
    EditText goal2;
    EditText goal3;
    EditText goal4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jkgoalcheck1);

        username=(EditText)findViewById(R.id.name);
        username.setHint(((MainActivity) MainActivity.forstatic).returnUsername());
        password=(EditText)findViewById(R.id.password);
        password.setHint("Password");
        usercode = ((MainActivity)MainActivity.forstatic).returnUsercode();
        btn = (Button) findViewById(R.id.changeprofile);

        goal1 = (EditText)findViewById(R.id.text1);
        goal2 = (EditText)findViewById(R.id.text2);
        goal3 = (EditText)findViewById(R.id.text3);
        goal4 = (EditText)findViewById(R.id.text4);



        DB1 = FirebaseDatabase.getInstance();
        myref = DB1.getReference();
        setGoal("goal1",goal1);
        setGoal("goal2",goal2);
        setGoal("goal3",goal3);
        setGoal("goal4",goal4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        username.setText(((MainActivity)MainActivity.forstatic).returnUsername());
        usercode=((MainActivity)MainActivity.forstatic).returnUsercode();


        setGoal("goal1", goal1);
        setGoal("goal2", goal2);
        setGoal("goal3", goal3);
        setGoal("goal4", goal4);
    }

    public void setGoal(String goals,final EditText egoal) {
        DatabaseReference myRef =DB1.getReference("check").child(usercode).child(goals);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists() ) {
                    egoal.setHint("목표가 비어있습니다.");
                }
                else{
                    String newPost = snapshot.getValue().toString();
                    egoal.setHint(newPost);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public void changeprofile(View v){
        if(username.getText().toString() == "" || password.getText().toString() == "") Toast.makeText(jkgoalcheck.this, "이름과 비밀번호를 모두 입력하세요.", Toast.LENGTH_LONG).show();
        else if (password.getText().length() < 6) Toast.makeText(jkgoalcheck.this, "비밀번호는 6글자 이상이어야 합니다.", Toast.LENGTH_LONG).show();
        else {
            Intent intent = new Intent(this, profilepopup.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                ((MainActivity)MainActivity.forstatic).changingUsername(username.getText().toString());
                ((MainActivity)MainActivity.forstatic).changePassword(password.getText().toString());
            }
        }
    }




    public void changegoal(View v) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(jkgoalcheck.this);

        if (goal1.getText().toString().getBytes().length <= 0 &&
                goal2.getText().toString().getBytes().length <= 0 &&
                goal3.getText().toString().getBytes().length <= 0 &&
                goal4.getText().toString().getBytes().length <= 0
        ) {
            dlg.setTitle("목표 설정"); //제목
            dlg.setMessage("변경사항이 없습니다."); // 메시지
            dlg.setIcon(R.drawable.hi); // 아이콘 설정
//                버튼 클릭시 동작
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            dlg.show();
        }

        else { dlg.setTitle("목표 설정"); //제목
        dlg.setMessage("목표가 변경되었습니다!!"); // 메시지
        dlg.setIcon(R.drawable.hi); // 아이콘 설정
//                버튼 클릭시 동작
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dlg.show();
    }
        if(goal1.getText().toString().getBytes().length > 0) {
            myref.child("User").child(usercode).child("goal1").setValue( goal1.getText().toString());
            myref.child("User").child(usercode).child("goal1num").setValue(0);
        }
        if(goal2.getText().toString().getBytes().length > 0) {
            myref.child("User").child(usercode).child("goal2").setValue( goal2.getText().toString());
            myref.child("User").child(usercode).child("goal2num").setValue(0);
        }
        if(goal3.getText().toString().getBytes().length > 0) {
            myref.child("User").child(usercode).child("goal3").setValue( goal3.getText().toString());
            myref.child("User").child(usercode).child("goal3num").setValue(0);
        }
        if(goal4.getText().toString().getBytes().length > 0) {
            myref.child("User").child(usercode).child("goal4").setValue( goal4.getText().toString());
            myref.child("User").child(usercode).child("goal4num").setValue(0);
        }


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