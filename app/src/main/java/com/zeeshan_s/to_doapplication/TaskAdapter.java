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

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    Context context;
    List<Task> taskList;
    ScreenUpdaterListener listener;

    public TaskAdapter(Context context, List<Task> taskList, ScreenUpdaterListener listener) {
        this.context = context;
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.to_do_list_recycler_shower, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.date.setText(task.getDate());
        holder.time.setText(task.getTime());
        holder.checkBox.setChecked(task.isDone());
        holder.title.setText(task.getTitle());

//        -----Setting category-----
        if (task.isCategoryPersonal()){holder.cat_personal.setVisibility(View.VISIBLE);}
        else {holder.cat_personal.setVisibility(View.GONE);}
        if (task.isCategoryOffice()){holder.cat_office.setVisibility(View.VISIBLE);}
        else {holder.cat_office.setVisibility(View.GONE);}
        if (task.isCategoryShopping()){holder.cat_shopping.setVisibility(View.VISIBLE);}
        else {holder.cat_shopping.setVisibility(View.GONE);}
        if (task.isCategoryHealth()){holder.cat_health.setVisibility(View.VISIBLE);}
        else {holder.cat_health.setVisibility(View.GONE);}

//        -----Setting Priority-----
        if (task.getPriority().equals("Normal")){Glide.with(context).load(R.drawable.priority_normal_icon).into(holder.priority);}
        if (task.getPriority().equals("Medium")){Glide.with(context).load(R.drawable.priority_medium_icon).into(holder.priority);}
        if (task.getPriority().equals("Important")){Glide.with(context).load(R.drawable.priority_high_icon).into(holder.priority);}

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                task.setDone(b);
                TaskDatabase.getInstance(context).getTaskDao().update(task);
                listener.screenRefresher();
            }
        });
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, Single_Item_Activity.class);

            intent.putExtra("itemID",task.getId());
//            intent.putExtra("title",task.getTitle());
//            intent.putExtra("desc",task.getDescription());
//            intent.putExtra("priority",task.getPriority());
//            intent.putExtra("time",task.getTime());
//            intent.putExtra("date",task.getDate());
//            intent.putExtra("isPer",task.isCategoryPersonal());
//            intent.putExtra("isOff",task.isCategoryOffice());
//            intent.putExtra("isSho",task.isCategoryShopping());
//            intent.putExtra("isHealth",task.isCategoryHealth());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
