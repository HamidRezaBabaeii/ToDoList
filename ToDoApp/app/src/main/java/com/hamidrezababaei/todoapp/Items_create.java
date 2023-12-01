package com.hamidrezababaei.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hamidrezababaei.todoapp.db.Items;
import com.hamidrezababaei.todoapp.db.ItemsDAO;
import com.hamidrezababaei.todoapp.db.TodoListDatabase;

public class Items_create extends AppCompatActivity {

    TextView  add_items_textview , name_textview , priority_textview , description_textview , category_textview ;
    EditText name_EditText , description_EditText ;
    Spinner  priority_spinner , category_spinner , date_spinner , hour_spinner , minute_spinner ;
    ImageView calender_image , watch_image ;
    Button cancel_btn , save_btn ;

    //private TodoListDatabase todoListDatabase;
    private ItemsDAO itemsDAO;
    private  int i ;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_create);

        TodoListDatabase db = TodoListDatabase.getTodoListDatabase(this);
        itemsDAO = db.getItemsDAO();

        //get checkList id
        Intent intent = getIntent();
        String id = intent.getStringExtra("CheckListId");
        int checkListID = Integer.parseInt(id);
       // Log.e("checkList id", "onCreate: "+ id );
        // Text view
        add_items_textview = findViewById(R.id.textView_add_Item);
        name_textview = findViewById(R.id.textView_customDialog_name);
        priority_textview = findViewById(R.id.textView_customDialog_description);
        description_textview = findViewById(R.id.textView_itemCr_priority);
        category_textview = findViewById(R.id.textView_itemCr_category);


        // Edit Text
        name_EditText = findViewById(R.id.editText_customDialog_name);
        description_EditText = findViewById(R.id.editText_customDialog_description);


        // Spinners
        priority_spinner = findViewById(R.id.spinner_number);
        category_spinner = findViewById(R.id.spinner_category);
        date_spinner = findViewById(R.id.spinner_Date);
        hour_spinner = findViewById(R.id.spinner_Time_hour);
        minute_spinner = findViewById(R.id.spinner_Time_minute);


        // Buttons
        cancel_btn = findViewById(R.id.cancel_btn_customDialog);
        save_btn = findViewById(R.id.send_btn_customDialog);


        // Image views
        calender_image = findViewById(R.id.calender_image);
        watch_image = findViewById(R.id.watch_image);

//        Log.d("spinners :" ,    name_EditText.getText().toString()+" -- "+
//                description_EditText.getText().toString()+" -- "+
//                priority_spinner.getSelectedItem().toString()+" -- "+
//                category_spinner.getSelectedItem().toString()+" -- "+
//                date_spinner.getSelectedItem().toString()+" -- "+
//                getTime() );

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsDAO.addItem(new Items(
                        name_EditText.getText().toString(),
                        description_EditText.getText().toString(),
                        priority_spinner.getSelectedItem().toString(),
                        category_spinner.getSelectedItem().toString(),
                        date_spinner.getSelectedItem().toString(),
                        getTime(),
                        false,
                        checkListID
                        ));
                //----------------
                name = name_EditText.getText().toString();
                if(itemsDAO.getCountItems() == 0  ){
                   i=1;
                }else{
                    i =itemsDAO.getLastIItemd()+1;
                }
                setAlarm(Items_create.this , getAlarmTime() , i);
                //-----------------
                finish();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }

    public String getTime(){
        String hour1 = hour_spinner.getSelectedItem().toString();
        String minute1 = minute_spinner.getSelectedItem().toString();
        String time = hour1+":"+minute1;
        return time;
    }

    //----------------------------
    public int getAlarmTime(){
        int i;
        String hour1 = hour_spinner.getSelectedItem().toString();
        String minute1 = minute_spinner.getSelectedItem().toString();
        if(hour1.equals("") && minute1.equals("")){
            i = -1;
        }
        else if(hour1.equals("") && !minute1.equals("")){
             i = Integer.parseInt(minute1);
        }
        else if(!hour1.equals("") && minute1.equals("")){
            i = Integer.parseInt(hour1)*60;
        }
        else{
            i = Integer.parseInt(minute1) + (Integer.parseInt(hour1)*60);
        }
        return i;
    }
    //------------------------
    public void setAlarm(Context context, int time_min , int itemId) {

        if(time_min == -1){
            Toast.makeText(context, "Alarm Didn't Set", Toast.LENGTH_SHORT).show();
        }else{
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, AlarmReceiver.class);
            intent.putExtra("ItemName" ,name);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, itemId, intent, PendingIntent.FLAG_IMMUTABLE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis() + (time_min *60000) , pendingIntent);
        }
        
    }
}