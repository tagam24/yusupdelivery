package com.tagam24.tagam.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.google.firebase.messaging.RemoteMessage;
import com.tagam24.tagam.MainActivity;
import com.tagam24.tagam.R;


/**
 * Created by User on 15.01.2019.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    private NotificationChannel mChannel;
    private NotificationManager notifManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) notifey(remoteMessage.getData().get("message")); else
            shownof(remoteMessage.getData().get("message"));
     Log.d("key","213"+remoteMessage.getData());
    }
    public void shownof(String message){
        Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent p=PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this).setAutoCancel(true).setContentTitle("Tagam24")
                .setContentText(message).setContentIntent(p).setSound(defaultSoundUri);
        NotificationManager  manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    void notifey(String message){
        Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent p=PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        final NotificationManager mNotific=
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        CharSequence name="Ragav";
        String desc="this is notific";
        int imp=NotificationManager.IMPORTANCE_HIGH;
        final String ChannelID="my_channel_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel mChannel = new NotificationChannel(ChannelID, name,
                    imp);
            mChannel.setDescription(message);
            mChannel.setLightColor(Color.CYAN);
            mChannel.canShowBadge();
            mChannel.setShowBadge(true);
            mNotific.createNotificationChannel(mChannel);
        }

        final int ncode=101;

        String Body="This is testing notific";
        Notification n= new Notification.Builder(this,ChannelID)
                .setContentTitle("Tagam24")
                .setContentText(message)
                .setBadgeIconType(R.mipmap.ic_launcher_round)
                .setNumber(1).setSound(defaultSoundUri)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true).setContentIntent(p)
                .build();
        mNotific.notify(ncode, n);
    }
}
