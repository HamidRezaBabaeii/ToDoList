package com.hamidrezababaei.todoapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(exportSchema = false, entities = {CheckList.class, Items.class}, version = 1)
public abstract class TodoListDatabase extends RoomDatabase {

    public static TodoListDatabase todoListDatabase ;
    public static TodoListDatabase getTodoListDatabase(Context context){
        if (todoListDatabase == null){
            todoListDatabase = Room.databaseBuilder(context , TodoListDatabase.class , "DBToDoList")
                    .allowMainThreadQueries()
                    .build();
        }
        return todoListDatabase ;
    }
    public abstract CheckListDAO getCheckListDAO();
    public abstract ItemsDAO getItemsDAO();
}
