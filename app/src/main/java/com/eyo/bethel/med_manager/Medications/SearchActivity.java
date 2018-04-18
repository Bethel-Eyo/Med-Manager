package com.eyo.bethel.med_manager.Medications;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.eyo.bethel.med_manager.data.UserDataContract.*;

import com.eyo.bethel.med_manager.R;
import com.eyo.bethel.med_manager.data.MedicalDbHelper;
import com.eyo.bethel.med_manager.data.Medication;
import com.eyo.bethel.med_manager.data.UserDataContract;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<Medication> filteredList;
    private MedicationAdapter medicationAdapter;
    SQLiteDatabase mDb;
    Toolbar toolbar;

    RecyclerView recyclerView;
    // initialize search area
    EditText searchBox;
    // initialize cursor
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        filteredList = new ArrayList<>();

        searchBox = (EditText) findViewById(R.id.search_area_edTxt);
        recyclerView = (RecyclerView) findViewById(R.id.search_recyclerView);
        toolbar = (Toolbar) findViewById(R.id.search_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.icons));
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MedicalDbHelper dbHelper = new MedicalDbHelper(this);
        mDb = dbHelper.getReadableDatabase();

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                recyclerView.setVisibility(View.VISIBLE);
                filter(editable.toString());
            }
        });
    }


    private void filter(String searchQuery) {
        filteredList.clear();

        Medication med = null;
        c = loadData(searchQuery);

        while (c.moveToNext()){
            String drugName = c.getString(c.getColumnIndex(UserDataContract
                    .MedParameters.DRUG_NAME));
            String description = c.getString(c.getColumnIndex(UserDataContract
                    .MedParameters.DESCRIPTION));
            String tabsPerIntake = c.getString(c.getColumnIndex(UserDataContract
                    .MedParameters.TABLETS_PER_INTAKE));
            int mTabsPerIntake = Integer.parseInt(tabsPerIntake);
            String frequency = c.getString(c.getColumnIndex(UserDataContract
                    .MedParameters.TIMES_PER_DAY));
            int mFrequency = Integer.parseInt(frequency);
            String startDate = c.getString(c.getColumnIndex(UserDataContract
                    .MedParameters.START_DATE));
            String endDate = c.getString(c.getColumnIndex(UserDataContract
                    .MedParameters.END_DATE));

            med = new Medication(drugName, description, mTabsPerIntake, mFrequency,
                    startDate, endDate);
            med.setData(med, drugName, description, mTabsPerIntake, mFrequency,
                    startDate, endDate);

            filteredList.add(med);
        }
        medicationAdapter = new MedicationAdapter(this, c);
        recyclerView.setAdapter(medicationAdapter);
    }

    // Retrieve data for filter
    public Cursor loadData(String searchItem){
        String[] columns = {MedParameters._ID, MedParameters.DRUG_NAME,
                MedParameters.DESCRIPTION, MedParameters.TABLETS_PER_INTAKE,
                MedParameters.TIMES_PER_DAY, MedParameters.START_DATE, MedParameters.END_DATE};

        Cursor cursor = null;

        if (searchItem != null && searchItem.length() > 0){
            String sql = "SELECT * FROM "+ UserDataContract.MedParameters.TABLE_NAME+ " WHERE "
                    + MedParameters.DRUG_NAME + " LIKE '%"+searchItem+"%'";
            cursor = mDb.rawQuery(sql, null);
            return cursor;
        }

       cursor = mDb.query(MedParameters.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}
