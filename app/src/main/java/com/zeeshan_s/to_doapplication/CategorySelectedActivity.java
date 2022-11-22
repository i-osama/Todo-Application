package com.zeeshan_s.to_doapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.zeeshan_s.to_doapplication.Database.TaskDatabase;
import com.zeeshan_s.to_doapplication.databinding.ActivityCategorySelectedBinding;

public class CategorySelectedActivity extends AppCompatActivity implements ScreenUpdaterListener{

    ActivityCategorySelectedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategorySelectedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent= getIntent();

        binding.selectedCategoryImage.setImageResource(intent.getIntExtra("imgName",700008));
        binding.selectedCategoryName.setText(intent.getStringExtra("categoryName"));

//        *************** To setup the view holder we will use the same ViewHolder from MainActivity************

        if (intent.getBooleanExtra("isPersonalCategory",false)){
            binding.selectedCategoryText.setText("All tasks from personal category");
            CategoryAdapter adapter = new CategoryAdapter(this, "Personal", TaskDatabase.getInstance(this).getTaskDao().getTaskIsPersonal(true));
            binding.selectedCategoryRecycler.setAdapter(adapter);
        }
        else if (intent.getBooleanExtra("isOfficeCategory",false)){
            binding.selectedCategoryText.setText("All tasks from Office category");
            CategoryAdapter adapter = new CategoryAdapter(this, "Office", TaskDatabase.getInstance(this).getTaskDao().getTaskIsOffice(true));
            binding.selectedCategoryRecycler.setAdapter(adapter);
        }
        else if (intent.getBooleanExtra("isShoppingCategory",false)){
            binding.selectedCategoryText.setText("All tasks from shopping category");
            CategoryAdapter adapter = new CategoryAdapter(this, "Shopping", TaskDatabase.getInstance(this).getTaskDao().getTaskIsShopping(true));
            binding.selectedCategoryRecycler.setAdapter(adapter);
        }
        else if (intent.getBooleanExtra("isHealthCategory",false)){
            binding.selectedCategoryText.setText("All tasks from health category");
            CategoryAdapter adapter = new CategoryAdapter(this, "Health", TaskDatabase.getInstance(this).getTaskDao().getTaskIsHealth(true));
            binding.selectedCategoryRecycler.setAdapter(adapter);
        }else{
            binding.selectedCategoryText.setText("All tasks");
            TaskAdapter adapter = new TaskAdapter(this, TaskDatabase.getInstance(this).getTaskDao().getTask(), this);
            binding.selectedCategoryRecycler.setAdapter(adapter);
        }
    }

    @Override
    public void screenRefresher() {

    }
}