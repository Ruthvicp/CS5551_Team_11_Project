package com.umkc.team11.smartshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void toShopping(View v)
    {
        Intent redirect = new Intent(Home.this, ShoppingActivity.class);
        startActivity(redirect);
    }
}
