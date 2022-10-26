package com.example.ekart;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CategoryFragment extends Fragment {
    View view;
    Button mobiles,laptops,fragrances,skincare,groceries,decors;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_category, container, false);

        mobiles=view.findViewById(R.id.mobiles);
        laptops=view.findViewById(R.id.laptops);
        fragrances=view.findViewById(R.id.fragrances);
        skincare=view.findViewById(R.id.skincare);
        groceries=view.findViewById(R.id.groceries);
        decors=view.findViewById(R.id.decors);

        mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList("smartphones");
            }
        });
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList("laptops");
            }
        });
        fragrances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList("fragrances");
            }
        });

        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList("groceries");
            }
        });
        skincare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList("skincare");
            }
        });
        decors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList("home-decoration");
            }
        });

        return view;
    }
    void getList(String value){
        Intent i=new Intent(getActivity(),ListActivity.class);
        i.putExtra("values",value);
        startActivity(i);
    }


}