package com.example.user.kaanbaba;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            sendNotigication("FireBaseMessage",remoteMessage.getData().toString());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
        }            sendNotigication("FireBaseMessage",remoteMessage.getData().toString());

    }
    private void sendNotigication(String title,String massege) {
        Intent i = new Intent(this,LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);

        long []pattern={500,500,500,500};

        NotificationCompat.Builder notificationBulider= new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(massege)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setStyle(new NotificationCompat.InboxStyle())
                .setContentIntent(pendingIntent);
        NotificationManager motificationManeger =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        motificationManeger.notify(0,notificationBulider.build());

    }
}
