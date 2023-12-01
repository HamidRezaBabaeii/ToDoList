package com.hamidrezababaei.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryDetails extends AppCompatActivity {
    TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);


        txtview = findViewById(R.id.textViewCategoryDetails);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        Log.e("Category :" , category );
        txtview.setText(category);
        LinearLayout layout = findViewById(R.id.itemsCategory);
        new CategoryRefresher(this, getLayoutInflater(), layout , category).start();
    }
}