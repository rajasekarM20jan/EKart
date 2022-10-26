package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ThankYouPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you_page);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(ThankYouPage.this,MainActivity.class);
        startActivity(i);
    }
}