package com.example.ekart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Screen extends AppCompatActivity {

    ImageView img;
    TextView txt;
    Animation end,still;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        txt=findViewById(R.id.txt);
        img=findViewById(R.id.img);

        end= AnimationUtils.loadAnimation(this,R.anim.end);
        still=AnimationUtils.loadAnimation(this,R.anim.still);

        img.setAnimation(end);
        txt.setAnimation(still);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Screen.this,MainActivity.class);
                startActivity(i);
            }
        },3500);
    }
}