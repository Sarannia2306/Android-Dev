package com.example.fitness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Me_Fragment extends Fragment {

    private TextView Name;
    private TextView Age;
    private TextView DOB;
    private TextView Weight;
    private TextView Height;
    private TextView Gender;
    private TextView BMI;
    private TextView Privacy;
    private ImageView logoutIcon;

    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        Name = view.findViewById(R.id.name);
        Age = view.findViewById(R.id.age);
        DOB = view.findViewById(R.id.dob);
        Weight = view.findViewById(R.id.weight);
        Height = view.findViewById(R.id.height);
        Gender = view.findViewById(R.id.gender);
        BMI = view.findViewById(R.id.BMI);
        Privacy = view.findViewById(R.id.Privacy);
        logoutIcon = view.findViewById(R.id.logoutIcon);

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();
        Log.d("Firebase", "Users: " + user_id);

        // Reference to "User Details" under the current user
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("User Details");

        // Add a ValueEventListener to retrieve and update data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Use the UserInfoClass to map the data from the database
                    UserInfoClass userInfo = dataSnapshot.getValue(UserInfoClass.class);

                    if (userInfo != null) {
                        // Update the TextViews with the retrieved data
                        Name.setText("Name: " + userInfo.getName());
                        Age.setText("Age: " + userInfo.getAge());
                        DOB.setText("DOB: " + userInfo.getDOB());
                        Weight.setText("Weight: " + userInfo.getWeight());
                        Height.setText("Height: " + userInfo.getHeight());
                        Gender.setText("Gender: " + userInfo.getGender());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error: " + databaseError.getMessage());
            }
        });

        BMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on IBM TextView
                Intent intent = new Intent(getActivity(), BMI_calc.class);
                startActivity(intent);
            }
        });

        Privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on Privacy TextView
                Intent intent = new Intent(getActivity(), Privacy_Activity.class);
                startActivity(intent);
            }
        });

        ImageButton reminderButton = view.findViewById(R.id.reminder);
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReminderActivity();
            }
        });

        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
            });


        return view;
    }

    private void openReminderActivity() {
        Intent intent = new Intent(getActivity(), ReminderActivity.class);
        startActivity(intent);
    }

}
