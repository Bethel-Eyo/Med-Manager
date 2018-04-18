package com.eyo.bethel.med_manager.Notifications;

import android.content.Context;

import android.support.annotation.NonNull;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

// this class holds the methods that schedules the jobs.
public class ReminderUtilities {
    private static final int ONE_FREQUENCY = 1;//1440
    private static final int TWO_FREQUENCY = 2;//720
    private static final int THREE_FREQUENCY = 3;//360
    private static final int ONE_REMINDER_INTERVAL_SECONDS = (int) (java.util.concurrent.TimeUnit.MINUTES.toSeconds(ONE_FREQUENCY));
    private static final int TWO_REMINDER_INTERVAL_SECONDS = (int) (java.util.concurrent.TimeUnit.MINUTES.toSeconds(TWO_FREQUENCY));
    private static final int THREE_REMINDER_INTERVAL_SECONDS = (int) (java.util.concurrent.TimeUnit.MINUTES.toSeconds(THREE_FREQUENCY));
    private static final int ONE_SYNC_FLEXTIME_SECONDS = ONE_REMINDER_INTERVAL_SECONDS;
    private static final int TWO_SYNC_FLEXTIME_SECONDS = TWO_REMINDER_INTERVAL_SECONDS;
    private static final int THREE_SYNC_FLEXTIME_SECONDS = THREE_REMINDER_INTERVAL_SECONDS;
    private static final String REMINDER_JOB_TAG = "medication_reminder_tag";

    private static boolean sInitialized;

    synchronized public static void scheduleMedicationNotifier1(@NonNull final Context context) {

        if (sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        /* Create the Job to periodically create reminders to take theirn medications */
        Job constraintReminderJob = dispatcher.newJobBuilder()
                /* The Service that will be used to write to preferences */
                .setService(MedicationNotifierFirebaseJobService.class)
                /*
                 * Set the UNIQUE tag used to identify this Job.
                 */
                .setTag(REMINDER_JOB_TAG)
                .setConstraints(Constraint.DEVICE_CHARGING)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        ONE_REMINDER_INTERVAL_SECONDS,
                        ONE_REMINDER_INTERVAL_SECONDS + ONE_SYNC_FLEXTIME_SECONDS))
                /*
                 * If a Job with the tag with provided already exists, this new job will replace
                 * the old one.
                 */
                .setReplaceCurrent(true)
                /* Once the Job is ready, call the builder's build method to return the Job */
                .build();

        /* Schedule the Job with the dispatcher */
        dispatcher.schedule(constraintReminderJob);

        /* The job has been initialized */
        sInitialized = true;
    }

    synchronized public static void scheduleMedicationNotifier2(@NonNull final Context context) {

        if (sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        /* Create the Job to periodically create reminders to take theirn medications */
        Job constraintReminderJob = dispatcher.newJobBuilder()
                /* The Service that will be used to write to preferences */
                .setService(MedicationNotifierFirebaseJobService.class)
                /*
                 * Set the UNIQUE tag used to identify this Job.
                 */
                .setTag(REMINDER_JOB_TAG)
                .setConstraints(Constraint.DEVICE_CHARGING)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        TWO_REMINDER_INTERVAL_SECONDS,
                        TWO_REMINDER_INTERVAL_SECONDS + TWO_SYNC_FLEXTIME_SECONDS))
                /*
                 * If a Job with the tag with provided already exists, this new job will replace
                 * the old one.
                 */
                .setReplaceCurrent(true)
                /* Once the Job is ready, call the builder's build method to return the Job */
                .build();

        /* Schedule the Job with the dispatcher */
        dispatcher.schedule(constraintReminderJob);

        /* The job has been initialized */
        sInitialized = true;
    }

    synchronized public static void scheduleMedicationNotifier3(@NonNull final Context context) {

        if (sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        /* Create the Job to periodically create reminders to take theirn medications */
        Job constraintReminderJob = dispatcher.newJobBuilder()
                /* The Service that will be used to write to preferences */
                .setService(MedicationNotifierFirebaseJobService.class)
                /*
                 * Set the UNIQUE tag used to identify this Job.
                 */
                .setTag(REMINDER_JOB_TAG)
                .setConstraints(Constraint.DEVICE_CHARGING)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        THREE_REMINDER_INTERVAL_SECONDS,
                        THREE_REMINDER_INTERVAL_SECONDS + THREE_SYNC_FLEXTIME_SECONDS))
                /*
                 * If a Job with the tag with provided already exists, this new job will replace
                 * the old one.
                 */
                .setReplaceCurrent(true)
                /* Once the Job is ready, call the builder's build method to return the Job */
                .build();

        /* Schedule the Job with the dispatcher */
        dispatcher.schedule(constraintReminderJob);

        /* The job has been initialized */
        sInitialized = true;
    }


}
