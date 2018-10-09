package com.example.admin.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class AlarmReceiver extends BroadcastReceiver {

    MediaPlayer myPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            AlarmActivity inst = AlarmActivity.instance();
            inst.setAlarmText("Alarm! Wake up! Wake up!");
            /*myPlayer = MediaPlayer.create(context,R.raw.kalimba);
            myPlayer.setLooping(true);
            myPlayer.start();
*/

           Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if (alarmUri == null) {
                alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                }

            Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
            ringtone.play();
            ComponentName comp = new ComponentName(context.getPackageName(), AlarmService.class.getName());
            intent.setComponent(comp);
            startWakefulService(context, (intent.setComponent(comp)));
            setResultCode(Activity.RESULT_OK);

        } catch (Exception ex) {



        }

    }

}