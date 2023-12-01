package com.hamidrezababaei.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CheckList_Items extends AppCompatActivity {
    TextView textView ;
    FloatingActionButton addFab_item;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list_items);
        //set checkList id
        Intent intent = getIntent();
        String id_1 = intent.getStringExtra("CHECKLISTID");
        id = Integer.parseInt(id_1);

        // Text View
        textView = findViewById(R.id.textView_add_Item);

        // FloatingAction Button
        addFab_item = findViewById(R.id.floatingActionButton_add_Checklist_item);
        addFab_item.setOnClickListener(view ->{
            Intent items_create = new Intent(CheckList_Items.this , Items_create.class);
            items_create.putExtra("CheckListId" , id_1);
            startActivity(items_create);
        });

        //Recycler View
          LinearLayout layout = findViewById(R.id.checkList_Linear);
        new Items_Ui_Refresher(this, getLayoutInflater(), layout , id).start();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LinearLayout layout = findViewById(R.id.checkList_Linear);
        new Items_Ui_Refresher(this, getLayoutInflater(), layout , id).start();
    }
}