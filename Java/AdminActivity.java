package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    private EditText passwordEditText;
    private EditText adminIdEditText;
    private Button adminLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        passwordEditText = findViewById(R.id.Password);
        adminIdEditText = findViewById(R.id.AdminID);
        adminLoginButton = findViewById(R.id.AdminLogin);

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the login button click event here

                String password = passwordEditText.getText().toString();
                String adminId = adminIdEditText.getText().toString();

                // You can add login logic here, such as verifying the ID and password
                openHomePage();
            }
        });
    }

    private void openHomePage() {
        Intent intent = new Intent(this, HomeFragment.class); // Use the appropriate activity class
        startActivity(intent);
    }

    private boolean isLoginSuccessful(String email, String password) {

        return email.equals("MY230601") && password.equals("24gym@");
    }
    private void showError(String errorMessage) {
        adminIdEditText.setError(errorMessage);
        passwordEditText.setError(errorMessage);
    }

}
