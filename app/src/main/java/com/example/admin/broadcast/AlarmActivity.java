package com.example.admin.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;
    public static AlarmActivity instance() {
        return inst;
        }
        @Override
    public void onStart() {
        super.onStart();
        inst = this;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);

        alarmTextView = (TextView) findViewById(R.id.alarmText);

        final ToggleButton alarmToggle = (ToggleButton)findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmToggle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    if (alarmToggle.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Alarm On", Toast.LENGTH_SHORT).show();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY,
                                alarmTimePicker.getCurrentHour());
                        calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                        Intent myIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
                        pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, 0);
                        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                        } else {
                        alarmManager.cancel(pendingIntent);
                        Toast.makeText(getApplicationContext(), "Alarm Off", Toast.LENGTH_SHORT).show();
                        setAlarmText("");

                    }

                } catch (Exception ex) {
                    }
                    }
                    });
    }

    public void setAlarmText(String alarmText) {
         alarmTextView.setText(alarmText);

    }

}