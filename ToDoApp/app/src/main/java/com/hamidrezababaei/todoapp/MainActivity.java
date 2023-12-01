package com.hamidrezababaei.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hamidrezababaei.todoapp.db.CheckList;
import com.hamidrezababaei.todoapp.db.CheckListDAO;
import com.hamidrezababaei.todoapp.db.TodoListDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton home_btn ;
    TextView date_TextView ;
    FloatingActionButton addFab;
    ImageView background;
    // ROOM DATABASE
    private CheckListDAO checkListDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DataBase
        TodoListDatabase db = TodoListDatabase.getTodoListDatabase(this);
        checkListDAO = db.getCheckListDAO();

        //Get All CheckLists
        LinearLayout layout = findViewById(R.id.checklist_linear);
        new Ui_Refresher(this, getLayoutInflater(), layout).start();

        // set date
        String date = Date_Set();

        // Image View buttons
        home_btn = findViewById(R.id.imageButton_home_mainAc);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(MainActivity.this , Home.class);
                startActivity(home);
            }
        });


//      Text View
        date_TextView = findViewById(R.id.textview_date_mainAc);
        date_TextView.setText(date);

        // FloatingActionButton
        addFab = findViewById(R.id.floatingActionButton_add_mainAc);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        // Background Image
        background = findViewById(R.id.backgroundImage);

    }

    public static String Date_Set(){
        String date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = String.valueOf(java.time.LocalDate.now());
        }
        String newFormat = "TODAY ";
        char[] chr = date.toCharArray();
        for (char ch:chr){
            if (ch == '-') ch = '/';
            newFormat += ch ;
        }
        return  newFormat ;
    }

   public  void showCustomDialog(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();

        EditText name = dialog.findViewById(R.id.editText_customDialog_name);
        EditText description = dialog.findViewById(R.id.editText_customDialog_description);
        Button save = dialog.findViewById(R.id.send_btn_customDialog);

        save.setOnClickListener(v -> {
           if(!name.getText().toString().equals("") && !description.getText().toString().equals("") ){

               checkListDAO.addCheckList(new CheckList(name.getText().toString() , description.getText().toString()));
               LinearLayout  layout = findViewById(R.id.checklist_linear);
               new Ui_Refresher(this, getLayoutInflater(), layout).start();
               dialog.cancel();
           }else{
               Toast.makeText(MainActivity.this,"Please Fill All Fields", Toast.LENGTH_SHORT).show();
           }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LinearLayout layout = findViewById(R.id.checklist_linear);
        new Ui_Refresher(this, getLayoutInflater(), layout).start();
    }
}