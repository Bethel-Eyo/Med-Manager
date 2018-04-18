package com.eyo.bethel.med_manager.Medications;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eyo.bethel.med_manager.R;
import com.eyo.bethel.med_manager.SettingsActivity;
import com.eyo.bethel.med_manager.SettingsFragment;

public class MedFragment extends Fragment implements MedicationContract.MedicationView , SharedPreferences.OnSharedPreferenceChangeListener {
    Bundle nBundle;

    MedicationPresenter medicationPresenter;
    MedicationAdapter medicationAdapter;
    Cursor cursor;
    RecyclerView recyclerView;
    Toolbar toolbar;

    public static MedFragment newInstance(){
        MedFragment medFragment = new MedFragment();
        return medFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.medication_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.med_list);
        toolbar = (Toolbar) view.findViewById(R.id.med_frag_app_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Medications");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.icons));
        assert recyclerView != null;
        final MedicationActivity m = (MedicationActivity) getActivity();
        recyclerView.setLayoutManager(new LinearLayoutManager(m));
        medicationPresenter = new MedicationPresenter(this, getContext());
        setUpSharedPreferences();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (nBundle != null){
        }
    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        /* Use the inflater's inflate method to inflate our medications_menu layout to this menu */
        inflater.inflate(R.menu.medications_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(getContext(), SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        } else if (id == android.R.id.home){
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataLoaded(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDataLoadedError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    public void loadMonthFromSharedPreferences(SharedPreferences sharedPreferences){
        String month = sharedPreferences.getString(getString(R.string.pref_month_key),
                getString(R.string.pref_month_value_all));
        updateAdapter(month);
    }

    private void setUpSharedPreferences(){
        // Get all of the values from shared preferences to set it up
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        loadMonthFromSharedPreferences(sharedPreferences);
    }

    public void updateAdapter(String month){
        if (month.equals("january")){
            int jan = 1;
            medicationPresenter.loadDataForMonth(jan);
        } else if (month.equals("february")){
            int feb = 2;
            cursor = medicationPresenter.loadDataForMonth(feb);
        } else if (month.equals("march")){
            int mar = 3;
            cursor = medicationPresenter.loadDataForMonth(mar);
        } else if (month.equals("april")){
            int apr = 4;
            cursor = medicationPresenter.loadDataForMonth(apr);
        } else if (month.equals("may")){
            int may = 5;
            cursor = medicationPresenter.loadDataForMonth(may);
        } else if (month.equals("june")){
            int jun = 6;
            cursor = medicationPresenter.loadDataForMonth(jun);
        } else if (month.equals("july")){
            int jul = 7;
            cursor = medicationPresenter.loadDataForMonth(jul);
        } else if (month.equals("august")){
            int aug = 8;
            cursor = medicationPresenter.loadDataForMonth(aug);
        } else if (month.equals("september")){
            int sep = 9;
            cursor = medicationPresenter.loadDataForMonth(sep);
        } else if (month.equals("october")){
            int oct = 10;
            cursor = medicationPresenter.loadDataForMonth(oct);
        } else if (month.equals("november")){
            int nov = 11;
            cursor = medicationPresenter.loadDataForMonth(nov);
        } else if (month.equals("december")){
            int dec = 12;
            cursor = medicationPresenter.loadDataForMonth(dec);
        } else {
            cursor = medicationPresenter.loadData();
        }
        medicationAdapter = new MedicationAdapter(getContext(), cursor);
        recyclerView.setAdapter(medicationAdapter);
    }

    // Updates the screen if the shared preferences change. This method is required when you make a
    // class implement OnSharedPreferenceChangedListener
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(R.string.pref_month_key)){
            loadMonthFromSharedPreferences(sharedPreferences);
        }
    }
}
