package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BuyNow extends AppCompatActivity {
    ArrayList products;
    String address;
    String order;
    String product;
    TextView productText,productPrice,total,addressOfUser;
    Button confirmPurchase,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now);
        productText=findViewById(R.id.productText);
        productPrice=findViewById(R.id.productTextPrice);
        total=findViewById(R.id.totalPrice);
        addressOfUser=findViewById(R.id.address);
        confirmPurchase=findViewById(R.id.confirmPurchase);
        cancel=findViewById(R.id.cancelButton);

        Intent intent=getIntent();
        address=intent.getStringExtra("address");
        products=intent.getStringArrayListExtra("products");
        addressOfUser.setText(getString(R.string.address_of_delivery)+address);
        int count=1;
        int totalAmount=0;
        for(int i=0;i<products.size();i++){
            count=count+i;
            productText.setText(productText.getText()+"\n"+ count +"\t"+String.valueOf(products.get(i))+"\n");
            DbForProducts db=new DbForProducts(BuyNow.this);
            db.getData(String.valueOf(products.get(i)));
            String price=db.price;
            productPrice.setText(productPrice.getText()+"\n\t"+price+"\n");
            int a1=Integer.parseInt(price);
            totalAmount=totalAmount+a1;
        }
        total.setText("Total :\t\t"+totalAmount);


        confirmPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sd=new SimpleDateFormat("dd/MM/y  hh:mm:ss");
                Date d=new Date();
                String OrderDate= sd.format(d);
                SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
                String mobile=sp.getString("mobile","no");
                System.out.println("MY PRODUCTS: "+product);
                DbForUser db=new DbForUser(BuyNow.this);
                db.deleteCart();
                db.insertOrders(mobile, String.valueOf(products),OrderDate);
                Intent i=new Intent(BuyNow.this,ThankYouPage.class);
                startActivity(i);
            }
        });

    }
}