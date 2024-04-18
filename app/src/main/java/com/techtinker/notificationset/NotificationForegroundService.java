package com.techtinker.notificationset;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Calendar;

public class NotificationForegroundService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if (intent.getAction() != null && intent.getAction().equals("ACTION_START_FOREGROUND_SERVICE")) {
                // Schedule your notification here
                scheduleNotification();
            }
        }
        return START_STICKY;
    }

    private void scheduleNotification() {
        // Set the target date and time
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.set(Calendar.YEAR, 2024);
        targetCalendar.set(Calendar.MONTH, Calendar.APRIL); // Month is zero-based
        targetCalendar.set(Calendar.DAY_OF_MONTH, 18);
        targetCalendar.set(Calendar.HOUR_OF_DAY, 11); // 24-hour format
        targetCalendar.set(Calendar.MINUTE, 11);
        targetCalendar.set(Calendar.SECOND, 1);

        // Get the context
        Context context = getApplicationContext();

        // Create an intent for the BroadcastReceiver
        Intent intent = new Intent(context, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Schedule the alarm
        android.app.AlarmManager alarmManager = (android.app.AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setExact(android.app.AlarmManager.RTC_WAKEUP, targetCalendar.getTimeInMillis(), pendingIntent);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}