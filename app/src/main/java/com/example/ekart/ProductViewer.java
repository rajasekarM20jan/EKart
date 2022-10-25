package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductViewer extends AppCompatActivity {
    ViewPager productImage;
    TextView productName,productPrice,productDescription,productRating,productBrand,productCategory;
    ArrayList arr;
    String[] url;
    Object[] url1;
    DbForProducts db;

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
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        System.out.println("My Data: "+title);
        db=new DbForProducts(ProductViewer.this);
        db.getImages(title);
        arr=db.image;
        System.out.println("My Data"+arr);
        db=new DbForProducts(ProductViewer.this);
        db.getData(title);
        System.out.println("My Data ; "+db.id+db.title+db.price+db.rating);
        productName.setText(db.title);
        productPrice.setText("$\t\t"+db.price+"/-");
        productDescription.setText("\t\t"+db.description);
        productRating.setText(db.rating);
        productBrand.setText(db.brand);
        productCategory.setText(db.category);
        System.out.println("My Images:"+arr.get(1));
        ArrayList image= (ArrayList) arr.get(1);
        url1= image.toArray();
        url=new String[url1.length];
        for(int i=0;i<url1.length;i++){
            url[i]=(String) url1[i];
        }
        System.out.println("My Images2:"+url[0]);
        ViewPagerAdapter vpAdapter=new ViewPagerAdapter(this,url);
        productImage.setAdapter(vpAdapter);

    }
}