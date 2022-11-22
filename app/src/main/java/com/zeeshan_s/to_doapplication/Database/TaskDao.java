package com.zeeshan_s.to_doapplication.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM Task")
    List<Task> getTask();

    @Query("SELECT * FROM Task WHERE date == :today")
    List<Task> getTask(String today); //---------------- teacher used getTask() here----------

    @Query("SELECT * FROM Task WHERE priority == :priority")
    List<Task> getTaskByPriority(String priority);

    @Query("SELECT * FROM Task WHERE categoryPersonal == :category")
    List<Task> getTaskIsPersonal(boolean category);

    @Query("SELECT * FROM Task WHERE categoryOffice == :category")
    List<Task> getTaskIsOffice(boolean category);

    @Query("SELECT * FROM Task WHERE categoryShopping == :category")
    List<Task> getTaskIsShopping(boolean category);

    @Query("SELECT * FROM Task WHERE categoryHealth == :category")
    List<Task> getTaskIsHealth(boolean category);

    @Query("SELECT * FROM Task WHERE id == :taskID")
    List<Task> getTaskByID(int taskID);

}
