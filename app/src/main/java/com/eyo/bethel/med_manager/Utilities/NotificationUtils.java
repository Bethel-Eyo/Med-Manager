package com.eyo.bethel.med_manager.Utilities;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.eyo.bethel.med_manager.HomeActivity;
import com.eyo.bethel.med_manager.Medications.MedNotifierActivity;
import com.eyo.bethel.med_manager.Notifications.MedicationReminderIntentService;
import com.eyo.bethel.med_manager.Notifications.NotifierTasks;
import com.eyo.bethel.med_manager.R;

public class NotificationUtils {
    /**
     * This pending intent id is used to uniquely reference the pending intent
     */
    private static final int MEDICATION_REMINDER_PENDING_INTENT_ID = 1417;

    /*This notification ID can be used to access our notification after we've displayed it. This
     * can be handy when we need to cancel the notification, or perhaps update it.*/
    private static final int MEDICATION_REMINDER_NOTIFICATION_ID = 2138;
    /**
     * This notification channel id is used to link notifications to this channel
     */
    private static final String MEDICATION_REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 12;
    private static final int ACTION_TAKE_MEDICATION_PENDING_INTENT_ID = 3;

    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    private static NotificationCompat.Action ignoreNotificationAction(Context context) {
        Intent ignoreReminderIntent = new Intent(context, MedicationReminderIntentService.class);
        ignoreReminderIntent.setAction(NotifierTasks.ACTION_DISMISS_NOTIFICATION);
        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,
                "No, thanks.",
                ignoreReminderPendingIntent);
        return ignoreReminderAction;
    }

    private static NotificationCompat.Action takeMedicationAction(Context context) {
        Intent updateMedicationStatusIntent = new Intent(context, MedicationReminderIntentService.class);
        updateMedicationStatusIntent.setAction(NotifierTasks.ACTION_UPDATE_MEDICATION_STATUS);
        PendingIntent statusUpdatePendingIntent = PendingIntent.getService(
                context,
                ACTION_TAKE_MEDICATION_PENDING_INTENT_ID,
                updateMedicationStatusIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Action takeMedicationAction = new NotificationCompat.Action(R.drawable.ic_take_medication_black_24px,
                "I did it!",
                statusUpdatePendingIntent);
        return takeMedicationAction;
    }

    // to create pending intent that will start an activity
    private static PendingIntent contentIntent(Context context){
        Intent startActivityIntent = new Intent(context, MedNotifierActivity.class);
        // to wrap the explicit intent as a pending Intent
        return PendingIntent.getActivity(
                context,
                MEDICATION_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    // notification method
    public static void notifyUserToTakeMedication(Context context){
        // to get the notification manger
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    MEDICATION_REMINDER_NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.main_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        // to create the notification builder
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,MEDICATION_REMINDER_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_store_white_48pt)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.medication_reminder_notification_title))
                .setContentText(context.getString(R.string.medication_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.medication_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(takeMedicationAction(context))
                .addAction(ignoreNotificationAction(context))
                .setAutoCancel(true);

        // Set notification's priority to priority high
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        // pass in unique id for the notification and notification and notification.builder
        notificationManager.notify(MEDICATION_REMINDER_NOTIFICATION_ID, notificationBuilder.build());
    }

    // for notification icon
    private static Bitmap largeIcon(Context context){
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_med);
        return largeIcon;
    }
}
