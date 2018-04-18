package com.eyo.bethel.med_manager.Medications;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eyo.bethel.med_manager.R;
import com.eyo.bethel.med_manager.Utilities.Keys;
import com.eyo.bethel.med_manager.data.Medication;

public class MedicationActivity extends AppCompatActivity{
    View mFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        mFragmentContainer = findViewById(R.id.fragment);
        Fragment fragment = MedFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment,
                Keys.UI.MED_FRAGMENT_TAG).commit();
    }
}
