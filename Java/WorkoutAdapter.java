package com.example.fitness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;


import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {
    private List<WorkoutModel> workoutList;

    public WorkoutAdapter(List<WorkoutModel> workoutList) {
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkoutModel workout = workoutList.get(position);
        holder.workoutNameTextView.setText(workout.getName());
        holder.workoutDescriptionTextView.setText(workout.getDescription());

        // Load and display the image using Picasso
        Picasso.get().load(workout.getImageUrl()).into(holder.workoutImageView);
    }


    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView workoutImageView;
        TextView workoutNameTextView;
        TextView workoutDescriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            workoutImageView = itemView.findViewById(R.id.workoutImageView);
            workoutNameTextView = itemView.findViewById(R.id.workoutNameTextView);
            workoutDescriptionTextView = itemView.findViewById(R.id.workoutDescriptionTextView);
        }
    }
}
