package com.hamidrezababaei.todoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hamidrezababaei.todoapp.db.CheckList;
import com.hamidrezababaei.todoapp.db.CheckListDAO;
import com.hamidrezababaei.todoapp.db.Items;
import com.hamidrezababaei.todoapp.db.ItemsDAO;
import com.hamidrezababaei.todoapp.db.TodoListDatabase;

public class Items_Ui_Refresher extends Thread{
    Context context;
    LayoutInflater inflater;
    LinearLayout layout;
    public int chItemIndex;
    ItemsDAO itemsDAO_1;
    public  int checkListId;
    CheckListDAO checkListDAO_D_E;

    Items_Ui_Refresher(Context context ,LayoutInflater inflater,LinearLayout layout , int checkListId){
        this.context = context;
        this.inflater = inflater;
        this.layout = layout;
        this.checkListId = checkListId;
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
        for (Items items : itemsDAO.getItems_by_checkListId(checkListId)) {
            View fragment = inflater.inflate(R.layout.items_fragment, layout, false);
            layout.addView(fragment);
            ViewGroup view = (ViewGroup) layout.getChildAt(i++); // i

            //CheckBox
            CheckBox checkBox = (CheckBox) view.getChildAt(0);
            if (items.getIsChecked()) checkBox.setChecked(true);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!items.getIsChecked()){
                        itemsDAO_1.addChecked(true , items.getId() , checkListId);
                    }else{
                        itemsDAO_1.addChecked(false , items.getId() , checkListId);
                    }

                }
            });

            //Text name
            TextView textView = (TextView) view.getChildAt(1);
            textView.setText(items.getName());

            //Edite button
            ImageButton imageButton = (ImageButton) view.getChildAt(2);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context , UpdateItemsActivity.class);
                    intent.putExtra("id" , String.valueOf(items.getId()));
                    intent.putExtra("checklist_id" , String.valueOf(checkListId));
                    intent.putExtra("name" , items.getName());
                    intent.putExtra("description" , items.getDescription());
                    intent.putExtra("category" , items.getCategory());
                    intent.putExtra("priority" , items.getPriority());
                    intent.putExtra("date" , items.getDate());
                    intent.putExtra("time" , items.getTime());
                    context.startActivity(intent);

                }
            });

            //delete button
            ImageButton imageButton1 = (ImageButton) view.getChildAt(3);
            imageButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemsDAO_1.deleteItems(new Items( items.getId()));
                   new Items_Ui_Refresher(context, inflater, layout , checkListId).start();
                }
            });
        }

    }
}
