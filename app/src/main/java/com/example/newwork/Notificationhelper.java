package com.example.newwork;
import static android.app.NotificationManager.IMPORTANCE_DEFAULT;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.newwork.MainActivity;

public class Notificationhelper {
    public static final String CHANNEL_ID_LOW = "";
    static final String CHANNEL_ID_DEFAULT = "";
    static final String CHANNEL_ID_HIGH = "";
    private static final String CHANNEL_NAME = "Kanał powiadomień";

    // private static final int NOTIFICATION_ID = 1;
    public static void createNotificationChannel(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            NotificationChannel channelLow = new NotificationChannel(CHANNEL_ID_LOW, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel channelDefault = new NotificationChannel(CHANNEL_ID_DEFAULT, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel channelHigh = new NotificationChannel(CHANNEL_ID_HIGH, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channelLow);
                notificationManager.createNotificationChannel(channelDefault);
                notificationManager.createNotificationChannel(channelHigh);
            }
        }
    }

    public static void setNotification(int NOTIFICATION_ID, String CHANNEL_ID, AppCompatActivity activity, String title, String message, int style) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }

        NotificationManager notificationManager = (NotificationManager)
                activity.getSystemService(activity.NOTIFICATION_SERVICE);
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        //    notificationManager.createNotificationChannel(channel);

        //}
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, CHANNEL_ID)
                .setSmallIcon(R.drawable.g).setContentTitle(title).setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);
        switch (style) {
            case 1:
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
                break;
            case 2:
                Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.dice);
                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setBigContentTitle(title));
                break;
            case 3:
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                inboxStyle.addLine(message);
                inboxStyle.addLine("DDODATKOWE INFORMACJE");
                builder.setStyle(inboxStyle);
                break;

        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}
