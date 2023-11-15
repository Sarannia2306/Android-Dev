package com.example.fitness;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.WorkoutAdapter;
import com.example.fitness.WorkoutModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AbsActivity extends AppCompatActivity {
    private List<WorkoutModel> workoutList = new ArrayList<>();
    private RecyclerView recyclerView;
    private WorkoutAdapter adapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abs);

        recyclerView = findViewById(R.id.recyclerView);

        // Initialize the adapter
        adapter = new WorkoutAdapter(workoutList);
        recyclerView.setAdapter(adapter);

        // Set the layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the database reference for AbsWorkout
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("AbsWorkout");

        // Retrieve data from Firebase and populate workoutList
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                workoutList.clear(); // Clear existing data
                for (DataSnapshot workoutSnapshot : dataSnapshot.getChildren()) {
                    String name = workoutSnapshot.child("Name").getValue(String.class);
                    String description = workoutSnapshot.child("Description").getValue(String.class);
                    String imageUrl = workoutSnapshot.child("Pic").getValue(String.class);

                    WorkoutModel workout = new WorkoutModel(name, description, imageUrl);
                    workoutList.add(workout);
                }

                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle any errors here
            }
        });
    }
}
