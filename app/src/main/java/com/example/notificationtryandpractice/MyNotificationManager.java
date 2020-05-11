package com.example.notificationtryandpractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

public class MyNotificationManager {
    private Context ctx;
    private static final int NOTIFICATION_ID = 22;
    public static final String NOTIFICATION_CHANNEL_ID = "kibria";

    public MyNotificationManager(Context ctx) {
        this.ctx = ctx;
    }
    public void showNotification(String title, String notification, Intent intent){
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx,NOTIFICATION_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder= new NotificationCompat.Builder(ctx,NOTIFICATION_CHANNEL_ID);
        Notification mNotification = builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(false)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(notification)
                .setLargeIcon(BitmapFactory.decodeResource(ctx.getResources(),R.mipmap.ic_launcher))
                .build();
        mNotification.flags |=Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,mNotification);
    }
}
