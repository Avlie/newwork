package com.example.newwork;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.CharSequenceTransformation;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "my_channel_id";
    private static final int NOTIFICATION_ID = 1;
    private static int ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Notificationhelper.createNotificationChannel(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }

            Button button = findViewById(R.id.button);
            button.setOnClickListener(v -> { Notificationhelper.setNotification(ID, Notificationhelper.CHANNEL_ID_LOW, this, "Nowe", "ВСЕ ПЛОХА", 1);
                ID++;});
            Button button1 = findViewById(R.id.button1);
            button1.setOnClickListener(v -> { Notificationhelper.setNotification(ID, Notificationhelper.CHANNEL_ID_DEFAULT,this, "Nowe", "ВСЕ ПЛОХА МИР ВЗАРВЕТСА", 2);
                    ID++;});
            Button ButtonPicture = findViewById(R.id.PictureButton);
            ButtonPicture.setOnClickListener(v->{ Notificationhelper.setNotification(ID, Notificationhelper.CHANNEL_ID_HIGH,this, "Nowe", "ВСЕ ПЛОХА МИР ВЗАРВЕТСА", 3);
                ID++;});
            Button Addline = findViewById(R.id.Addline);
            Addline.setOnClickListener(v->{ Notificationhelper.setNotification(ID,Notificationhelper.CHANNEL_ID_HIGH,this, "Nowe", "ВСЕ ПЛОХА МИР ВЗАРВЕТСА", 3);
                ID++;});
        }

    }
    private void createnotif(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name = "Kanal powiadomien";
            String description = "Opis Kanalu powiadonienia";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel chanel = new NotificationChannel(CHANNEL_ID, name, importance);
            chanel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(chanel);
        }
    }

    private void sendNotification() {
        Intent intent =new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.g).setContentTitle("nowe").setContentText("ВСЕ ПЛОХА, МИР ВЗАРВЕТСА").setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent).setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

    }
    private void createnotiflong(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name = "Kanal powiadomien";
            String description = "Opis Kanalu powiadonienia";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel chanel = new NotificationChannel(CHANNEL_ID, name, importance);
            chanel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(chanel);

        }
    }
    private void sendNotificationlong() {
        Intent intent =new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.g).setContentTitle("nowe").setContentText("ВСЕ ПЛОХА, МИР ВЗАРВЕТСА").setStyle(new NotificationCompat.BigTextStyle().bigText("Lorem2")).setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent).setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

    }
    private void sendNotificationPicture(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dice);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS},1);
                return;
            }


            Intent intent = new Intent(MainActivity.this, MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.dice2)
                            .setContentTitle("Powiadomienie Obraz 3TPE")
                            .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);
            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(this);
            notificationManager.notify(1,builder.build());
        }
    }
}



