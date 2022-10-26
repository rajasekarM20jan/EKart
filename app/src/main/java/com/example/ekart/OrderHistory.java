package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

import model.OrderModel;

public class OrderHistory extends AppCompatActivity {
    ArrayList orderData;
    ArrayList<OrderModel> orders;

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
        orders=new ArrayList<OrderModel>();
        for(int i=0;i<orderData.size();i++){
            ArrayList order=(ArrayList) orderData.get(i);
            String orderDate=order.get(1).toString();
            String product=(String) order.get(0);
            System.out.println("Order Date: "+orderDate);
            System.out.println("Order Date: "+product);

        }



    }
}