package com.example.ekart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import model.CartModel;

public class Cart extends AppCompatActivity {
    String title,thumbnail,price;
    ArrayList cartData;
    ListView cartList;
    Button proceedToBuy;
    ArrayList<CartModel> myArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartList=findViewById(R.id.cartList);
        proceedToBuy=findViewById(R.id.proceedToBuy);
        cartData=new ArrayList<>();
        SharedPreferences sp=getSharedPreferences("MyPref",MODE_PRIVATE);
        String mobile=sp.getString("mobile","no");
        DbForUser dbForUser=new DbForUser(Cart.this);
        myArray=new ArrayList<>();
        dbForUser.getCart(mobile);
        cartData=dbForUser.cart;
        System.out.println("MY CART"+cartData);

        for(int i=0;i<cartData.size();i++){
            DbForProducts db=new DbForProducts(Cart.this);
            db.getData(cartData.get(i).toString());
            title=db.title;
            thumbnail=db.thumbnail;
            price=db.price;
            myArray.add(new CartModel(title,thumbnail,price));
        }
        MyCartAdapter mcAdapter=new MyCartAdapter(Cart.this,R.layout.my_custom_cart,myArray);
        cartList.setAdapter(mcAdapter);
        cartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String product=myArray.get(position).getTitle();
                System.out.println("product: :"+product);
                deleteProduct(product);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent a=new Intent(Cart.this,MainActivity.class);
        startActivity(a);
    }

    void deleteProduct(String product){
        SharedPreferences sp1=getSharedPreferences("MyPref",MODE_PRIVATE);
        String mobileNumber=sp1.getString("mobile","no");
        AlertDialog.Builder alert=new AlertDialog.Builder(Cart.this);
        alert.setMessage("Are You sure to remove "+product+" from the cart");
        alert.setCancelable(false);
        alert.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alert.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DbForUser dbClass=new DbForUser(Cart.this);
                dbClass.deleteFromCart(mobileNumber,product);
                Toast.makeText(Cart.this, "item deleted from the cart", Toast.LENGTH_SHORT).show();
                Intent ai=new Intent(Cart.this,Cart.class);
                startActivity(ai);
                dialogInterface.cancel();
            }
        });
        alert.show();
    }
}