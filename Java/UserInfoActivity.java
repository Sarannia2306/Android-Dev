package com.example.fitness;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

public class UserInfoActivity extends AppCompatActivity {

    Button dob;
    TextInputEditText name, age, weight, height;
    Spinner gender;
    Button btn_userinfosave;
    Calendar calendar = Calendar.getInstance();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth dbAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.user_info);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        gender = findViewById(R.id.gender);
        dob = findViewById(R.id.dob);
        btn_userinfosave = findViewById(R.id.btn_userinfoSave);

        dob.setText("Date of Birth");
        dob.setOnClickListener(v -> showDatePicker());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        btn_userinfosave.setOnClickListener(view -> {
            String Name = Objects.requireNonNull(name.getText()).toString();
            String Age = Objects.requireNonNull(age.getText()).toString();
            String Weight = Objects.requireNonNull(weight.getText()).toString();
            String Height = Objects.requireNonNull(height.getText()).toString();
            String Gender = gender.getSelectedItem().toString();
            String Dob = dob.getText().toString();

            AdminViewUserDataClass dataClass = new AdminViewUserDataClass(Name, Age, Height, Weight, Dob, Gender);
            String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

            FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("User Details")
                    .setValue(dataClass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(UserInfoActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(UserInfoActivity.this, Navigation_bottom.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(UserInfoActivity.this, Objects.requireNonNull(e.getMessage())
                            , Toast.LENGTH_SHORT).show());
        });
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth1) -> dob.setText
                        (dayOfMonth1 + "/" + (monthOfYear + 1) + "/" + year1), year, month, dayOfMonth);

        datePickerDialog.show();
    }
}

