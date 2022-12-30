package com.example.e_foodcourtapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_foodcourtapp.Adapter.CartAdapter;
import com.example.e_foodcourtapp.Helper.ManagementCart;
import com.example.e_foodcourtapp.Interface.ChangeNumberItem;
import com.example.e_foodcourtapp.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalTxt, emptyTxt, taxTxt, itemFeeTxt;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        calculateCart();
        bottomNav();
        orderBtn();
    }

    private  void bottomNav(){
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void orderBtn(){
        TextView orderBtn = findViewById(R.id.orderBtn);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Success Order Your Food, please wait your order.", Toast.LENGTH_SHORT).show();
                Toast.makeText(CartActivity.this, "Thankyou and Enjoy Your Food!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        totalTxt = findViewById(R.id.total);
        emptyTxt = findViewById(R.id.empty);
        itemFeeTxt = findViewById(R.id.itemFee);
        taxTxt = findViewById(R.id.tax);
        scrollView = findViewById(R.id.scrollView2);
        recyclerViewList = findViewById(R.id.cartRcycler);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managementCart.getListCart(), this, new ChangeNumberItem() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart(){
        double precentTax = 0.2;
        double tax = Math.round((managementCart.getTotalFee() * precentTax) * 100) / 100;
        double itemFee = Math.round(managementCart.getTotalFee() * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax) * 100) / 100;

        itemFeeTxt.setText("$" + itemFee);
        taxTxt.setText("$" + tax);
        totalTxt.setText("$" + total);

    }
}