package com.eyo.bethel.med_manager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.eyo.bethel.med_manager.data.UserDataContract.*;

public class Medication {
    private String drugName, description;
    private int tabletsPerIntake, timesPerday;
    private String startDate, endDate;

    SQLiteDatabase database;
    MedicalDbHelper dbHelper;

    public Medication(String drugName, String description, int tabletsPerIntake, int timesPerday, String startDate, String endDate) {
        this.drugName = drugName;
        this.description = description;
        this.tabletsPerIntake = tabletsPerIntake;
        this.timesPerday = timesPerday;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Medication open(Context context){
        dbHelper = new MedicalDbHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void InsertMedicationData(){
        ContentValues cv = new ContentValues();
        cv.put(MedParameters.DRUG_NAME, getDrugName());
        cv.put(MedParameters.DESCRIPTION, getDescription());
        cv.put(MedParameters.TABLETS_PER_INTAKE, getTabletsPerIntake());
        cv.put(MedParameters.TIMES_PER_DAY, getTimesPerday());
        cv.put(MedParameters.START_DATE, getStartDate());
        cv.put(MedParameters.END_DATE, getEndDate());

        database.insert(MedParameters.TABLE_NAME, null, cv);
    }

    public void setData(Medication medication, String DrugName, String Description,
                        int TabsPerIntake, int Frequency, String StartDate, String EndDate){
        medication.setDrugName(DrugName);
        medication.setDescription(Description);
        medication.setTabletsPerIntake(TabsPerIntake);
        medication.setTimesPerday(Frequency);
        medication.setStartDate(StartDate);
        medication.setEndDate(EndDate);
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTabletsPerIntake(int tabletsPerIntake) {
        this.tabletsPerIntake = tabletsPerIntake;
    }

    public void setTimesPerday(int timesPerday) {
        this.timesPerday = timesPerday;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getDescription() {
        return description;
    }

    public int getTabletsPerIntake() {
        return tabletsPerIntake;
    }

    public int getTimesPerday() {
        return timesPerday;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }
}
