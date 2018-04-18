package com.eyo.bethel.med_manager.Medications;

import android.database.Cursor;
import android.os.Bundle;

import com.eyo.bethel.med_manager.BasePresenter;
import com.eyo.bethel.med_manager.BaseView;

public class MedicationContract {
    /* handles the results of the actions */
    interface MedicationView extends BaseView{
        void onDataLoaded(String message);
        void onDataLoadedError(String error);
    }

    /*handles the actions to be carried out*/
    interface MedicationPresenter extends BasePresenter{
        Cursor loadData();
        Cursor loadDataForMonth(int month);
    }
}
