package com.example.ekart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import model.OrderModel;
import model.ProductModel;

public class MyOrderAdapter extends ArrayAdapter<OrderModel> {

    Context context;
    int resource;
    List<OrderModel> myOrders;
    public MyOrderAdapter(@NonNull Context context, int resource, @NonNull List<OrderModel> myOrders) {
        super(context, resource, myOrders);
        this.context=context;
        this.resource=resource;
        this.myOrders=myOrders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator=LayoutInflater.from(context);
        View  view=inflator.inflate(resource,null);

        TextView product=view.findViewById(R.id.product);
        TextView date=view.findViewById(R.id.date);

        OrderModel myModel=myOrders.get(position);
        product.setText(myModel.getProducts());
        date.setText("ON: "+myModel.getOrderDate());

        return view;
    }
}
