package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class WorkoutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        ImageView absImageView = view.findViewById(R.id.abs);
        ImageView legsImageView = view.findViewById(R.id.legs);
        ImageView armsImageView = view.findViewById(R.id.arms);
        ImageView fullbodyImageView = view.findViewById(R.id.fullbody);

        String absImageURL = "https://firebasestorage.googleapis.com/v0/b/fitness-ee9af.appspot.com/o/Abs%2FAbsWallpaper.jpg?alt=media&token=18025271-3a71-46f2-aa31-4bbd6578c30f";
        String armsImageURL = "https://firebasestorage.googleapis.com/v0/b/fitness-ee9af.appspot.com/o/Arms%2Farmswallpaper.jpg?alt=media&token=ed45ec62-6d5d-431d-aa85-5516d9ede50d";
        String fullbodyImageURL = "https://firebasestorage.googleapis.com/v0/b/fitness-ee9af.appspot.com/o/FullBody%2Ffullbodywallpaper.jpg?alt=media&token=a4e19fac-25c8-4fb4-a887-e3253e6e3706";
        String legsImageURL = "https://firebasestorage.googleapis.com/v0/b/fitness-ee9af.appspot.com/o/Legs%2Flegswallpaper.jpg?alt=media&token=e006e9b1-b1a0-4ae7-8a6b-2792658b1cbc";

        // Use Picasso to load and display the images in the ImageViews
        Picasso.get().load(absImageURL).into(absImageView);
        Picasso.get().load(armsImageURL).into(armsImageView);
        Picasso.get().load(fullbodyImageURL).into(fullbodyImageView);
        Picasso.get().load(legsImageURL).into(legsImageView);


        absImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click action for Abs
                openAbsWorkout();
            }
        });

        legsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click action for Legs
                openLegsWorkout();
            }
        });

        armsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click action for Arms
                openArmsWorkout();
            }
        });

        fullbodyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click action for Full Body
                openFullBodyWorkout();
            }
        });

        return view;
    }

    private void openAbsWorkout() {
        // Create an Intent to navigate to the Abs workout page
        // You should define AbsActivity as an Activity in your AndroidManifest.xml
        Intent intent = new Intent(getActivity(), AbsActivity.class);
        startActivity(intent);
    }

    private void openLegsWorkout() {
        // Create an Intent to navigate to the Legs workout page
        Intent intent = new Intent(getActivity(), LegsActivity.class);
        startActivity(intent);
    }

    private void openArmsWorkout() {
        // Create an Intent to navigate to the Arms workout page
        Intent intent = new Intent(getActivity(), ArmsActivity.class);
        startActivity(intent);
    }

    private void openFullBodyWorkout() {
        // Create an Intent to navigate to the Full Body workout page
        Intent intent = new Intent(getActivity(), FullBodyActivity.class);
        startActivity(intent);
    }
}
