package com.zeeshan_s.to_doapplication;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    TextView selCategoryDate, selCategoryTime, selCategoryTitle, selCategoryName;
    CheckBox selCategoryCheckbox;
    ImageView selCategoryPriority;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        selCategoryDate = itemView.findViewById(R.id.selectedCategoryDate);
        selCategoryTime = itemView.findViewById(R.id.selectedCategoryTime);
        selCategoryTitle = itemView.findViewById(R.id.selectedCategoryTitle);
        selCategoryPriority = itemView.findViewById(R.id.selectedCategoryPriority);
        selCategoryName = itemView.findViewById(R.id.selectedCategoryName);
        selCategoryCheckbox = itemView.findViewById(R.id.selectedCategoryCheckBox);
    }
}
