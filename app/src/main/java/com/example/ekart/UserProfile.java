package com.example.ekart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.Profile;

public class UserProfile extends AppCompatActivity {
    TextView name,mobile,email,password,loginStatus;
    FloatingActionButton home;
    ArrayList<Profile> userData;
    DbForUser db;
    Button LogoutButton,orderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        name=findViewById(R.id.fullName);
        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginStatus=findViewById(R.id.loginStatus);
        home =findViewById(R.id.filterPage);
        LogoutButton=findViewById(R.id.logoutButton);
        orderHistory=findViewById(R.id.orderHistory);
        userData=new ArrayList<Profile>();
        db=new DbForUser(UserProfile.this);
        SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
        String mobile1=sp.getString("mobile","no");
        db.getData(mobile1);
        String uName=db.name;
        String uMobile=db.mobileNumber;
        String uMail=db.email;
        String uPass=db.password;
        String uLogin=db.login;
        userData.add(new Profile(uName,uMobile,uMail,uPass,uLogin));
        name.setText(userData.get(0).getName());
        mobile.setText(userData.get(0).getMobile());
        email.setText(userData.get(0).getMail());
        password.setText(userData.get(0).getPassword());
        if(userData.get(0).getLoginStatus().equals("true")){
            loginStatus.setText("Active");
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserProfile.this,MainActivity.class);
                startActivity(i);
            }
        });

        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent oh=new Intent(UserProfile.this,OrderHistory.class);
                startActivity(oh);
            }
        });

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(UserProfile.this);
                alert.setMessage("Are You Sure about Signing Out?");
                alert.setCancelable(false);
                alert.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
                        String mobile=sp.getString("mobile","no");
                        DbForUser db=new DbForUser(UserProfile.this);
                        db.updateData(mobile,"false");
                        Intent ai=new Intent(UserProfile.this,MainActivity.class);
                        startActivity(ai);
                        dialogInterface.cancel();
                    }
                });
                alert.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a=new Intent(UserProfile.this,MainActivity.class);
        startActivity(a);
    }
}