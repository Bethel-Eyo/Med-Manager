package com.eyo.bethel.med_manager.addMedication;

import android.content.Context;

import com.eyo.bethel.med_manager.Utilities.Keys;
import com.eyo.bethel.med_manager.Utilities.Utils;
import com.eyo.bethel.med_manager.data.Medication;

import java.util.Date;

public class AddMedicationPresenter implements AddMedicationContract.AddMedicationPresenter {
    AddMedicationContract.AddMedicationView addMedicationView;
    Context mContext;

    @Override
    public void start() {

    }

    public AddMedicationPresenter(AddMedicationContract.AddMedicationView addMedicationView, Context mContext) {
        this.addMedicationView = addMedicationView;
        this.mContext = mContext;
    }

    @Override
    public void addMedication(String drugName, String description, int tabPerIntake, int frequencyPerDay, String startDate, String endDate) {
        // check for validations
        if (!Utils.isValidName(drugName)){
            addMedicationView.onMedicationAddedError(Keys.UI.DRUG_NAME_ERR_MSG, Keys.UI.DRUG_NAME);
        } else if (!Utils.isValidDescription(description)){
            addMedicationView.onMedicationAddedError(Keys.UI.DESCRIPTION_ERR_MSG, Keys.UI.DESCRIPTION);
        } else if (!Utils.isValidTabletsPerIntake(tabPerIntake)){
            addMedicationView.onMedicationAddedError(Keys.UI.TABS_PER_INTAKE_ERR_MSG, Keys.UI.TABLETS_PER_INTAKE);
        } else if (!Utils.isValidTimesPerDay(frequencyPerDay)){
            addMedicationView.onMedicationAddedError(Keys.UI.TIMES_PER_DAY_ERR_MSG, Keys.UI.TIMES_PER_DAY);
        } else if (!Utils.isDateValid(endDate)){
            addMedicationView.onMedicationAddedError(Keys.UI.START_DATE_ERR_MSG, Keys.UI.START_DATE);
        } else {
            Medication medication = new Medication(drugName, description, tabPerIntake, frequencyPerDay, startDate, endDate);
            try {
                medication.open(mContext);
                medication.InsertMedicationData();
                addMedicationView.onMedicationAdded(Keys.UI.MED_ADDED);
                medication.close();
            } catch (Exception e){
                addMedicationView.onMedicationAddedError("Error adding", "Unknown");
            }
        }
    }
}
