package com.hamidrezababaei.todoapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Items")
public class Items {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "priority")
    private String priority;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "isChecked")
    private boolean isChecked;

    @ColumnInfo(name = "checklist_id")
    private int CheckList_Id;

    public Items(int id ,  String name , String description , String priority , String category , String date , String time , boolean isChecked ,int CheckList_Id ){
        this.id = id ;
        this.CheckList_Id = CheckList_Id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.date = date;
        this.time = time ;
        this.isChecked = isChecked;
    }

    @Ignore
    public Items(){
    }

    @Ignore
    public Items(int checkList_Id , int id ,boolean isChecked){
            this.id=id;
            this.CheckList_Id = checkList_Id;
            this.isChecked = isChecked;
    }

    @Ignore
    public Items(int id ){
        this.id = id;
    }

    @Ignore
    public Items(String s){
        int id = Integer.parseInt(s);
        this.CheckList_Id = id;
    }

    @Ignore
    public Items(String name, String description, String priority, String category, String date, String time, boolean isChecked ,int checkList_Id){
        this.name = name;
        this.CheckList_Id = checkList_Id;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.date = date;
        this.time = time ;
        this.isChecked = isChecked;
    }


    public int getCheckList_Id() {
        return CheckList_Id;
    }

    public void setCheckList_Id(int checkList_Id) {
        CheckList_Id = checkList_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getIsChecked() {return isChecked;}

    public void setIsChecked(boolean isChecked) {this.isChecked = isChecked;}
}
