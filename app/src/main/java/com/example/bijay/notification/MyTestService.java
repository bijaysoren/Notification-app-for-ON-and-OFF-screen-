package com.example.bijay.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;


/**
 * Created by bijay on 11/3/16.
 */
public class MyTestService extends Service {

    BroadcastReceiver mReceiver=null;

    @Override
    public void onCreate() {
        super.onCreate();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);

    }

    @Override
    public void onStart(Intent intent, int startId) {

        boolean screenOn = false;

        try{

            screenOn = intent.getBooleanExtra("screen_state", false);

        }catch(Exception e){}


        if (!screenOn) {

            Notify("Hey ","welcome back");

        } else {


        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {

        Log.i("ScreenOnOff", "Service  distroy");
        if(mReceiver!=null)
            unregisterReceiver(mReceiver);

    }

    private void Notify(String notificationTitle, String notificationMessage){

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.drawing)
                        .setContentTitle(notificationTitle)
                        .setContentText( notificationMessage);


        Intent resultIntent = new Intent(this, ResultActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        int mNotificationId = 001;


        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());


    }




}