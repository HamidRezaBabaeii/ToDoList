package com.hamidrezababaei.todoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hamidrezababaei.todoapp.db.Items;
import com.hamidrezababaei.todoapp.db.ItemsDAO;
import com.hamidrezababaei.todoapp.db.TodoListDatabase;

public class CategoryRefresher extends Thread {
    Context context;
    LayoutInflater inflater;
    LinearLayout layout;
    ItemsDAO itemsDAO_1;
    public  String category;



    CategoryRefresher(Context context ,LayoutInflater inflater,LinearLayout layout , String category){
        this.context = context;
        this.inflater = inflater;
        this.layout = layout;
        this.category = category;
    }



    @Override
    public void run() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    createlist();
                    sleep(200);
                } catch (Exception e) {

                }
            }
        });
    }

    void createlist() {
        TodoListDatabase db = TodoListDatabase.getTodoListDatabase(context);
        layout.removeAllViews();
        ItemsDAO itemsDAO = db.getItemsDAO();
        itemsDAO_1 = itemsDAO;

        int i = 0;
        for (Items items : itemsDAO.getItems_by_category(category)) {

            View fragment = inflater.inflate(R.layout.items_fragment, layout, false);

            layout.addView(fragment);
            ViewGroup view = (ViewGroup) layout.getChildAt(i++); // i


            CheckBox checkBox = (CheckBox) view.getChildAt(0);

            if (items.getIsChecked()) checkBox.setChecked(true);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!items.getIsChecked()){
                        itemsDAO_1.addCheckedCategory(true , items.getId());
                    }else{
                        itemsDAO_1.addCheckedCategory(false , items.getId());
                    }

                }
            });

            TextView textView = (TextView) view.getChildAt(1);
            textView.setText(items.getName());

            ImageButton imageButton = (ImageButton) view.getChildAt(2);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context , UpdateItemsActivity.class);
                    intent.putExtra("id" , String.valueOf(items.getId()));
                    intent.putExtra("checklist_id" , String.valueOf(items.getId()));
                    intent.putExtra("name" , items.getName());
                    intent.putExtra("description" , items.getDescription());
                    intent.putExtra("category" , items.getCategory());
                    intent.putExtra("priority" , items.getPriority());
                    intent.putExtra("date" , items.getDate());
                    intent.putExtra("time" , items.getTime());
                    context.startActivity(intent);

                }
            });
            ImageButton imageButton1 = (ImageButton) view.getChildAt(3);
            imageButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemsDAO_1.deleteItems(new Items( items.getId()));
                    new CategoryRefresher(context, inflater, layout , items.getCategory()).start();
                }
            });
        }

    }
}
