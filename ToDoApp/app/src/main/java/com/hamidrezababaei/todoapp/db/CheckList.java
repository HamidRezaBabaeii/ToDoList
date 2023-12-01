package com.hamidrezababaei.todoapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "CheckLists")
public class CheckList {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private  int   cheklistId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    public  CheckList(int cheklistId , String name , String description){
        this.cheklistId = cheklistId;
        this.description= description;
        this.name = name ;
    }

    @Ignore
    public  CheckList( String name , String description){
        this.description= description;
        this.name = name ;
    }

    @Ignore
    public  CheckList(int cheklistId){
        this.cheklistId = cheklistId;
    }

    @Ignore
    public CheckList(){
    }

    public int getCheklistId() {
        return cheklistId;
    }

    public void setCheklistId(int cheklistId) {
        this.cheklistId = cheklistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
