package com.eyo.bethel.med_manager.Notifications;

import android.content.Context;

import com.eyo.bethel.med_manager.Utilities.NotificationUtils;

public class NotifierTasks {
    public static final String ACTION_UPDATE_MEDICATION_STATUS = "update-medication-status";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    static final String ACTION_MEDICATION_REMINDER = "medication-reminder";


    public static void executeTask(Context context, String action) {
        if (ACTION_UPDATE_MEDICATION_STATUS.equals(action)) {
            incrementWaterCount(context);
        } else if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        } else if (ACTION_MEDICATION_REMINDER.equals(action)) {
            issueMedicationReminder(context);
        }
    }

    private static void incrementWaterCount(Context context) {
        NotificationUtils.clearAllNotifications(context);
    }

    private static void issueMedicationReminder(Context context) {
        NotificationUtils.notifyUserToTakeMedication(context);
    }
}
