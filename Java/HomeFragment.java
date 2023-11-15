package com.example.fitness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private CalendarView calendarView;
    private TextView Welcome;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        Welcome = view.findViewById(R.id.Welcome);
        imageView = view.findViewById(R.id.imageView2);

        // You can set up behavior and functionality for these views as needed

        return view;
    }
}
