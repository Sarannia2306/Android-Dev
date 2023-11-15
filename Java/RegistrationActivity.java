package com.example.fitness;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    private EditText emailAddress;
    private EditText password;
    private EditText rePassword;
    private EditText name;
    private EditText age;
    private EditText weight;
    private EditText height;
    private RadioGroup gender;
    private RadioButton maleRadioBtn;
    private RadioButton femaleRadioBtn;
    private RadioButton otherRadioBtn;
    private EditText dateOfBirthEditText;
    private Button chooseDateButton;
    private Button registerButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserDetails");

        emailAddress = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.Password);
        rePassword = findViewById(R.id.RePassword);
        name = findViewById(R.id.Name);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        gender = findViewById(R.id.genderRadioGroup);
        maleRadioBtn = findViewById(R.id.maleRadioBtn);
        femaleRadioBtn = findViewById(R.id.femaleRadioBtn);
        otherRadioBtn = findViewById(R.id.otherRadioBtn);
        dateOfBirthEditText = findViewById(R.id.dateOfBirthEditText);
        chooseDateButton = findViewById(R.id.chooseDateButton);
        registerButton = findViewById(R.id.btnRegister);

        chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = emailAddress.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String confirmPassword = rePassword.getText().toString().trim();
        String userName = name.getText().toString().trim();
        String userAge = age.getText().toString().trim();
        String userWeight = weight.getText().toString().trim();
        String userHeight = height.getText().toString().trim();
        String userGender = getSelectedGender();
        String userDateOfBirth = dateOfBirthEditText.getText().toString().trim();

        if (!validateInputs(email, userPassword, confirmPassword, userName, userAge, userWeight, userHeight, userGender, userDateOfBirth)) {
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                            if (currentUser != null) {
                                String userId = currentUser.getUid();

                                // Create a user map to be stored in Realtime Database
                                Map<String, Object> userMap = new HashMap<>();
                                userMap.put("email", email);
                                userMap.put("name", userName);
                                userMap.put("age", userAge);
                                userMap.put("weight", userWeight);
                                userMap.put("height", userHeight);
                                userMap.put("gender", userGender);
                                userMap.put("dateOfBirth", userDateOfBirth);

                                // Add user details to Realtime Database
                                databaseReference.child(userId)
                                        .setValue(userMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                                    openUserInfoPage();
                                                } else {
                                                    Toast.makeText(RegistrationActivity.this, "Failed to add user details to Realtime Database", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validateInputs(String email, String password, String confirmPassword,
                                   String name, String age, String weight, String height,
                                   String gender, String dateOfBirth) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty() ||
                gender.isEmpty() || dateOfBirth.isEmpty()) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Add more validation logic if needed

        return true;
    }

    private String getSelectedGender() {
        int selectedId = gender.getCheckedRadioButtonId();

        if (selectedId == R.id.maleRadioBtn) {
            return "Male";
        } else if (selectedId == R.id.femaleRadioBtn) {
            return "Female";
        } else if (selectedId == R.id.otherRadioBtn) {
            return "Other";
        } else {
            return "";
        }
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateOfBirthEditText.setText(String.format("%02d/%02d/%d", day, month + 1, year));
                    }
                },
                year, month, day
        );

        datePickerDialog.show();
    }

    private void openUserInfoPage() {
        Intent intent = new Intent(this, UserInfoActivity.class);
        startActivity(intent);
    }
}
