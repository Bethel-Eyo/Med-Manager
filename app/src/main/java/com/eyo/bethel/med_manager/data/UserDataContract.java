package com.eyo.bethel.med_manager.data;

import android.provider.BaseColumns;

public class UserDataContract {
    public static final class MedParameters implements BaseColumns {
        public static final String TABLE_NAME = "med_table";
        public static final String DRUG_NAME = "drug_name";
        public static final String DESCRIPTION = "description";
        public static final String TABLETS_PER_INTAKE = "tablets_per_intake";
        public static final String TIMES_PER_DAY = "times_per_day";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
    }
}
