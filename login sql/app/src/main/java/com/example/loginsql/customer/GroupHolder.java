package com.example.loginsql.customer;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginsql.R;

public class GroupHolder extends RecyclerView.ViewHolder {

    TextView grouptagname;
    ImageView group_img;

    public GroupHolder(@NonNull View itemView) {
        super(itemView);
        grouptagname=itemView.findViewById(R.id.group_tag_name_id);
        group_img=itemView.findViewById(R.id.group_img_id);
    }
}
