package com.example.e_foodcourtapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.e_foodcourtapp.Adapter.PopularAdapter;
import com.example.e_foodcourtapp.Domain.PopularDomain;
import com.example.e_foodcourtapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewPopularList;
    private ImageView detailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPopular();
        bottomNav();
    }

    private void bottomNav(){
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<PopularDomain> popularList = new ArrayList<>();
        popularList.add(new PopularDomain("karage","Karage","Japanese deep fried chicken.",3.0));
        popularList.add(new PopularDomain("edamame","Edamame","Boiled or steamed of immature\nsoybeans.",2.0));
        popularList.add(new PopularDomain("gyukatsu","Gyukatsu","Tender beef steak that coated in\nbreadcrumbs and deep fried.",5.0));
        popularList.add(new PopularDomain("gyoza","Gyoza","Chinese dumplings commonly.",3.0));
        popularList.add(new PopularDomain("sushi","Sushi","Japanese dish of prepared\nvinegared.",6.0));
        popularList.add(new PopularDomain("ramen","Ramen","Japanese noodle dish, served\nwith broth.",4.0));
        popularList.add(new PopularDomain("bento","Bento","Japanese iteration of a\nhome-packed meal.",4.0));

        adapter = new PopularAdapter(popularList);
        recyclerViewPopularList.setAdapter(adapter);
    }

}