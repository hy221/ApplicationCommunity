package com.applicationcommunity;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class NotificationService extends Service {
    private static final String TAG = "Service";
    private NotificationChannel channel;

    @Override
    public void onCreate() {
        super.onCreate();

        channel = new NotificationChannel(
                "service_status", "サービス状況",
                NotificationManager.IMPORTANCE_DEFAULT
        );
        channel.setVibrationPattern(new long[]{1000, 500, 1000, 500, 2000, 500});
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

}
