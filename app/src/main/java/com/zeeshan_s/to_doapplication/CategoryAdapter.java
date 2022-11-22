package com.zeeshan_s.to_doapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zeeshan_s.to_doapplication.Database.Task;
import com.zeeshan_s.to_doapplication.Database.TaskDatabase;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    Context context;
    String categoryName;
    List<Task> taskList;

    public CategoryAdapter(Context context, String categoryName, List<Task> taskList) {
        this.context = context;
        this.categoryName = categoryName;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_recycler, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.selCategoryTime.setText(task.getTime());
        holder.selCategoryDate.setText(task.getDate());

        holder.selCategoryCheckbox.setChecked(task.isDone());
        holder.selCategoryTitle.setText(task.getTitle());
        holder.selCategoryName.setText(categoryName);

        //        -----Setting Priority-----
        if (task.getPriority().equals("Normal")){Glide.with(context).load(R.drawable.priority_normal_icon).into(holder.selCategoryPriority);}
        if (task.getPriority().equals("Medium")){Glide.with(context).load(R.drawable.priority_medium_icon).into(holder.selCategoryPriority);}
        if (task.getPriority().equals("Important")){Glide.with(context).load(R.drawable.priority_high_icon).into(holder.selCategoryPriority);}

        holder.selCategoryCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                task.setDone(b);
                TaskDatabase.getInstance(context).getTaskDao().update(task);
            }
        });
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, Single_Item_Activity.class);
//            Log.i(TAG, "This works------ ");
            intent.putExtra("itemID", task.getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
