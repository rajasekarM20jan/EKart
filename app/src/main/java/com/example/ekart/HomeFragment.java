package com.example.ekart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HomeFragment extends Fragment {
    View view;
    DbForProducts db;
    ImageView iphoneX,macBook,huawei,surfaceBook,oppoF19;
    ImageView iphone9,brownPerfume,treeOil,keyHolder;
    Button listButton;
    String url;



    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        iphoneX=view.findViewById(R.id.iphoneX);
        macBook=view.findViewById(R.id.macBook);
        huawei=view.findViewById(R.id.huawei);
        surfaceBook=view.findViewById(R.id.surfaceBook);
        oppoF19=view.findViewById(R.id.oppoF19);
        iphone9=view.findViewById(R.id.iphone9);
        brownPerfume=view.findViewById(R.id.brownPerfume);
        treeOil=view.findViewById(R.id.treeOil);
        keyHolder=view.findViewById(R.id.keyHolder);
        listButton=view.findViewById(R.id.listButton);
        db=new DbForProducts(getActivity());
        OkHttpClient myClient=new OkHttpClient();
        String url1="https://dummyjson.com/products";

        Request fetchData= new Request.Builder().url(url1).build();
        myClient.newCall(fetchData).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("My Images Failed");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String resultData=response.body().string();
                try{

                    JSONObject ab=new JSONObject(resultData);
                    JSONArray imgs=ab.getJSONArray("products");
                    db=new DbForProducts(getActivity());
                    db.getCount();
                    int a=db.count;
                    for(int i=a;i<imgs.length();i++){
                        JSONObject jerry=(JSONObject) imgs.get(i);
                        String title=(String) jerry.get("title");
                        JSONArray image=(JSONArray) jerry.get("images");
                        for(int j=0;j<image.length();j++){
                            String jobj= (String) image.get(j);
                            db=new DbForProducts(getActivity());
                            db.insertImage(title,jobj);
                        }


                    }
                }catch (Exception e){
                    System.out.println("My Images Failed catch");
                }

            }
        });
        try {
            db = new DbForProducts(getActivity());
            db.getThumbnail("iPhone X");
            url = db.thumbnail;
            Picasso.with(view.getContext()).load(url).into(iphoneX);

            db = new DbForProducts(getActivity());
            db.getThumbnail("MacBook Pro");
            url = db.thumbnail;
            Picasso.with(view.getContext()).load(url).into(macBook);

            db = new DbForProducts(getActivity());
            db.getThumbnail("Huawei P30");
            url = db.thumbnail;
            Picasso.with(view.getContext()).load(url).into(huawei);

            db = new DbForProducts(getActivity());
            db.getThumbnail("Microsoft Surface Laptop 4");
            url = db.thumbnail;
            Picasso.with(view.getContext()).load(url).into(surfaceBook);

            db = new DbForProducts(getActivity());
            db.getThumbnail("OPPOF19");
            url = db.thumbnail;
            Picasso.with(view.getContext()).load(url).into(oppoF19);


            db = new DbForProducts(getActivity());
            db.getThumbnail("iPhone 9");
            url = db.thumbnail;
            Picasso.with(view.getContext()).load(url).into(iphone9);

            db = new DbForProducts(getActivity());
            db.getThumbnail("Brown Perfume");
            url = db.thumbnail;
            Picasso.with(view.getContext()).load(url).into(brownPerfume);

            db = new DbForProducts(getActivity());
            db.getThumbnail("Tree Oil 30ml");
            url = db.thumbnail;
            Picasso.with(view.getContext()).load(url).into(treeOil);

            db = new DbForProducts(getActivity());
            db.getThumbnail("Key Holder");
            url = db.thumbnail;
            Picasso.with(view.getContext()).load(url).into(keyHolder);
        }catch(Exception e){
            Toast.makeText(getContext(), "Check Your Internet connection and try again", Toast.LENGTH_SHORT).show();
        }



        iphoneX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="iPhone X";
                getNextPage(title);
            }
        });

        macBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="MacBook Pro";
                getNextPage(title);
            }
        });

        huawei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="Huawei P30";
                getNextPage(title);
            }
        });

        surfaceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="Microsoft Surface Laptop 4";
                getNextPage(title);
            }
        });

        oppoF19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="OPPOF19";
                getNextPage(title);
            }
        });

        iphone9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="iPhone 9";
                getNextPage(title);
            }
        });


        brownPerfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="Brown Perfume";
                getNextPage(title);
            }
        });


        treeOil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="Tree Oil 30ml";
                getNextPage(title);
            }
        });

        keyHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="Key Holder";
                getNextPage(title);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            getList();
            }
        });

        return view;
    }
    void getNextPage(String title){
        Intent i=new Intent(getActivity(),ProductViewer.class);
        i.putExtra("title",title);
        startActivity(i);
    }
    void getList(){
        Intent i=new Intent(getActivity(),ListActivity.class);
        i.putExtra("values","all");
        startActivity(i);
    }
}