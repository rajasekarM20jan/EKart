package com.example.ekart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BuyingPage extends AppCompatActivity {
    EditText etForDoor,etForStreet,etForCity,etForState,etForPin,etForLandmark;
    Button next,savedAddress;
    FloatingActionButton back;
    String door,street,city,state,pin,landmark;
    ArrayList products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying_page);
        etForDoor=findViewById(R.id.etForDoorNo);
        etForStreet=findViewById(R.id.etForStreet);
        etForCity=findViewById(R.id.etForCity);
        etForState=findViewById(R.id.etForState);
        etForPin=findViewById(R.id.etForPin);
        etForLandmark=findViewById(R.id.etForLandMark);
        savedAddress=findViewById(R.id.addressListButton);
        next=findViewById(R.id.nextInBuy);
        back=findViewById(R.id.backInBuyingPage);
        Intent i=getIntent();
        products=i.getStringArrayListExtra("products");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getConfirmation();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart=new Intent(BuyingPage.this,Cart.class);
                startActivity(cart);
            }
        });
        savedAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent address=new Intent(BuyingPage.this,SavedAddress.class);
                address.putExtra("products",products);
                startActivity(address);
            }
        });

    }
    void getConfirmation(){
        if(etForDoor.length()==0){
            AlertDialog.Builder alert=new AlertDialog.Builder(BuyingPage.this);
            alert.setMessage(getString(R.string.door_alert));
            alert.setCancelable(false);
            alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alert.show();
        }else if(etForStreet.length()==0){
            AlertDialog.Builder alert=new AlertDialog.Builder(BuyingPage.this);
            alert.setMessage(getString(R.string.street_alert));
            alert.setCancelable(false);
            alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alert.show();
        }else if(etForCity.length()==0){
            AlertDialog.Builder alert=new AlertDialog.Builder(BuyingPage.this);
            alert.setMessage(getString(R.string.city_alert));
            alert.setCancelable(false);
            alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alert.show();
        }else if(etForState.length()==0){
            AlertDialog.Builder alert=new AlertDialog.Builder(BuyingPage.this);
            alert.setMessage(getString(R.string.state_alert));
            alert.setCancelable(false);
            alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alert.show();
        }else if(etForPin.length()!=6){
            AlertDialog.Builder alert=new AlertDialog.Builder(BuyingPage.this);
            alert.setMessage(getString(R.string.pin_alert));
            alert.setCancelable(false);
            alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alert.show();
        }else if(etForLandmark.length()==0){
            AlertDialog.Builder alert=new AlertDialog.Builder(BuyingPage.this);
            alert.setMessage(getString(R.string.landmark_alert));
            alert.setCancelable(false);
            alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alert.show();
        }else{
            door=etForDoor.getText().toString();
            street=etForStreet.getText().toString();
            city=etForCity.getText().toString();
            state=etForState.getText().toString();
            pin=etForPin.getText().toString();
            landmark=etForLandmark.getText().toString();
            nextPage();
        }
    }
    void nextPage(){
        System.out.println("My Address: "+door+", "+street+", "+city+", "+state+", "+pin+". Near "+landmark);
        String address=(door+", "+street+", "+city+", \n"+state+", "+pin+". \nLandMark: "+landmark);
        SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
        String mobile=sp.getString("mobile","no");

        DbForUser db=new DbForUser(BuyingPage.this);
        db.insertAddress(mobile,address);
        Intent ai=new Intent(BuyingPage.this,BuyNow.class);
        ai.putExtra("products",products);
        ai.putExtra("address",address);
        startActivity(ai);
    }

    @Override
    public void onBackPressed() {
        Intent cart=new Intent(BuyingPage.this,Cart.class);
        startActivity(cart);
    }
}