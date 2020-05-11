package com.example.notificationtryandpractice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import static com.example.notificationtryandpractice.MyNotificationManager.NOTIFICATION_CHANNEL_ID;

public class MyFirebaseInstantIdService extends FirebaseMessagingService {
    private MyNotificationManager myNotificationManager;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {
            Log.d("data", "Message data payload: " + remoteMessage.getData());
        }  if (remoteMessage.getNotification() != null) {
            Log.d("notification", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            notifyUser(remoteMessage.getFrom(),remoteMessage.getNotification().getBody());

            /**
             * ekhan theke ami server e data send korte parbo hote pare seta amer nijer othoba onno  karo
             */
        }
    }

    private void notifyUser(String title,String notification){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {//this is a notification channel for device notification
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "kibria", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("this is my channel Description");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }

     MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext());
     myNotificationManager.showNotification(title,notification,new Intent(getApplicationContext(),MainActivity.class));

    }
}
