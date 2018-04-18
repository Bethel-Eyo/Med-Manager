package com.eyo.bethel.med_manager.Utilities;

public interface Keys {

    public interface UI {
        public static final String DRUG_NAME_ERR_MSG = "Enter less than 50 chars";
        public static final String DESCRIPTION_ERR_MSG = "Enter more than 12chars and less than 150chars";
        public static final String TABS_PER_INTAKE_ERR_MSG = "Enter number between 1 and 5";
        public static final String TIMES_PER_DAY_ERR_MSG = "Enter number between 1 and 4";
        public static final String START_DATE_ERR_MSG = "Enter Valid date";
        public static final String DRUG_NAME = "Drug Name";
        public static final String FIRST_NAME = "First Name";
        public static final String LAST_NAME = "Last Name";
        public static final String PHONE_NUMBER = "Phone Number";
        public static final String NAME_ERR_MSG = "Please Enter a valid Name";
        public static final String PHONE_ERR_MSG = "Please enter a valid Phone number";
        public static final String DESCRIPTION = "Description";
        public static final String TABLETS_PER_INTAKE = "Tablets per intake";
        public static final String TIMES_PER_DAY = "Times per day";
        public static final String START_DATE = "Start date";
        public static final String DATE_FORMAT = "dd-MM-yyyy";
        public static final String MED_ADDED = "You have Successfully added a Medication";
        public static final String PROFILE_UPDATED = "You have Successfully Updated your Profile";
        public static final String DATA_RETREIVED_SUCCESS = "Medications Data loaded Successfully";
        public static final String FAILED_DATA_RETRIEVAL = "Unable to load Medications Data";
        public static final String MED_FRAGMENT_TAG = "MedFragment";
    }
}
