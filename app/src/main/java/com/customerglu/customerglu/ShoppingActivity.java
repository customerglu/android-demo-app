package com.customerglu.customerglu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.customerglu.customerglu.adapter.GroceryAdapter;


public class ShoppingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GroceryAdapter groceryAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        init();
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    private void init() {

        Intent intent = getIntent();
        Uri data = intent.getData();
        recyclerView = findViewById(R.id.shopping);
        groceryAdapter = new GroceryAdapter(getApplicationContext());
        GridLayoutManager groceryManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(groceryManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(groceryAdapter);

    }
}
