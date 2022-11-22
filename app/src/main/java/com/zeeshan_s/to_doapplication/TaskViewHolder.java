package com.zeeshan_s.to_doapplication;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    TextView title, date, time, cat_personal, cat_office, cat_shopping, cat_health;
    CheckBox checkBox;
    ImageView priority;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.taskTitle);
        date = itemView.findViewById(R.id.taskDate);
        time = itemView.findViewById(R.id.taskTime);
        cat_personal = itemView.findViewById(R.id.taskCategoryPersonal);
        cat_office = itemView.findViewById(R.id.taskCategoryOffice);
        cat_shopping = itemView.findViewById(R.id.taskCategoryShopping);
        cat_health = itemView.findViewById(R.id.taskCategoryHealth);
        checkBox = itemView.findViewById(R.id.taskDoneCheckBox);
        priority = itemView.findViewById(R.id.taskPriority);


    }
}
