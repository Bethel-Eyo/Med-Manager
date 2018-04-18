package com.eyo.bethel.med_manager.Medications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.eyo.bethel.med_manager.R;
import com.eyo.bethel.med_manager.data.Medication;
import com.eyo.bethel.med_manager.data.UserDataContract;

public class MedDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView mDescription, mTabsPerIntake, mFrequency, mStartDate, mEndDate;
    Bundle extras;

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
        setUpViews();
    }

    public void setUpViews(){
        String drugName, description, tabsPerIntake, frequency, startDate, endDate;
        extras = getIntent().getExtras();
        drugName = extras.getString("drugName");
        description = extras.getString("description");
        tabsPerIntake = extras.getString("tabsPerIntake");
        frequency = extras.getString("frequency");
        startDate = extras.getString("startDate");
        endDate = extras.getString("endDate");

        toolbar.setTitle(drugName);
        mDescription.setText(description);
        mTabsPerIntake.setText(tabsPerIntake);
        mFrequency.setText(frequency);
        mStartDate.setText(startDate);
        mEndDate.setText(endDate);
    }

}
