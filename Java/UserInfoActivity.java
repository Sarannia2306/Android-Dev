package com.example.fitness;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserInfoActivity extends AppCompatActivity {

    private Button getStartedButton;
    private Button selectDayButton;
    private Button selectTimeButton;
    private TextView selectedDayTimeTextView;
    private Switch reminderSwitch;

    private Calendar selectedCalendar;
    private boolean isReminderEnabled;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String REMINDER_KEY = "reminderKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info); // Set the user_info layout

        getStartedButton = findViewById(R.id.getStartedButton);
        selectDayButton = findViewById(R.id.selectDayButton);
        selectTimeButton = findViewById(R.id.selectTimeButton);
        selectedDayTimeTextView = findViewById(R.id.selectedDayTimeTextView);
        reminderSwitch = findViewById(R.id.reminder);

        selectedCalendar = Calendar.getInstance();

        reminderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isReminderEnabled = isChecked;
            saveReminderState(isReminderEnabled);
        });

        // Load the reminder state from SharedPreferences
        isReminderEnabled = loadReminderState();
        reminderSwitch.setChecked(isReminderEnabled);

        selectDayButton.setOnClickListener(view -> showDatePicker());

        selectTimeButton.setOnClickListener(view -> showTimePicker());

        getStartedButton.setOnClickListener(view -> {
            setReminder();
            // Handle the click action to switch to FragmentsActivity
            Intent intent = new Intent(UserInfoActivity.this, Navigation_bottom.class);
            startActivity(intent);
        });
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (datePicker, year, month, day) -> {
                    selectedCalendar.set(year, month, day);
                    updateSelectedDayTimeText();
                },
                selectedCalendar.get(Calendar.YEAR),
                selectedCalendar.get(Calendar.MONTH),
                selectedCalendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (timePicker, hourOfDay, minute) -> {
                    selectedCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedCalendar.set(Calendar.MINUTE, minute);
                    updateSelectedDayTimeText();
                },
                selectedCalendar.get(Calendar.HOUR_OF_DAY),
                selectedCalendar.get(Calendar.MINUTE),
                false // 24-hour format
        );

        timePickerDialog.show();
    }

    private void updateSelectedDayTimeText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault());
        String formattedDateTime = dateFormat.format(selectedCalendar.getTime());
        selectedDayTimeTextView.setText("Selected Date and Time: " + formattedDateTime);
    }

    private void setReminder() {
        if (isReminderEnabled) {
            // Get the time in milliseconds
            long reminderTimeMillis = selectedCalendar.getTimeInMillis();

            //Intent for the AlarmReceiver class
            Intent intent = new Intent(this, AlarmReceiver.class);

            // Create a PendingIntent to be triggered when the alarm goes off
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );

            // Get the AlarmManager service
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            // Set the alarm to start at the specified time
            alarmManager.set(AlarmManager.RTC_WAKEUP, reminderTimeMillis, pendingIntent);

            Toast.makeText(this, "Reminder set successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveReminderState(boolean isEnabled) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(REMINDER_KEY, isEnabled);
        editor.apply();
    }

    private boolean loadReminderState() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(REMINDER_KEY, false);
    }
}
