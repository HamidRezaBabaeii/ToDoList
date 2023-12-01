package com.hamidrezababaei.todoapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemsDAO {

        @Insert
        public long addItem(Items items);

        @Query("UPDATE Items SET isChecked=:isChecked WHERE id=:id AND checklist_id=:check_id")
        public void addChecked(boolean isChecked , int id , int check_id );

        @Query("UPDATE Items SET isChecked=:isChecked WHERE id=:id ")
        public void addCheckedCategory(boolean isChecked , int id  );

        @Query("UPDATE Items SET name=:name,description=:description,priority=:priority,category=:category,date=:date,time=:time WHERE id=:id AND checklist_id=:check_id")
        public void updateItem(String name, String description , String priority , String category , String date , String time , int id , int check_id );

        @Update
        public void updateItem(Items items);

        @Delete
        public void deleteItems(Items items);

        @Query("select * from Items")
        public List<Items> getItems();

        @Query("select * from Items where checklist_id==:checklistID")
        public List<Items> getItems_by_checkListId(int checklistID);

        @Query("select * from Items where category=:category")
        public List<Items> getItems_by_category(String category);

        @Query("SELECT COUNT(isChecked) FROM Items WHERE checklist_id=:checklist_id AND isChecked=:True ")
        public int isCheckedPTRUE(int checklist_id , boolean True);

        @Query("SELECT COUNT(isChecked) FROM Items WHERE checklist_id=:checklist_id  ")
        public int isCheckedPALL(int checklist_id);

        @Query(" SELECT id FROM Items ORDER BY id DESC LIMIT 1")
        public int getLastIItemd();

        @Query(" SELECT COUNT(id) FROM Items")
        public int getCountItems();

        @Query("SELECT name,checklist_id FROM Items WHERE id=:id  ")
        public int getItemById(int id);

}
