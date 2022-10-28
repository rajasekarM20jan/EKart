package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.AddressModel;

public class SavedAddress extends AppCompatActivity {
    ListView addressList;
    FloatingActionButton back;
    ArrayList products;

    ArrayList<AddressModel> savedAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_address);
        addressList=findViewById(R.id.addressList);
        back=findViewById(R.id.backInSA);
        Intent intent=getIntent();
        products=intent.getStringArrayListExtra("products");
        savedAddress=new ArrayList<>();
        SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
        String mobile=sp.getString("mobile","no");
        DbForUser db=new DbForUser(SavedAddress.this);
        db.getAddress(mobile);
        ArrayList arr=db.savedAddress;
        for (int i=0;i<arr.size();i++){
            ArrayList a1=(ArrayList) arr.get(i);
            String myMobile= a1.get(0).toString();
            String myAddress=a1.get(1).toString();

            savedAddress.add(new AddressModel(myMobile,myAddress));

        }
        MyAddressAdapter adapter=new MyAddressAdapter(SavedAddress.this,R.layout.my_address_layout,savedAddress);
        addressList.setAdapter(adapter);

        addressList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String address=savedAddress.get(position).getAddress();
                System.out.println("MY SAVED ADDRESS: "+address);
                Intent ai=new Intent(SavedAddress.this,BuyNow.class);
                ai.putExtra("products",products);
                ai.putExtra("address",address);
                startActivity(ai);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBack();
            }
        });
    }

    @Override
    public void onBackPressed() {
        getBack();
    }
    void getBack(){
        Intent i=new Intent(SavedAddress.this,Cart.class);
        startActivity(i);
    }
}