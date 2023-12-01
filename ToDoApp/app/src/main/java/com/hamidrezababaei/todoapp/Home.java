package com.hamidrezababaei.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    Button meet_people , do_exercise , travel , home_work , daily_works;
    TextView textView_ca ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Text View
        textView_ca = findViewById(R.id.textView_home_activity);


        // Image Buttons
        meet_people = findViewById(R.id.imageBtn_meetPeople_homeAc);
        do_exercise = findViewById(R.id.imageBtn_doExercise_homeAc);
        travel = findViewById(R.id.imageBtn_travel_homeAc);
        home_work = findViewById(R.id.imageBtn_homeWork_homeAc);
        daily_works = findViewById(R.id.imageBtn_dailyWorks_homeAc);

        meet_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , CategoryDetails.class);
                intent.putExtra("category" , "Meet People");
                startActivity(intent);
            }
        });

        do_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , CategoryDetails.class);
                intent.putExtra("category" , "Do Exercise");
                startActivity(intent);
            }
        });

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , CategoryDetails.class);
                intent.putExtra("category" , "Travel");
                startActivity(intent);
            }
        });

        home_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , CategoryDetails.class);
                intent.putExtra("category" , "HomeWorks");
                startActivity(intent);
            }
        });

        daily_works.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , CategoryDetails.class);
                intent.putExtra("category" , "Daily Works");
                startActivity(intent);
            }
        });

    }
}