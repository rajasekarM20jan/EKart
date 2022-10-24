package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductViewer extends AppCompatActivity {
    ImageView productImage;
    TextView productName,productPrice,productDescription,productRating,productBrand,productCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_viewer);
        productImage=findViewById(R.id.productImage);
        productName=findViewById(R.id.productName);
        productPrice=findViewById(R.id.productPrice);
        productDescription=findViewById(R.id.productDescription);
        productRating=findViewById(R.id.productRating);
        productBrand=findViewById(R.id.productBrand);
        productCategory=findViewById(R.id.productCategory);
        Intent i=getIntent();
        String title=i.getStringExtra("title");
        System.out.println("My Data: "+title);
        DbForProducts db=new DbForProducts(ProductViewer.this);
        db.getImages(title);
        ArrayList arr=db.image;
        System.out.println("My Data"+arr);
        db=new DbForProducts(ProductViewer.this);
        db.getData(title);
        System.out.println("My Data"+db.id+db.title+db.price+db.rating);
        productName.setText(db.title);
        productPrice.setText("$\t\t"+db.price+"/-");
        productDescription.setText("\t\t"+db.description);
        productRating.setText(db.rating);
        productBrand.setText(db.brand);
        productCategory.setText(db.category);


    }
}