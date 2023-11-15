package com.example.fitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Login extends AppCompatActivity {
    private EditText EmailAddress;
    private EditText Password;
    private Button btnLogin;
    private TextView Register;
    private TextView btnAdmin;
    private ImageView app;
    private SharedPreferences sharedPreferences;
    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EmailAddress = findViewById(R.id.EmailAddress);
        Password = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.btnLogin);
        Register = findViewById(R.id.Register);
        btnAdmin = findViewById(R.id.btnAdmin);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);
        app = findViewById(R.id.app);

        String appURL ="https://firebasestorage.googleapis.com/v0/b/fitness-ee9af.appspot.com/o/app.jpg?alt=media&token=5fa49023-d4bc-4739-a41a-58fde3e2ff63";
        Picasso.get().load(appURL).into(app);

        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the email and password
                String Email = EmailAddress.getText().toString();
                String password = Password.getText().toString();

                // Check if "Remember Me" is checked
                boolean shouldRemember = rememberMeCheckBox.isChecked();

                if (shouldRemember) {
                    // Save the email and password
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("rememberMe", true);
                    editor.putString("EmailAddress", Email);
                    editor.putString("Password", password);
                    editor.apply();
                } else {
                    // Clear the saved data
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("rememberMe", false);
                    editor.remove("EmailAddress");
                    editor.remove("Password");
                    editor.apply();
                }

                if (isLoginSuccessful(Email, password)) {
                    openHomePage();
                } else {
                    showError("Login failed. Please check your credentials.");
                }
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistrationPage();
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdminPage();
            }
        });
    }

    private void openHomePage() {
        Intent intent = new Intent(this, HomeFragment.class);
        startActivity(intent);
    }

    private boolean isLoginSuccessful(String email, String password) {

        return true;
    }

    private void showError(String errorMessage) {
        EmailAddress.setError(errorMessage);
        Password.setError(errorMessage);
    }

    public void openRegistrationPage() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void openAdminPage() {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}
