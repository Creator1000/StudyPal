package com.example.studypal;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class StudySessionScheduler extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_session_scheduler);

        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        Button scheduleButton = findViewById(R.id.scheduleButton);

        scheduleButton.setOnClickListener(v -> scheduleStudySession());
    }

    @SuppressLint("ScheduleExactAlarm")
    private void scheduleStudySession() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                timePicker.getHour(), timePicker.getMinute(), 0);

        Intent intent = new Intent(this, StudySessionReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            try {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                Snackbar.make(findViewById(android.R.id.content), "Study session scheduled", Snackbar.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.make(findViewById(android.R.id.content), "Failed to schedule study session", Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(findViewById(android.R.id.content), "AlarmManager is null", Snackbar.LENGTH_LONG).show();
        }
    }
}
