package com.example.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2;
    int count = 0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.un);
        ed2 = findViewById(R.id.pw);
    }

    public void login(View view){
        if(ed1.equals("") || ed1.getText().toString().split("@").length!= 2){
            ed1.setError("Please enter the username");
        }
        else {
            if(ed2.equals("")){
                ed1.setError("Please enter the password");
            }
            else {
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mDbRef = rootRef.child("user");
                mDbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dsp : snapshot.getChildren()) {
                            if (dsp.child("email").getValue(String.class).equals(ed1.getText().toString()) && dsp.child("password").getValue(String.class).equals(ed2.getText().toString())) {
                                Intent i = new Intent(MainActivity.this, MainActivity3.class);
                                i.putExtra("ID", dsp.child("id").getValue(Integer.class));
                                startActivity(i);
                                count++;
                            }
                        }
                        if (count == 0) {
                            Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        }
    }
    public void signUpView(View view){
        Intent i = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(i);
    }
}