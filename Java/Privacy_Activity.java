package com.example.fitness;



import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Privacy_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy);

        TextView privacyText = findViewById(R.id.Privacy);
        TextView privacyTitle = findViewById(R.id.Privacy_title);


    }
}
