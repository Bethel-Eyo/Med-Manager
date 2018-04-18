package com.eyo.bethel.med_manager.Medications;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.eyo.bethel.med_manager.Utilities.Keys;
import com.eyo.bethel.med_manager.data.MedicalDbHelper;
import com.eyo.bethel.med_manager.data.UserDataContract;

public class MedicationPresenter implements MedicationContract.MedicationPresenter{
    private SQLiteDatabase mDb;
    Context mContext;
    MedicationContract.MedicationView medicationView;

    public MedicationPresenter(MedicationContract.MedicationView medicationView, Context mContext) {
        this.mContext = mContext;
        this.medicationView = medicationView;
        MedicalDbHelper dbHelper = new MedicalDbHelper(mContext);
        mDb = dbHelper.getReadableDatabase();
    }

    @Override

    public void start() {
    }

    @Override
    public Cursor loadData() {
        Cursor cursor;
        try{
            cursor = mDb.rawQuery("SELECT * FROM "+ UserDataContract.MedParameters.TABLE_NAME, null);
            medicationView.onDataLoaded(Keys.UI.DATA_RETREIVED_SUCCESS);
        } catch (Exception ex){
            medicationView.onDataLoadedError(Keys.UI.FAILED_DATA_RETRIEVAL);
            return null;
        }
        return cursor;
    }

    @Override
    public Cursor loadDataForMonth(int month){
        Cursor cursor;
        try{
            String sql = "SELECT * FROM "+ UserDataContract.MedParameters.TABLE_NAME +
                    " WHERE strfttime('%m', date) = " + month;
            cursor = mDb.rawQuery(sql, null);
            medicationView.onDataLoaded(Keys.UI.DATA_RETREIVED_SUCCESS);
        } catch (Exception ex){
            medicationView.onDataLoadedError(Keys.UI.FAILED_DATA_RETRIEVAL);
            return null;
        }
        return cursor;
    }
}
