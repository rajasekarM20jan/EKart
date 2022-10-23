package com.example.ekart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String resultData;
    BottomNavigationView btmView;
    DbForProducts dbClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btmView=findViewById(R.id.bottomNav);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout,new HomeFragment()).commit();
        btmView.setSelectedItemId(R.id.homeButton);

        btmView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment frag=null;
                switch (item.getItemId()){
                    case R.id.homeButton:{
                        frag=new HomeFragment();
                        break;
                    }
                    case R.id.profileButton:{
                        frag=new ProfileFragment();
                        break;
                    }
                    case R.id.categories:{
                        frag=new CategoryFragment();
                        break;
                    }
                    case R.id.cartButton:{
                        frag=new CartFragment();
                        break;
                    }
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout,frag).commit();

                return true;
            }
        });


        OkHttpClient myClient=new OkHttpClient();
        String url="https://dummyjson.com/products";

        Request fetchData= new Request.Builder().url(url).build();
        myClient.newCall(fetchData).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("My Response: Error");
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    resultData=response.body().string();
                }
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject obj=new JSONObject(resultData);
                            JSONArray arr=obj.getJSONArray("products");
                            dbClass=new DbForProducts(MainActivity.this);
                            dbClass.getCount();
                            int a=dbClass.count;
                            System.out.println("MY COUNT: "+a);
                            for(int i=a;i<arr.length();i++){
                                JSONObject jerry=(JSONObject) arr.get(i);
                                String id= Integer.toString((Integer) jerry.get("id")) ;
                                String title=(String) jerry.get("title");
                                String description=(String) jerry.get("description");
                                String price=Integer.toString((Integer) jerry.get("price"));
                                String discountPercentage=Double.toString((Double)jerry.get("discountPercentage"));
                                String rating=Double.toString(Double.parseDouble(jerry.get("rating").toString()));
                                String stock=Integer.toString((Integer) jerry.get("stock"));
                                String brand=(String) jerry.get("brand");
                                String category=(String) jerry.get("category");
                                String thumbnail=(String) jerry.get("thumbnail");
                                dbClass=new DbForProducts(MainActivity.this);
                                dbClass.insert(id,title,description,price,discountPercentage,rating,stock,brand,category,thumbnail);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                });
            }
        });


    }
}