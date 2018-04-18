package com.eyo.bethel.med_manager.addMedication;

import com.eyo.bethel.med_manager.BasePresenter;
import com.eyo.bethel.med_manager.BaseView;

import java.util.Date;

public class AddMedicationContract {
    /* handles the results of the actions */
    interface AddMedicationView extends BaseView{
        void onMedicationAdded(String message);
        void onMedicationAddedError(String error, String errorType);
    }

    /*handles the actions to be carried out*/
    interface AddMedicationPresenter extends BasePresenter{
        void addMedication(String drugName, String description, int tabPerIntake,
                           int frequencyPerDay, String startDate, String endDate);
    }
}
