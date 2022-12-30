package com.example.e_foodcourtapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.e_foodcourtapp.Domain.PopularDomain;
import com.example.e_foodcourtapp.Helper.ManagementCart;
import com.example.e_foodcourtapp.R;

public class DetailActivity extends AppCompatActivity {
    private TextView addCartBtn;
    private TextView titleText, feeText, description, numberOrder;
    private ImageView plusBtn, minusBtn, picFood;
    private PopularDomain object;
    int numberOrderTxt = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (PopularDomain) getIntent().getSerializableExtra("object");

        int resId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(resId)
                .into(picFood);

        titleText.setText(object.getTitle());
        feeText.setText("$" + object.getFee());
        description.setText(object.getDesc());
        numberOrder.setText(String.valueOf(numberOrderTxt));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrderTxt = numberOrderTxt + 1;
                numberOrder.setText(String.valueOf(numberOrderTxt));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrderTxt>1){
                    numberOrderTxt = numberOrderTxt - 1;
                }
                numberOrder.setText(String.valueOf(numberOrderTxt));
            }
        });

        addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrderTxt);
                managementCart.insertFood(object);
            }
        });
    }

    private void initView() {
        addCartBtn = findViewById(R.id.addCartBtn);
        titleText = findViewById(R.id.titleText);
        feeText = findViewById(R.id.feeEachItem);
        description = findViewById(R.id.description);
        numberOrder = findViewById(R.id.numberOrder);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.picFood);
    }
}