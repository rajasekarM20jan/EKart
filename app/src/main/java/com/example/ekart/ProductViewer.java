package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ProductViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_viewer);
        Intent i=getIntent();
        String a=i.getStringExtra("title");
        System.out.println("My Data: "+a);
        DbForProducts db=new DbForProducts(ProductViewer.this);
        db.getImages(a);
        ArrayList arr=db.image;
        System.out.println("My Data"+arr);


    }
}