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
import com.zeeshan_s.to_doapplication.databinding.ActivitySingleItemBinding;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Single_Item_Activity extends AppCompatActivity {

    private ActivitySingleItemBinding binding;
    List<Task> taskList;
    boolean isPersonal = false, isOffice = false, isShopping = false, isHealth = false;
    int calDate, calMonth, calYear, tmMinute, tmHour;
    Calendar calendar;
    String priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingleItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final int ID;
        Intent intent = getIntent();


        taskList= new ArrayList<>();
        taskList = TaskDatabase.getInstance(this).getTaskDao().getTaskByID(intent.getIntExtra("itemID",0));
        ID = taskList.get(0).getId();

        binding.singleTaskTitle.setText(taskList.get(0).getTitle());
        binding.singleTaskDescription.setText(taskList.get(0).getDescription());
        binding.singleTaskDate.setText(taskList.get(0).getDate());
        binding.singleTaskTime.setText(taskList.get(0).getTime());
        binding.singleTaskPriority.setText(taskList.get(0).getPriority());
        if (taskList.get(0).isDone()){binding.singleTaskIsDone.setText("Done");}

//        -----Setting category-----
////        categorySetting();
//        if (taskList.get(0).isCategoryPersonal()){binding.singleTaskCategoryPersonal.setVisibility(View.VISIBLE);}
//        if (taskList.get(0).isCategoryOffice()){binding.singleTaskCategoryOffice.setVisibility(View.VISIBLE);}
//        if (taskList.get(0).isCategoryShopping()){binding.singleTaskCategoryShopping.setVisibility(View.VISIBLE);}
//        if (taskList.get(0).isCategoryHealth()){binding.singleTaskCategoryHealth.setVisibility(View.VISIBLE);}

//        -------------Setting edit Parts-----------
//        binding.singleTaskCategoryBucket.setOnClickListener(view -> {
////            ----------- setting category -------
//            isCategoryEdited = true;
//
//            binding.editTopicBackground.setVisibility(View.VISIBLE);
//            binding.editTopicContainer.setVisibility(View.VISIBLE);
//            binding.editTopicCategory.setVisibility(View.VISIBLE);
//
//            binding.singleTaskTitle.setEnabled(false);
////            binding.singleTaskCategoryBucket.setEnabled(false);
//            binding.singleTaskPriority.setEnabled(false);
//            binding.singleTaskDeleteBtn.setEnabled(false);
//            binding.singleTaskBackBtn.setEnabled(false);
//            binding.singleTaskSaveBtn.setEnabled(false);
//        });

        binding.singleTaskPriority.setOnClickListener(view -> {
            binding.editTopicBackground.setVisibility(View.VISIBLE);
            binding.editTopicContainer.setVisibility(View.VISIBLE);
            binding.editTopicPriority.setVisibility(View.VISIBLE);

            binding.singleTaskTitle.setEnabled(false);
//            binding.singleTaskCategoryBucket.setEnabled(false);
            binding.singleTaskPriority.setEnabled(false);
            binding.singleTaskDeleteBtn.setEnabled(false);
            binding.singleTaskBackBtn.setEnabled(false);
            binding.singleTaskSaveBtn.setEnabled(false);
        });


//        -------------- Setting Calender ----------------
        calendar = Calendar.getInstance();
        binding.singleTaskDate.setOnClickListener(view -> {
            calDate = calendar.get(Calendar.DATE);
            calMonth = calendar.get(Calendar.MONTH);
            calYear = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                            binding.singleTaskDate.setText(dayOfMonth+"-"+(month+1)+"-"+year); //---- Month attribute starts from 0

                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, month);
                            calendar.set(Calendar.DATE, dayOfMonth);

//                            timeMillis = calendar.getTimeInMillis();
                        }
                    }, calYear, calMonth, calDate);
            datePickerDialog.show();
        });

//        ------------- Setting Time picker ---------------
        binding.singleTaskTime.setOnClickListener(view -> {
            tmMinute = calendar.get(Calendar.MINUTE);
            tmHour = calendar.get(Calendar.HOUR);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                    binding.singleTaskTime.setText(getFormattedTime(hourOfDay, minute));
                }
            }, tmHour, tmMinute, false);
            timePickerDialog.show();
        });

//        --------------- Category Edit setting --------------
        if (taskList.get(0).isCategoryPersonal()){binding.editCategoryPersonal.setBackgroundResource(R.drawable.category_selected); isPersonal = true;}
        if (taskList.get(0).isCategoryOffice()){binding.editCategoryOffice.setBackgroundResource(R.drawable.category_selected); isOffice = true;}
        if (taskList.get(0).isCategoryShopping()){binding.editCategoryShopping.setBackgroundResource(R.drawable.category_selected); isShopping = true;}
        if (taskList.get(0).isCategoryHealth()){binding.editCategoryHealth.setBackgroundResource(R.drawable.category_selected); isHealth = true;}

        binding.editCategoryPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.editCategoryPersonal.getBackground().getConstantState() == getResources().getDrawable(R.drawable.category_background).getConstantState()) //---checking if the background resources are same
                {
                    binding.editCategoryPersonal.setBackgroundResource(R.drawable.category_selected);
                    isPersonal = true;
                }
                else{
                    binding.editCategoryPersonal.setBackgroundResource(R.drawable.category_background);
                    isPersonal = false;
                }
            }
        });

        binding.editCategoryOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editCategoryOffice.getBackground().getConstantState() == getResources().getDrawable(R.drawable.category_background).getConstantState()) //---checking if the background resources are same
                {
                    binding.editCategoryOffice.setBackgroundResource(R.drawable.category_selected);
                    isOffice = true;
                }else{
                    binding.editCategoryOffice.setBackgroundResource(R.drawable.category_background);
                    isOffice = false;
                }
            }
        });

        binding.editCategoryShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editCategoryShopping.getBackground().getConstantState() == getResources().getDrawable(R.drawable.category_background).getConstantState()) //---checking if the background resources are same
                {
                    binding.editCategoryShopping.setBackgroundResource(R.drawable.category_selected);
                    isShopping = true;
                }else{
                    binding.editCategoryShopping.setBackgroundResource(R.drawable.category_background);
                    isShopping = false;
                }
            }
        });

        binding.editCategoryHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editCategoryHealth.getBackground().getConstantState() == getResources().getDrawable(R.drawable.category_background).getConstantState()) //---checking if the background resources are same
                {
                    binding.editCategoryHealth.setBackgroundResource(R.drawable.category_selected);
                    isHealth = true;
                }else{
                    binding.editCategoryHealth.setBackgroundResource(R.drawable.category_background);
                    isHealth= false;
                }
            }
        });

//        --------------- Category Setting End ----------------

//        Log.i("TAG", "Before radio button category");
        //        ----------RadioButton start -------------
        if (taskList.get(0).getPriority().equals("Normal")){binding.editPrioNormalRBtn.setChecked(true);}
        if (taskList.get(0).getPriority().equals("Medium")){binding.editPrioMediumRBtn.setChecked(true);}
        if (taskList.get(0).getPriority().equals("Important")){binding.editPrioImportantRBtn.setChecked(true);}

        binding.editTopicPriority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rBtn = findViewById(i);
                priority = rBtn.getText().toString();
            }
        });

        binding.singleTaskBackBtn.setOnClickListener(view -> {
            finish();
        });

        binding.singleTaskDeleteBtn.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Single_Item_Activity.this);
            builder.setIcon(R.drawable.warning_icon);
            builder.setTitle("Delete");
            builder.setMessage("Do you really want to delete this item?\nYou won't be able to retrieve this data");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    TaskDatabase.getInstance(Single_Item_Activity.this).getTaskDao().delete(taskList.get(0));
                    Intent intentToMainAfterDelete = new Intent(Single_Item_Activity.this, MainActivity.class);
                    startActivity(intentToMainAfterDelete);
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });

//        -----------------------------------------
        binding.singleTaskSaveBtn.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.priority_medium_icon);
            builder.setTitle("Save changes");
            builder.setMessage("Do you want to save this changes?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Task task = new Task();
                    task.setId(ID);
                    if (priority==null){ priority = "Normal";}
                    task.setPriority(priority);
                    task.setCategoryPersonal(isPersonal);
                    task.setCategoryOffice(isOffice);
                    task.setCategoryShopping(isShopping);
                    task.setCategoryHealth(isHealth);

                    if (binding.singleTaskTitle.getText().toString().equals("") || binding.singleTaskDescription.getText().toString().equals("")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Single_Item_Activity.this);
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
                    }
                    else {
                        task.setTitle(binding.singleTaskTitle.getText().toString());
                        task.setDescription(binding.singleTaskDescription.getText().toString());


                        task.setTime(binding.singleTaskTime.getText().toString());
                        task.setDate(binding.singleTaskDate.getText().toString());
                        task.setDone(taskList.get(0).isDone());
                        task.setDateMillis(taskList.get(0).getDateMillis());

                        TaskDatabase.getInstance(Single_Item_Activity.this).getTaskDao().update(task);
                        Intent intentMain = new Intent(Single_Item_Activity.this, MainActivity.class);
                        startActivity(intentMain);
                    }

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();


        });

        //        --------------- Saving the changes from edit Save Change Button -------------
//        --------------------------------------------
        binding.editSaveChangeBtn.setOnClickListener(view -> {
            Task task = new Task();
            task.setId(ID);
//                if (isCategoryEdited){
//                    task.setCategoryPersonal(isPersonal);
//                    task.setCategoryOffice(isOffice);
//                    task.setCategoryShopping(isShopping);
//                    task.setCategoryHealth(isHealth);
//
//                    task.setPriority(taskList.get(0).getPriority());
//
//                    Log.i("TAG", "Gone to category");
//                }
//                else{
            task.setPriority(priority);
            task.setCategoryPersonal(taskList.get(0).isCategoryPersonal());
            task.setCategoryOffice(taskList.get(0).isCategoryOffice());
            task.setCategoryShopping(taskList.get(0).isCategoryShopping());
            task.setCategoryHealth(taskList.get(0).isCategoryHealth());

            task.setTitle(taskList.get(0).getTitle());
            task.setTime(taskList.get(0).getTime());
            task.setDate(taskList.get(0).getDate());
            task.setDescription(taskList.get(0).getDescription());
            task.setDone(taskList.get(0).isDone());
            task.setDateMillis(taskList.get(0).getDateMillis());

            TaskDatabase.getInstance(this).getTaskDao().update(task);

            binding.singleTaskPriority.setText(priority);
            onBackPressed();
//            categorySetting();
        });


    }


//    private void categorySetting() {
//        Log.i("TAG", "category called");
//
//        if (taskList.get(0).isCategoryPersonal()){binding.singleTaskCategoryPersonal.setVisibility(View.VISIBLE);}
////        else{binding.singleTaskCategoryPersonal.setVisibility(View.GONE);}
//
//        if (taskList.get(0).isCategoryOffice()){binding.singleTaskCategoryOffice.setVisibility(View.VISIBLE);}
////        else {binding.singleTaskCategoryOffice.setVisibility(View.GONE);}
//
//        if (taskList.get(0).isCategoryShopping()){binding.singleTaskCategoryShopping.setVisibility(View.VISIBLE);}
////        else{binding.singleTaskCategoryShopping.setVisibility(View.GONE);}
//
//        if (taskList.get(0).isCategoryHealth()){binding.singleTaskCategoryHealth.setVisibility(View.VISIBLE);}
////        else{binding.singleTaskCategoryHealth.setVisibility(View.GONE);}
//
//        if (taskList.get(0).isCategoryPersonal() == false){binding.singleTaskCategoryPersonal.setVisibility(View.GONE);}
//        if (taskList.get(0).isCategoryOffice() == false){binding.singleTaskCategoryOffice.setVisibility(View.GONE);}
//        if (taskList.get(0).isCategoryShopping() == false){binding.singleTaskCategoryShopping.setVisibility(View.GONE);}
//        if (taskList.get(0).isCategoryHealth() == false){binding.singleTaskCategoryHealth.setVisibility(View.GONE);}
//        Log.i("TAG", "Gone to category end ------");
//
//    }
private String getFormattedTime(int hourOfDay, int minute) {

    Time time = new Time(hourOfDay, minute, 0);
    Format formatter = new SimpleDateFormat("h:mm a");
    return formatter.format(time);

}

    @Override
    public void onBackPressed() {
        Log.i("TAG", "Gone to Back pressed");
        if (binding.editTopicBackground.getVisibility()== View.VISIBLE || binding.editTopicContainer.getVisibility()== View.VISIBLE){
            binding.editTopicBackground.setVisibility(View.GONE);
            binding.editTopicContainer.setVisibility(View.GONE);
            binding.editTopicPriority.setVisibility(View.GONE);
//            binding.editTopicCategory.setVisibility(View.GONE);

            binding.singleTaskTitle.setEnabled(true);
//            binding.singleTaskCategoryBucket.setEnabled(true);
            binding.singleTaskPriority.setEnabled(true);
            binding.singleTaskDeleteBtn.setEnabled(true);
            binding.singleTaskBackBtn.setEnabled(true);
            binding.singleTaskSaveBtn.setEnabled(true);
        }else {
            super.onBackPressed();
        }
    }
}