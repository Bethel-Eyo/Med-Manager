package com.eyo.bethel.med_manager.Medications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.eyo.bethel.med_manager.Notifications.ReminderUtilities;
import com.eyo.bethel.med_manager.R;
import com.eyo.bethel.med_manager.Utilities.Keys;
import com.eyo.bethel.med_manager.data.UserDataContract;

import java.text.ParseException;
import java.util.Date;


public class MedNotifierActivity extends AppCompatActivity {
    SQLiteDatabase mDb;
    Cursor mCursor;
    Toolbar toolbar;
    TextView mDescription, mTabsPerIntake, mFrequency, mStartDate, mEndDate;
    String nFrequency, nStartDate, nEndDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.med_details);

        toolbar = (Toolbar) findViewById(R.id.med_details_toolbar);
        setSupportActionBar(toolbar);
        mDescription = (TextView) findViewById(R.id.details_description);
        mTabsPerIntake = (TextView) findViewById(R.id.details_tabs_per_intake);
        mFrequency = (TextView) findViewById(R.id.details_times_per_day);
        mStartDate = (TextView) findViewById(R.id.details_start_date);
        mEndDate = (TextView) findViewById(R.id.details_end_date);

        mCursor = mDb.rawQuery("SELECT * FROM " + UserDataContract.MedParameters.TABLE_NAME, null);
        while (mCursor.moveToNext()) {
            nFrequency = mCursor.getString(mCursor
                    .getColumnIndex(UserDataContract.MedParameters.TIMES_PER_DAY));
            nStartDate = mCursor.getString(mCursor
                    .getColumnIndex(UserDataContract.MedParameters.START_DATE));
            nEndDate = mCursor.getString(mCursor
                    .getColumnIndex(UserDataContract.MedParameters.END_DATE));
            int id = mCursor.getInt(mCursor.getColumnIndex(UserDataContract.MedParameters._ID));
            int frequency = Integer.parseInt(nFrequency);
            checkExpiryDate(nEndDate);
            if (frequency == 1){
                ReminderUtilities.scheduleMedicationNotifier1(this);
            } else if (frequency == 2){
                ReminderUtilities.scheduleMedicationNotifier2(this);
            } else if (frequency == 3){
                ReminderUtilities.scheduleMedicationNotifier3(this);
            }
            pullData(id);
        }

    }

    public void pullData(int id){
        String sql = "SELECT * FROM "+ UserDataContract.MedParameters.TABLE_NAME+ " WHERE "
                + UserDataContract.MedParameters._ID + " = " + id;

        mCursor = mDb.rawQuery(sql, null);

        String drugName = mCursor.getString(mCursor.getColumnIndex(UserDataContract
                .MedParameters.DRUG_NAME));
        String description = mCursor.getString(mCursor.getColumnIndex(UserDataContract
                .MedParameters.DESCRIPTION));
        String tabsPerIntake = mCursor.getString(mCursor.getColumnIndex(UserDataContract
                .MedParameters.TABLETS_PER_INTAKE));
        String frequency = mCursor.getString(mCursor.getColumnIndex(UserDataContract
                .MedParameters.TIMES_PER_DAY));
        String startDate = mCursor.getString(mCursor.getColumnIndex(UserDataContract
                .MedParameters.START_DATE));
        String endDate = mCursor.getString(mCursor.getColumnIndex(UserDataContract
                .MedParameters.END_DATE));

        toolbar.setTitle(drugName);
        mDescription.setText(description);
        mTabsPerIntake.setText(tabsPerIntake);
        mFrequency.setText(frequency);
        mStartDate.setText(startDate);
        mEndDate.setText(endDate);
    }

    public void checkExpiryDate(String nDate){
        try {
            Date date = new SimpleDateFormat(Keys.UI.DATE_FORMAT).parse(nDate);
            if (new Date().before(date)){
                String mySql = "DELETE FROM " + UserDataContract.MedParameters.TABLE_NAME
                        + " WHERE " + UserDataContract.MedParameters.END_DATE + " = " + nDate;
                mCursor = mDb.rawQuery(mySql, null);
            }
        } catch (ParseException e) {

        }
    }
}
