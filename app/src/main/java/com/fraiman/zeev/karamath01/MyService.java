package com.fraiman.zeev.karamath01;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        String notiTitle="Linear/Quadratic";
        String notiText="For start work press here";
        Intent intent=new Intent(MyService.this, Entrance.class);
        makeNotification(intent, notiTitle, notiText);
    }

    public void makeNotification(Intent intent, String notiTitle, String notiText) {

        PendingIntent contentIntent = PendingIntent.getActivity(MyService.this,
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyService.this);
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(notiTitle);
        builder.setContentText(notiText);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setDefaults(Notification.DEFAULT_LIGHTS);
        builder.setWhen(System.currentTimeMillis());
        Notification noti = builder.build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Toast.makeText(this, "Start notification service", Toast.LENGTH_SHORT).show();
        notificationManager.notify(1, noti);
    }

}
