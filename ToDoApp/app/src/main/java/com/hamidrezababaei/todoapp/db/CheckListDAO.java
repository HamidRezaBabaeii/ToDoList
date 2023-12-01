package com.hamidrezababaei.todoapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.net.PortUnreachableException;
import java.util.List;

@Dao
public interface CheckListDAO {

        @Insert
        public long addCheckList(CheckList checkList);

        @Update
        public void updateCheckList(CheckList checkList);

        @Delete
        public void deleteCheckList(CheckList checkList);

        @Query("select * from CheckLists")
        public List<CheckList> getCheckLists();

        @Query("select name from CheckLists where id==:checklistId")
        public String getCheckLists_name_byId(int checklistId);


        @Query("select description from CheckLists where id==:checklistId")
        public String getCheckLists_description_byId(int checklistId);

        @Query("select isChecked,checklist_id from Items where isChecked==1 & checklist_id==:id")
        public int getCheckedItems_InCheckList(int id);

        @Query("UPDATE checklists SET name=:name , description=:description WHERE id=:id ")
        public void updateChecklist(String name , String description , int id );


}
