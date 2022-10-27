package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ThankYouPage extends AppCompatActivity {
    FloatingActionButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you_page);
        back=findViewById(R.id.backInThankYou);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ThankYouPage.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(ThankYouPage.this,MainActivity.class);
        startActivity(i);
    }
}