package com.example.fitness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Handle the alarm action
        Toast.makeText(context, "It's time for your exercise!", Toast.LENGTH_SHORT).show();
    }
}
