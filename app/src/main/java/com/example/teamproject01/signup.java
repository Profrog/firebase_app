package com.example.teamproject01;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase DB1;
    private FirebaseUser user;
    private EditText editTextId;
    private EditText editTextPassword;
    private EditText editTextName;
    private Button buttonJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        DB1 = FirebaseDatabase.getInstance();

        editTextId = (EditText) findViewById(R.id.editText_Id);
        editTextPassword = (EditText) findViewById(R.id.editText_passWord);
        editTextName = (EditText) findViewById(R.id.editText_name);

        buttonJoin = (Button) findViewById(R.id.btn_join);
        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextId.getText().toString().equals("") && !editTextPassword.getText().toString().equals("") && !editTextName.getText().toString().equals("") ) {
                    // 학번과 비밀번호와 이름이 공백이 아닐 경우
                    createUser(editTextId.getText().toString()+"@inha.ac.kr", editTextPassword.getText().toString(), editTextName.getText().toString());
                }
                else {
                    Toast.makeText(signup.this, "학번 이름 비밀번호를 모두 입력하세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    private void createUser(String email, String password, String name) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공시
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                          //  updateUI(user);

                            DatabaseReference myRef = DB1.getReference("User").child(editTextId.getText().toString()).child("name");
                            myRef.setValue( editTextName.getText().toString());

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName( editTextName.getText().toString())
                                    .setPhotoUri(null)
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                                                           }
                                        }
                                    });


                            Toast.makeText(signup.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            // 계정이 중복된 경우
                            Toast.makeText(signup.this, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}