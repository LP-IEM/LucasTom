package com.mylittlebusiness.iem.mylittlebusiness.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mylittlebusiness.iem.mylittlebusiness.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setTitle(R.string.cart_title);


    }
}
