package ru.moskva.fm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class PlayService extends Service {

    private NotificationManager mNM;
    static final String TAG = "Moskva.fm";

    static final int NOTIFICATION = R.string.app_name;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {

        Log.d(TAG, "PlayService::onStart");

        Toast toast = Toast.makeText(this, R.string.start_service_toast, Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.show();
    }

    @Override
    public void onCreate() {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        showNotification();
    }

    @Override
    public void onDestroy() {

        Log.d(TAG, "PlayService::onDestroy");

        Toast toast = Toast.makeText(this, R.string.stop_service_toast, Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.show();

        mNM.cancel(NOTIFICATION);

    }

    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = getText(R.string.app_name);

        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(R.drawable.icon, text,
                System.currentTimeMillis());

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, ListenRadio.class), 0);

        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(this, getText(R.string.start_service_toast),
                text, contentIntent);

        // Send the notification.
        mNM.notify(NOTIFICATION, notification);
    }


}
