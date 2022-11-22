package com.zeeshan_s.to_doapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.zeeshan_s.to_doapplication.Database.Task;
import com.zeeshan_s.to_doapplication.Database.TaskDatabase;
import com.zeeshan_s.to_doapplication.databinding.ActivityAddNewTaskBinding;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddNewTask extends AppCompatActivity {

    private ActivityAddNewTaskBinding binding;
//    List<String> categoryName;
    boolean isPersonal = false, isOffice = false, isShopping = false, isHealth= false;
    int calDate, calMonth, calYear, tmMinute, tmHour;
    long timeMillis;
    Calendar calendar;

    String priority="Normal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calendar = Calendar.getInstance();

//        -------------- Setting Calender ----------------
        binding.addTaskDate.setOnClickListener(view -> {
            calDate = calendar.get(Calendar.DATE);
            calMonth = calendar.get(Calendar.MONTH);
            calYear = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                           binding.addTaskDate.setText(dayOfMonth+"-"+(month+1)+"-"+year); //---- Month attribute starts from 0

                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, month);
                            calendar.set(Calendar.DATE, dayOfMonth);

                            timeMillis = calendar.getTimeInMillis();
                        }
                    }, calYear, calMonth, calDate);
            datePickerDialog.show();
        });

//        ------------- Setting Time picker ---------------
        binding.addTaskTime.setOnClickListener(view -> {
            tmMinute = calendar.get(Calendar.MINUTE);
            tmHour = calendar.get(Calendar.HOUR);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                    binding.addTaskTime.setText(getFormattedTime(hourOfDay, minute));
                }
            }, tmHour, tmMinute, false);
            timePickerDialog.show();
        });

//        --------------- Category Setting Start --------------
        binding.addCategoryPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.addCategoryPersonal.getBackground().getConstantState() == getResources().getDrawable(R.drawable.category_background).getConstantState()) //---checking if the background resources are same
                {
                    binding.addCategoryPersonal.setBackgroundResource(R.drawable.category_selected);
                    isPersonal = true;
                }
                else{
                    binding.addCategoryPersonal.setBackgroundResource(R.drawable.category_background);
                    isPersonal = false;
                }
            }
        });

        binding.addCategoryOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.addCategoryOffice.getBackground().getConstantState() == getResources().getDrawable(R.drawable.category_background).getConstantState()) //---checking if the background resources are same
                {
                    binding.addCategoryOffice.setBackgroundResource(R.drawable.category_selected);
                    isOffice = true;
                }else{
                    binding.addCategoryOffice.setBackgroundResource(R.drawable.category_background);
                    isOffice = false;
                }
            }
        });

        binding.addCategoryShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.addCategoryShopping.getBackground().getConstantState() == getResources().getDrawable(R.drawable.category_background).getConstantState()) //---checking if the background resources are same
                {
                    binding.addCategoryShopping.setBackgroundResource(R.drawable.category_selected);
                    isShopping = true;
                }else{
                    binding.addCategoryShopping.setBackgroundResource(R.drawable.category_background);
                    isShopping = false;
                }
            }
        });

        binding.addCategoryHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.addCategoryHealth.getBackground().getConstantState() == getResources().getDrawable(R.drawable.category_background).getConstantState()) //---checking if the background resources are same
                {
                    binding.addCategoryHealth.setBackgroundResource(R.drawable.category_selected);
                    isHealth = true;
                }else{
                    binding.addCategoryHealth.setBackgroundResource(R.drawable.category_background);
                    isHealth= false;
                }
            }
        });

//        --------------- Category Setting End ----------------

//        ----------RadioButton start -------------
        binding.taskNormalRBtn.setChecked(true);
        binding.taskImportanceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rBtn = findViewById(i);
                priority = rBtn.getText().toString();
            }
        });

//        ---------------- cancel Button -----------------
        binding.addActivityCancelButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddNewTask.this, MainActivity.class);
            startActivity(intent);
        });

//      ----------------- Add button ---------------------
        binding.addActivityAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.addTitle.getText().toString();
                String description = binding.addDescription.getText().toString();
                String taskTime = binding.addTaskTime.getText().toString();
                boolean isDone = false;

                Task task = new Task();

                task.setTitle(title);
                task.setDescription(description);
                if (title.equals("") || description.equals("") || binding.addTaskDate.getText().toString().equals("DD-MM-YYYY")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddNewTask.this);
                    builder.setIcon(R.drawable.warning_icon);
                    builder.setTitle("Warning");
                    builder.setMessage("Field cannot be empty!");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else {

                    task.setCategoryPersonal(isPersonal);
                    task.setCategoryOffice(isOffice);
                    task.setCategoryShopping(isShopping);
                    task.setCategoryHealth(isHealth);
                    if (priority==null){ priority = "Normal";}
                    task.setPriority(priority);
                    task.setDone(isDone);

                    task.setDate(binding.addTaskDate.getText().toString());
                    task.setTime(taskTime);
                    task.setDateMillis(timeMillis);

                    Log.i("TAG", "Inside the add activity");
                    TaskDatabase.getInstance(AddNewTask.this).getTaskDao().insert(task);
                    Intent intent = new Intent(AddNewTask.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private String getFormattedTime(int hourOfDay, int minute) {

        Time time = new Time(hourOfDay, minute, 0);
        Format formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(time);

    }
}