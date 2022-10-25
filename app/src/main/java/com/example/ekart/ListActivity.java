package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import model.ProductModel;

public class ListActivity extends AppCompatActivity {
    String title,description,price;
    String thumbnail,brand,category;
    ArrayList<ProductModel> productList;
    ListView myList;
    SearchView mySearch;
    ArrayList<ProductModel> myFilter;
    DbForProducts db;
    String values="all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mySearch=findViewById(R.id.mySearch);
        myList=findViewById(R.id.productList);
        productList=new ArrayList<ProductModel>();
        Intent intent=getIntent();
        String ab;
        try{
            ab=intent.getStringExtra("values");
        }
        catch (Exception e){
            ab="all";
        }
        values=ab;
        db=new DbForProducts(ListActivity.this);
        db.getCount();
        int a=db.count;
        System.out.println("My Count"+a);
        for(int i=1;i<=a;i++){
            db=new DbForProducts(ListActivity.this);
            db.getList(String.valueOf(i));
            title=db.title;
            description=db.description;
            price=db.price;
            thumbnail=db.thumbnail;
            brand=db.brand;
            category=db.category;
            productList.add(new ProductModel(title,description,price,thumbnail,category,brand));
        }
        myFilter=new ArrayList<ProductModel>();
        for (ProductModel myFilters: productList) {
            if (values.equals("all")){
                myFilter.add(myFilters);
            }else if(myFilters.getCategory().toLowerCase().equals(values.toLowerCase())){
                myFilter.add(myFilters);

            }
        }
        MyCustomAdapter listAdapt=new MyCustomAdapter(ListActivity.this,R.layout.my_custom_layout,myFilter);
        myList.setAdapter(listAdapt);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i=new Intent(ListActivity.this,ProductViewer.class);
                i.putExtra("title",myFilter.get(position).getTitle());
                startActivity(i);
            }
        });

        mySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<ProductModel> mySearch = new ArrayList<ProductModel>();
                for (ProductModel item : myFilter) {
                    if (item.getTitle().toLowerCase().contains(s.toLowerCase())) {
                        mySearch.add(item);
                    }
                }
                MyCustomAdapter listAdapter = new MyCustomAdapter(ListActivity.this, R.layout.my_custom_layout, mySearch);
                myList.setAdapter(listAdapter);
                myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent i = new Intent(ListActivity.this, ProductViewer.class);
                        i.putExtra("title", mySearch.get(position).getTitle());
                        startActivity(i);
                    }
                });
            return false;
            }
        });
    }

}