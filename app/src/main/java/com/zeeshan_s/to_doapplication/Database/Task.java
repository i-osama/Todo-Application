package com.zeeshan_s.to_doapplication.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    private String title, description, date, time, priority;


    @ColumnInfo
    private boolean isDone, categoryPersonal, categoryOffice, categoryShopping,categoryHealth;

    @ColumnInfo
    private long dateMillis;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public long getDateMillis() {
        return dateMillis;
    }

    public void setDateMillis(long dateMillis) {
        this.dateMillis = dateMillis;
    }

    public boolean isCategoryPersonal() {
        return categoryPersonal;
    }

    public void setCategoryPersonal(boolean categoryPersonal) {
        this.categoryPersonal = categoryPersonal;
    }

    public boolean isCategoryOffice() {
        return categoryOffice;
    }

    public void setCategoryOffice(boolean categoryOffice) {
        this.categoryOffice = categoryOffice;
    }

    public boolean isCategoryShopping() {
        return categoryShopping;
    }

    public void setCategoryShopping(boolean categoryShopping) {
        this.categoryShopping = categoryShopping;
    }

    public boolean isCategoryHealth() {
        return categoryHealth;
    }

    public void setCategoryHealth(boolean categoryHealth) {
        this.categoryHealth = categoryHealth;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", priority='" + priority + '\'' +
                ", isDone=" + isDone +
                ", categoryPersonal=" + categoryPersonal +
                ", categoryOffice=" + categoryOffice +
                ", categoryShopping=" + categoryShopping +
                ", categoryHealth=" + categoryHealth +
                ", dateMillis=" + dateMillis +
                '}';
    }
}
