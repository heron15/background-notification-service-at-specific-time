package com.techtinker.notificationset;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;

public class ReminderBroadcast extends BroadcastReceiver {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        // Check if POST_NOTIFICATIONS permission is granted
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, show the notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyLemubit")
                    .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                    .setContentTitle("Remind me")
                    .setContentText("Hey Hello")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(200, builder.build());
        }
    }
}
