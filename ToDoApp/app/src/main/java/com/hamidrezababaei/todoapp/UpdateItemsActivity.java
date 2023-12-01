package com.hamidrezababaei.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hamidrezababaei.todoapp.db.Items;
import com.hamidrezababaei.todoapp.db.ItemsDAO;
import com.hamidrezababaei.todoapp.db.TodoListDatabase;

public class UpdateItemsActivity extends AppCompatActivity {

    TextView add_items_textview , name_textview , priority_textview , description_textview , category_textview ;
    EditText name_EditText , description_EditText ;
    Spinner priority_spinner , category_spinner , date_spinner , hour_spinner , minute_spinner ;
    ImageView calender_image , watch_image ;
    Button cancel_btn , save_btn ;
    String hour="" , minute ="";
    ItemsDAO itemsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_items);
        TodoListDatabase db = TodoListDatabase.getTodoListDatabase(this);
         itemsDAO = db.getItemsDAO();

        Intent intent = getIntent();
        int id =Integer.parseInt(intent.getStringExtra("id"));
        int checklistID =Integer.parseInt(intent.getStringExtra("checklist_id"));
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");

        add_items_textview = findViewById(R.id.textView_add_Item1);
        name_textview = findViewById(R.id.textView_customDialog_name1);
        priority_textview = findViewById(R.id.textView_customDialog_description1);
        description_textview = findViewById(R.id.textView_itemCr_priority1);
        category_textview = findViewById(R.id.textView_itemCr_category1);

        // Edit Text
        name_EditText = findViewById(R.id.editText_customDialog_name1);
        name_EditText.setText(name);
        description_EditText = findViewById(R.id.editText_customDialog_description1);
        description_EditText.setText(description);

        // Spinners
        priority_spinner = findViewById(R.id.spinner_category1);
        category_spinner = findViewById(R.id.spinner_category1);
        date_spinner = findViewById(R.id.spinner_Date1);
        hour_spinner = findViewById(R.id.spinner_Time_hour1);
        minute_spinner = findViewById(R.id.spinner_Time_minute1);

        // Buttons
        cancel_btn = findViewById(R.id.cancel_btn_customDialog1);
        save_btn = findViewById(R.id.send_btn_customDialog1);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsDAO.updateItem( name_EditText.getText().toString(),
                        description_EditText.getText().toString(),
                        priority_spinner.getSelectedItem().toString(),
                        category_spinner.getSelectedItem().toString(),
                        date_spinner.getSelectedItem().toString(),
                        getTime(), id , checklistID);
                onBackPressed();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // Image views
        calender_image = findViewById(R.id.calender_image1);
        watch_image = findViewById(R.id.watch_image1);

    }

//    private void getHourAndMinute(String time) {
//        char[] get = time.toCharArray();
//        boolean arrived = false;
//        for (int i = 0; i < get.length; i++) {
//            if (get[i] == ':') arrived = true;
//
//            if (arrived == false) {
//                hour += get[i];
//            } else {
//                minute += get[i];
//            }
//        }
//    }

    private String getTime(){
        String hour1 = hour_spinner.getSelectedItem().toString();
        String minute1 = minute_spinner.getSelectedItem().toString();
        String time = hour1+":"+minute1;
        return time;
    }
}