package com.zeeshan_s.to_doapplication.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class},version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao getTaskDao();
    static TaskDatabase taskDatabase = null;

    public static TaskDatabase getInstance(Context context){

        if(taskDatabase == null){

            taskDatabase = Room.databaseBuilder(context,
                    TaskDatabase.class,
                    "Task_DB")
                    .allowMainThreadQueries()
                    .build();
        }
        return taskDatabase;
    }
}
