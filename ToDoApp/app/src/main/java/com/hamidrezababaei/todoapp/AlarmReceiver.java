package com.hamidrezababaei.todoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Item : "+intent.getStringExtra("ItemName"), Toast.LENGTH_LONG).show();
    }
}
