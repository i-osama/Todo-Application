package com.zeeshan_s.to_doapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.*;
import com.zeeshan_s.to_doapplication.Database.TaskDatabase;
import com.zeeshan_s.to_doapplication.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements ScreenUpdaterListener{

    ActivityMainBinding binding;
    boolean hasUpcoming = false;
    int upcomingTaskID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapterRunner();

//        ------------- Setting Upcoming task on click ----------------
        binding.upcomingTaskToday.setOnClickListener(view -> {
            if (hasUpcoming){
                Intent intent = new Intent(MainActivity.this, Single_Item_Activity.class);
                intent.putExtra("itemID",upcomingTaskID);
                startActivity(intent);
            }
        });

//        ---------------- Category on click -----------------
        binding.personalCategory.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CategorySelectedActivity.class);
            intent.putExtra("imgName",R.drawable.personal_icon);
            intent.putExtra("categoryName","Personal");
            intent.putExtra("isPersonalCategory",true);
            startActivity(intent);

        });
        binding.officeCategory.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CategorySelectedActivity.class);
            intent.putExtra("imgName",R.drawable.office);
            intent.putExtra("categoryName","Office");
            intent.putExtra("isOfficeCategory",true);
            startActivity(intent);
        });
        binding.shoppingCategory.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CategorySelectedActivity.class);
            intent.putExtra("imgName",R.drawable.shopping);
            intent.putExtra("categoryName","Shopping");
            intent.putExtra("isShoppingCategory",true);
            startActivity(intent);
        });
        binding.healthCategory.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CategorySelectedActivity.class);
            intent.putExtra("imgName",R.drawable.hospital);
            intent.putExtra("categoryName","Health");
            intent.putExtra("isHealthCategory",true);
            startActivity(intent);
        });

        binding.allTaskImg.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CategorySelectedActivity.class);
            intent.putExtra("imgName",R.drawable.to_do_list_icon);
            intent.putExtra("categoryName","All");
            startActivity(intent);
        });


        binding.addTaskButton.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, AddNewTask.class);
            startActivity(intent);
        });

    }

    private void adapterRunner() {
//        TaskAdapter adapter = new TaskAdapter(MainActivity.this, TaskDatabase.getInstance(this).getTaskDao().getTask());
        TaskAdapter adapter = new TaskAdapter(MainActivity.this, TaskDatabase.getInstance(this).getTaskDao().getTask(getDateToday()), this);
        binding.taskRecycler.setAdapter(adapter);
//        ---
        int listSize = TaskDatabase.getInstance(this).getTaskDao().getTask(getDateToday()).size();
        if (listSize != 0) {
            for (int i = 0; i < listSize; i++) {
                Log.i("TAG", "adapterRunner: "+ TaskDatabase.getInstance(this).getTaskDao().getTask(getDateToday()).get(i).isDone());
                if (TaskDatabase.getInstance(this).getTaskDao().getTask(getDateToday()).get(i).isDone()== false) {
                    binding.upcomingTaskToday.setText("Next:\n"+TaskDatabase.getInstance(this).getTaskDao().getTask(getDateToday()).get(i).getTitle());
                    hasUpcoming = true;
                    upcomingTaskID = TaskDatabase.getInstance(MainActivity.this).getTaskDao().getTask(getDateToday()).get(i).getId();
                    break;
                }
                else {
                    continue;
                }
            }
        }
        else {
            binding.upcomingTaskToday.setText("You don't have any task today");
        }

//        ---

        String allTask = "All remaining task number: "+TaskDatabase.getInstance(this).getTaskDao().getTask(getDateToday()).size();
        binding.allRemainingTaskToday.setText(allTask);
    }

    private String getDateToday() {
        int date, month, year;
        Calendar calendar = Calendar.getInstance();

        date = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        return date+"-"+(month+1)+"-"+year; //---- Month attribute starts from 0 that is why I added 1 with month
    }

    @Override
    public void screenRefresher() {
        adapterRunner();
    }
}