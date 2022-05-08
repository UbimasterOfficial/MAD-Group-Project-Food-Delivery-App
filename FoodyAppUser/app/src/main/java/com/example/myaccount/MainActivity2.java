package com.example.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity2 extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    Button btn;
    user u = new user();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        e1 = findViewById(R.id.firstName);
        e2 = findViewById(R.id.secondName);
        e3 = findViewById(R.id.mobileNumber);
        e4 = findViewById(R.id.email);
        e5 = findViewById(R.id.password);
        btn= findViewById(R.id.signUpButton);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mDbRef = rootRef.child("user");
        mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                u.setId((int) snapshot.getChildrenCount()+ 1);
                /*for (DataSnapshot dsp : snapshot.getChildren()){

                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void signUp(View view){
        if(e1.getText().toString().equals("")){
            e1.setError("Please Enter First Name");
        }
        else {
            if(e2.getText().toString().equals("")){
                e2.setError("Please Enter Second Name");
            }
            else {
                if(e3.getText().toString().equals("")){
                    e3.setError("Please Enter Mobile valid Number");
                }
                else{
                    if((e4.getText().toString().equals("") || e4.getText().toString().split("@").length!= 2)){
                        e4.setError("Please Enter valid Email Address");
                    }
                    else{
                        if(e5.getText().toString().equals("")){
                            e5.setError("Please Enter Password");
                        }
                        else {
                            u.setFirstName(e1.getText().toString());
                            u.setSeconName(e2.getText().toString());
                            u.setMobile(e3.getText().toString());
                            u.setEmail(e4.getText().toString());
                            u.setPassword(e5.getText().toString());
                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                            DatabaseReference mDbRef = rootRef.child("user");
                            mDbRef.child(String.valueOf(u.getId())).setValue(u);

                            Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                            i.putExtra("ID", u.getId());
                            startActivity(i);
                        }
                    }
                }
            }

        }
    }
}