package com.example.fitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminViewUserMyAdapter extends RecyclerView.Adapter<AdminViewUserMyAdapter.AdminViewUserMyViewHolder> {

    private final Context context;
    private final List<AdminViewUserDataClass> dataList;

    public AdminViewUserMyAdapter(Context context, List<AdminViewUserDataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AdminViewUserMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_item, parent, false);
        return new AdminViewUserMyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewUserMyViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getName());
        holder.age.setText(dataList.get(position).getAge());
        holder.height.setText(dataList.get(position).getHeight());
        holder.weight.setText(dataList.get(position).getWeight());
        holder.dob.setText(dataList.get(position).getDob());
        holder.gender.setText(dataList.get(position).getGender());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class AdminViewUserMyViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, height, weight, dob, gender;
        CardView adminviewuser;

        public AdminViewUserMyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            weight = itemView.findViewById(R.id.weight);
            height = itemView.findViewById(R.id.height);
            gender = itemView.findViewById(R.id.gender);
            dob = itemView.findViewById(R.id.dob);
            adminviewuser = itemView.findViewById(R.id.adminviewuser);
        }
    }
}
