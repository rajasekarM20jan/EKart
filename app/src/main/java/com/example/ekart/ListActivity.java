package com.example.ekart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    FloatingActionButton filter;
    ConstraintLayout filterLayout;
    RadioGroup radioGroupFilter;
    RadioButton smartPhonesFilter,laptopsFilter,fragrancesFilter,skincareFilter,groceriesFilter,home_decorationFilter,viewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mySearch=findViewById(R.id.mySearch);
        myList=findViewById(R.id.productList);
        filter=findViewById(R.id.filterNavigator);
        filterLayout=findViewById(R.id.filterLayout);
        radioGroupFilter=findViewById(R.id.radioGroupFilter);
        smartPhonesFilter=findViewById(R.id.smartPhonesFilter);
        laptopsFilter=findViewById(R.id.laptopsFilter);
        fragrancesFilter=findViewById(R.id.fragrancesFilter);
        skincareFilter=findViewById(R.id.skincareFilter);
        groceriesFilter=findViewById(R.id.groceriesFilter);
        home_decorationFilter=findViewById(R.id.decorsFilter);
        viewAll=findViewById(R.id.allFilter);
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

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterLayout.setVisibility(View.VISIBLE);
            }
        });


                radioGroupFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checked) {
                        switch (checked){
                            case R.id.smartPhonesFilter:{
                                System.out.println("My Filter is SmartPhones");
                                Intent i=new Intent(ListActivity.this,ListActivity.class);
                                i.putExtra("values","smartphones");
                                startActivity(i);
                                filterLayout.setVisibility(View.GONE);
                                break;
                            }
                            case R.id.laptopsFilter:{
                                System.out.println("My Filter is laptops");
                                Intent i=new Intent(ListActivity.this,ListActivity.class);
                                i.putExtra("values","laptops");
                                startActivity(i);
                                filterLayout.setVisibility(View.GONE);
                                break;
                            }
                            case R.id.fragrancesFilter:{
                                System.out.println("My Filter is fragrances");
                                Intent i=new Intent(ListActivity.this,ListActivity.class);
                                i.putExtra("values","fragrances");
                                startActivity(i);
                                filterLayout.setVisibility(View.GONE);
                                break;
                            }
                            case R.id.skincareFilter:{
                                System.out.println("My Filter is skincare");
                                Intent i=new Intent(ListActivity.this,ListActivity.class);
                                i.putExtra("values","skincare");
                                startActivity(i);
                                filterLayout.setVisibility(View.GONE);
                                break;
                            }
                            case R.id.groceriesFilter:{
                                System.out.println("My Filter is groceries");
                                Intent i=new Intent(ListActivity.this,ListActivity.class);
                                i.putExtra("values","groceries");
                                startActivity(i);
                                filterLayout.setVisibility(View.GONE);
                                break;
                            }
                            case R.id.decorsFilter:{
                                System.out.println("My Filter is decors");
                                Intent i=new Intent(ListActivity.this,ListActivity.class);
                                i.putExtra("values","home-decoration");
                                startActivity(i);
                                filterLayout.setVisibility(View.GONE);
                                break;
                            }
                            case R.id.allFilter:{
                                System.out.println("My Filter is all");
                                Intent i=new Intent(ListActivity.this,ListActivity.class);
                                i.putExtra("values","all");
                                startActivity(i);
                                filterLayout.setVisibility(View.GONE);
                                break;
                            }
                        }
                    }
                });


    }



}