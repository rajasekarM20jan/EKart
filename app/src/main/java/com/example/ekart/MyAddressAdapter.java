package com.example.ekart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import model.AddressModel;

public class MyAddressAdapter extends ArrayAdapter<AddressModel> {
    Context context;
    int resource;
    List<AddressModel> myAddress;
    AddressModel address;

    public MyAddressAdapter(@NonNull Context context, int resource, @NonNull List<AddressModel> myAddress) {
        super(context, resource, myAddress);
        this.context=context;
        this.resource=resource;
        this.myAddress=myAddress;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator=LayoutInflater.from(context);
        View  view=inflator.inflate(resource,null);

        TextView myMobile=view.findViewById(R.id.savedAddressMobile);
        TextView mySavedAddress=view.findViewById(R.id.savedAddressText);

        address=myAddress.get(position);
        myMobile.setText("MOBILE : "+address.getMobile());
        mySavedAddress.setText("Address : "+address.getAddress());

        return view;
    }
}
