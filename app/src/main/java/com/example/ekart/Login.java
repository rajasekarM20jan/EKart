package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import controller.controllerLogin;

public class Login extends AppCompatActivity {
    EditText etForMail,etForPassword;
    Button loginButton;
    TextView linkForSignUp;
    FloatingActionButton back;
    DbForUser db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        back=findViewById(R.id.backInLogin);
        etForMail=findViewById(R.id.etForMail);
        etForPassword=findViewById(R.id.etForPassword);
        loginButton=findViewById(R.id.loginButton);
        linkForSignUp=findViewById(R.id.linkForSignup);

        linkForSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettoSignUp();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etForMail.length()!=0){
                    if(etForPassword.length()!=0){
                        gettoDb();
                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(Login.this,MainActivity.class);
                startActivity(a);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent a=new Intent(Login.this,MainActivity.class);
        startActivity(a);
    }

    void gettoSignUp(){
        Intent i=new Intent(this,Register.class);
        startActivity(i);
    }
    public void error(){
        etForMail.setError("Invalid Credentials");
        etForPassword.setError("Invalid Credentials");
    }
    public void dashboard(){
        SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
        String mobile=sp.getString("mobile","no");
        System.out.println("ERROR AT MOBILE: "+mobile);
        db=new DbForUser(Login.this);
        db.updateData(sp.getString("mobile","no"),"true");
        Intent i=new Intent(Login.this,UserProfile.class);
        startActivity(i);
    }
    public void gettoDb(){
        db=new DbForUser(Login.this);
        String name=String.valueOf(etForMail.getText());
        String pass=String.valueOf(etForPassword.getText());
        System.out.println("MyDataTransfer: "+name+pass);
        db.verify(name,pass);
        String user=db.userFound;
        if(user.equals("true")){
            String userMobile=db.mobileNumber;
            String password=db.password;
            System.out.println("ERROR AT CL");
            controllerLogin c=new controllerLogin(Login.this);
            System.out.println("ERROR AT CLV");
            c.verifyData(userMobile,password,etForMail.getText().toString(),etForPassword.getText().toString());
        }else{
            etForMail.setError("Invalid Credentials");
        }
    }
}