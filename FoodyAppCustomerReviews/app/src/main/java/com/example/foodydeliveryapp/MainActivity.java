package com.example.foodydeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1,button2,button3,button4 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.pri_button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityPages.class);

                startActivity(intent);
            }
        });

        button2= findViewById(R.id.button);
        Intent chooser;

        Intent intent2 = new Intent(Intent.ACTION_SEND);
        intent2.setData(Uri.parse("mailto:"));
        intent2.putExtra(Intent.EXTRA_EMAIL,new String[]{"trojanhorses321@gmail.com"});
        intent2.putExtra(Intent.EXTRA_SUBJECT, "Customer Review About Foody Delivery App");
        intent2.putExtra(Intent.EXTRA_TEXT,"Hay Trojan Horses; ");

        intent2.setType("message/rfc822");
        chooser = Intent.createChooser(intent2,"Send Email to Foody Delivery App");

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(chooser);
            }
        });

        button3= findViewById(R.id.button2);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoUrl("https://play.google.com/store/apps");
            }
        });

        button4 = findViewById(R.id.button3);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(),RateUs.class);

                startActivity(intent3);
            }
        });


    }




    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}