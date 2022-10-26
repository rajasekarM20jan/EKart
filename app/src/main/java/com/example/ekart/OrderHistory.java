package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {
    ArrayList orderData;
    String[] products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        DbForUser db=new DbForUser(OrderHistory.this);
        SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
        String mobile=sp.getString("mobile","no");
        db.getOrders(mobile);
        orderData=db.orderHistory;
        System.out.println("ORDER HISTORY: "+orderData);

    }
}