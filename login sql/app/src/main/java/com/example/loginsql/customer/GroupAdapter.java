package com.example.loginsql.customer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginsql.R;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupHolder> {

    ArrayList<model> glist;

    public GroupAdapter(ArrayList<model> glist) {
        this.glist = glist;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View gview = layoutInflater.inflate(R.layout.group_card, parent, false);

        return new GroupHolder(gview);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {

        holder.grouptagname.setText(glist.get(position).getG_tag());
        holder.group_img.setImageResource(glist.get(position).getG_img());

    }

    @Override
    public int getItemCount() {
        return glist.size();
    }
}
