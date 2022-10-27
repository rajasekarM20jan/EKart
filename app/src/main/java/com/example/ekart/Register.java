package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Register extends AppCompatActivity {
    EditText signUpName,userName,mailId,mobile,signupPassword,confirmPassword;
    Button registerButton;
    FloatingActionButton back;
    TextView linkForSignIn;
    int result;
    DbForUser dbForUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        back=findViewById(R.id.backInRegister);
        signUpName=findViewById(R.id.signupName);
        userName=findViewById(R.id.userName);
        mailId=findViewById(R.id.mailId);
        mobile=findViewById(R.id.mobile);
        signupPassword=findViewById(R.id.signupPassword);
        confirmPassword=findViewById(R.id.confirmPassword);
        registerButton=findViewById(R.id.registerButton);
        linkForSignIn=findViewById(R.id.linkForSignIn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUser();
            }
        });
        linkForSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLogin();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLogin();
            }
        });

    }
    int newUser() {
        if (signUpName.length() == 0) {

            result = 0;
            getResult();
            return result;

        }
        if (userName.length() == 0) {
            result = 1;
            getResult();
            return result;

        }
        if (mailId.length() == 0) {
            result = 2;
            getResult();
            return result;
        }
        if (mobile.length() == 0) {
            result = 3;
            getResult();
            return result;
        }
        if (signupPassword.length() < 6 && confirmPassword.length()< 6) {
            result = 4;
            getResult();
            return result;
        }
        if(String.valueOf(signupPassword.getText()).equals(String.valueOf(confirmPassword.getText()))){
            result=5;
            getResult();
            return result;
        }else{
            signupPassword.setError("Passwords doesn't match");
            confirmPassword.setError("Passwords doesn't match");
            result=4;
        }
        return result;
    }
    void getResult(){
        switch (result){
            case 0:{
                signUpName.setError("Field is Empty");
                break;
            }
            case 1:{
                userName.setError("Field is Empty");
                break;
            }
            case 2:{
                mailId.setError("Field is Empty");
                break;
            }
            case 3:{
                mobile.setError("Field is Empty");
                break;
            }
            case 4:{
                signupPassword.setError("Field is Empty");
                confirmPassword.setError("Field is Empty");
                break;
            }
            case 5:{
                String name=String.valueOf(signUpName.getText());
                String uname=String.valueOf(userName.getText());
                String mail=String.valueOf(mailId.getText());
                String mobileNumber=String.valueOf(mobile.getText());
                String password=String.valueOf(confirmPassword.getText());
                String login="false";
                System.out.println("My Data: "+name+uname+mail+mobileNumber+password+login);

                dbForUser=new DbForUser(Register.this);
                dbForUser.verify(mail,password);
                String res=dbForUser.userFound;
                if(res.equals("false")){
                    dbForUser.verify(mobileNumber,password);
                    String ac= dbForUser.userFound;
                    if(ac.equals("false")){
                        dbForUser.addNewUser(name,uname,mail,password,mobileNumber,login);
                        getLogin();
                        break;
                    }else{
                        AlertDialog.Builder a=new AlertDialog.Builder(Register.this);
                        a.setMessage("The mobile number is Already Taken");
                        a.setCancelable(false);
                        a.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mobile.setText("");
                                dialogInterface.cancel();
                            }
                        });a.show();
                    }
                }
                else{
                    AlertDialog.Builder a=new AlertDialog.Builder(Register.this);
                    a.setMessage("The mail ID is Already Taken");
                    a.setCancelable(false);
                    a.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mailId.setText("");
                            dialogInterface.cancel();
                        }
                    });a.show();
                }

            }
            default:{
                signUpName.setError("Enter The Credentials");
                break;
            }
        }

    }

    @Override
    public void onBackPressed() {
        getLogin();
    }

    void getLogin(){
        Intent i=new Intent(Register.this,Login.class);
        startActivity(i);

    }
}