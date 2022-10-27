package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import model.OrderModel;

public class OrderHistory extends AppCompatActivity {
    ArrayList orderData;
    ArrayList<OrderModel> orders;
    ListView myOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        DbForUser db=new DbForUser(OrderHistory.this);
        myOrders=findViewById(R.id.orderHistoryList);
        SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
        String mobile=sp.getString("mobile","no");
        db.getOrders(mobile);
        orderData=db.orderHistory;
        System.out.println("ORDER HISTORY: "+orderData);
        orders=new ArrayList<OrderModel>();
        for(int i=0;i<orderData.size();i++){
            System.out.println("MY ORDERS: "+orderData.get(i));
            ArrayList a= (ArrayList) orderData.get(i);
            String product=a.get(0).toString();
            String Date=a.get(1).toString();
            System.out.println("MY ORDERS: "+product+","+Date);
            orders.add(new OrderModel(product,Date));
        }
        MyOrderAdapter la=new MyOrderAdapter(OrderHistory.this,R.layout.my_orders_layout,orders);
        myOrders.setAdapter(la);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(OrderHistory.this,UserProfile.class);
        startActivity(i);
    }
}