package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class MeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);

        TextView ibmTextView = rootView.findViewById(R.id.IBM);
        TextView privacyTextView = rootView.findViewById(R.id.Privacy);

        ibmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for IBM
                startActivity(new Intent(getActivity(), BMI_calc.class));
            }
        });

        privacyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event for Privacy Policy
                startActivity(new Intent(getActivity(), Privacy_Activity.class));
            }
        });

        // Other initialization code

        return rootView;
    }
}
