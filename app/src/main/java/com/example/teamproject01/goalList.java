package com.example.teamproject01;

import android.content.Intent;
import android.content.res.Resources;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class goalList extends AppCompatActivity {


    public int pur_count = 0;
    public List<String> test_purpose;
    private Button mButton[];
    private List<Button> mButton2;
    private LinearLayout subLayout;
    public Button button02;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goallist01);


        String username1 = ((MainActivity) MainActivity.forstatic).returnUsername();
        String usercode1 = ((MainActivity) MainActivity.forstatic).returnUsercode();
        String classcode1 = ((MainActivity) MainActivity.forstatic).returnClasscode();


        //((MainData2) MainData2.forstatic2).changingpurpose(0);
        //pur_count = ((MainData2) MainData2.forstatic2).returnpurpose();
        test_purpose = new ArrayList<>(101);

        //final TextView purpose_content = (TextView)findViewById(R.id.purpose_contents);

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        //Date currentTime = Calendar.getInstance().getTime();


        DatabaseReference myRef = DB1.getReference("check").child(classcode1).child("purpose");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Object newPost = dataSnapshot.getValue();
                //purpose_content.setText(newPost.toString());

                if(pur_count < 100) {
                    test_purpose.add(newPost.toString());
                    pur_count++;
                   // ((MainData2) MainData2.forstatic2).changingpurpose(pur_count);
                }
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {

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
       // final TextView purpose_content = (TextView)findViewById(R.id.purpose_contents);

        String username1 = ((MainActivity) MainActivity.forstatic).returnUsername();
        String usercode1 = ((MainActivity) MainActivity.forstatic).returnUsercode();
        String classcode1 = ((MainActivity) MainActivity.forstatic).returnClasscode();

        FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
        Date currentTime = Calendar.getInstance().getTime();

        DatabaseReference myRef = DB1.getReference("check").child(classcode1).child("purpose").child(currentTime.toString()); //여기 고쳐야 합니다
        myRef.setValue(enroll_purpose.getText().toString());

        enroll_purpose.setText("");

        //Map<String, Object> childUpdates = new HashMap<>();
        //childUpdates.put(usercode1,enroll_purpose.getText().toString());

    }


    public void clicksav_update1(View v)
    {
       // final TextView purpose_content = (TextView)findViewById(R.id.purpose_contents);

        data_changing();

       //Intent intent1 = new Intent(getApplicationContext(), goalList.class);
        //startActivity(intent1);
    }

    public void data_changing()
    {

        ScrollView  scroll01 = (ScrollView)findViewById(R.id.puspose_scroll);
        scroll01.removeAllViews();

        LinearLayout subLayout= new LinearLayout(this);
        subLayout.setOrientation(LinearLayout.VERTICAL);

        //subLayout.addView();


        for(int x = 0 ; x < test_purpose.size() ; x++)
        {
            final Button button01 = new Button(this);
            button01.setText(Integer.toString(x) + " : " + test_purpose.get(x));
            button01.setId(x);

            button01.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO : click event
                    Button b = (Button)view;
                    ((MainActivity)MainActivity.forstatic).changingchatState(b.getText().toString());

                    FirebaseDatabase DB1 = FirebaseDatabase.getInstance();
                    Date currentTime = Calendar.getInstance().getTime();

                    DatabaseReference myRef = DB1.getReference("check").child( ((MainActivity) MainActivity.forstatic).returnClasscode()).child(((MainActivity) MainActivity.forstatic).returnchatState()).child(currentTime.toString());
                    myRef.setValue( ((MainActivity) MainActivity.forstatic).returnUsername() + " 님이 입장하셨습니다" + "\n");

                    Intent intent1 = new Intent(getApplicationContext(), chat.class);
                    startActivity(intent1);

                }
            });





            //mButton[x] = (Button) findViewById(x);
            //mButton[x].setText("hihihi");
            subLayout.addView(button01);
        }


        scroll01.addView(subLayout); //지정된 뷰에 셋팅완료된 mButton을 추가

    }




    public Button createButton(String number) {
        Button button = new Button(this);
        button.setText(number);
        return button;
    }


    class MyListener implements View.OnClickListener {

        public void onClick(View v){

            Intent intent1 = new Intent(getApplicationContext(), goalcheck.class);
            startActivity(intent1);
        }
    }


}
