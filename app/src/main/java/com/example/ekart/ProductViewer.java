package com.example.ekart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductViewer extends AppCompatActivity {
    ViewPager productImage;
    TextView productName,productPrice,productDescription,productRating,productBrand,productCategory;
    ArrayList arr;
    String[] url;
    Object[] url1;
    DbForProducts db;
    Button addToCart;
    FloatingActionButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_viewer);
        back=findViewById(R.id.backInPV);
        productImage=findViewById(R.id.productImage);
        productName=findViewById(R.id.productName);
        productPrice=findViewById(R.id.productPrice);
        productDescription=findViewById(R.id.productDescription);
        productRating=findViewById(R.id.productRating);
        productBrand=findViewById(R.id.productBrand);
        productCategory=findViewById(R.id.productCategory);
        addToCart=findViewById(R.id.cartButton);
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
        System.out.println("My Images2:"+image.size());
        ViewPagerAdapter vpAdapter=new ViewPagerAdapter(this,url);
        productImage.setAdapter(vpAdapter);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
                String mobile=sp.getString("mobile","no");
                DbForUser dbForUser=new DbForUser(ProductViewer.this);
                dbForUser.getData(mobile);
                String login=dbForUser.login;
                System.out.println("My Login: "+login);
                if(login.equals("true")){
                    SharedPreferences sp1=getSharedPreferences("MyPref",MODE_PRIVATE);
                    String mobile1=sp1.getString("mobile","no");
                    String cart=productName.getText().toString();
                    System.out.println("My Kart: "+mobile1+"\t"+cart);
                    DbForUser dbClass=new DbForUser(ProductViewer.this);
                    dbClass.insertCart(mobile,cart);
                    AlertDialog.Builder alert=new AlertDialog.Builder(ProductViewer.this);
                    alert.setTitle("Added To Cart");
                    alert.setMessage(cart+"\t is added to cart");
                    alert.setCancelable(false);
                    alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    alert.show();
                }else{
                    AlertDialog.Builder alert=new AlertDialog.Builder(ProductViewer.this);
                    alert.setMessage(getString(R.string.login_alert_pv));
                    alert.setCancelable(false);
                    alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    alert.show();
                }


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProductViewer.this,MainActivity.class);
                startActivity(i);
            }
        });
    }


}