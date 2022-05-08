package com.example.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  MainActivity3 extends AppCompatActivity {

    int id = 0;
    EditText e1,e2,e3,e4,e5;
    Button updateBtn, deleteBtn, saveDetails;
    user u = new user();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        e1 = findViewById(R.id.firstName);
        e2 = findViewById(R.id.secondName);
        e3 = findViewById(R.id.mobileNumber);
        e4 = findViewById(R.id.email);
        e5 = findViewById(R.id.password);
        updateBtn= findViewById(R.id.updateButton);
        deleteBtn= findViewById(R.id.deleteButton);
        saveDetails= findViewById(R.id.saveDetailsButton);

        saveDetails.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("ID");
            e1.setText(String.valueOf(id));
            System.out.println("Got the ID as Ranuka Harshana " + id);
        }

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("user");
        DatabaseReference mDbRef = rootRef.child(String.valueOf(id));
        mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                e1.setText(dataSnapshot.child("firstName").getValue(String.class));
                e2.setText(dataSnapshot.child("seconName").getValue(String.class));
                e3.setText(dataSnapshot.child("mobile").getValue(String.class));
                e4.setText(dataSnapshot.child("email").getValue(String.class));
                e5.setText(dataSnapshot.child("password").getValue(String.class));

                e1.setEnabled(false);
                e2.setEnabled(false);
                e3.setEnabled(false);
                e4.setEnabled(false);
                e5.setEnabled(false);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void clickOnTextBox(View view){
        e1.setEnabled(true);
        e2.setEnabled(true);
        e3.setEnabled(true);
        e4.setEnabled(true);
        e5.setEnabled(true);

        updateBtn.setVisibility(View.GONE);
        deleteBtn.setVisibility(View.GONE);
        saveDetails.setVisibility(View.VISIBLE);

    }

    public void logout(View view){
        Intent i = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(i);
    }

    public void deleteAcount(View view){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("user");
        DatabaseReference mDbRef = rootRef.child(String.valueOf(id));
        mDbRef.removeValue();
        Intent i = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(i);
    }

    public void Update(View view){
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

                            updateBtn.setVisibility(View.VISIBLE);
                            deleteBtn.setVisibility(View.VISIBLE);
                            saveDetails.setVisibility(View.GONE);
                        }
                    }
                }
            }

        }


    }
}