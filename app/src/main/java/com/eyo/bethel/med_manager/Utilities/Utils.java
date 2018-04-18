package com.eyo.bethel.med_manager.Utilities;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.Patterns;

import java.text.ParseException;
import java.util.Date;

public class Utils {

    public static boolean isDeviceOnline(Context context) {
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            boolean isConnected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
            return isConnected;
        } catch (Exception e) {
            Log.v("Utils:  Connectivity", e.toString());
            return false;
        }
    }

    public static boolean isValidName(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0 && charSequence.length() <= 40;
    }

    public static boolean isValidDescription(CharSequence charSequence){
        return charSequence != null && charSequence.length() > 12 && charSequence.length() <= 150;
    }

    public static boolean isDateValid(String myDate){
        try {
            Date date = new SimpleDateFormat(Keys.UI.DATE_FORMAT).parse(myDate);
            return new Date().before(date);
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidTabletsPerIntake(int tablets){
        return tablets > 0 && tablets <= 5;
    }

    public static boolean isValidTimesPerDay(int times){
        return times > 0 && times <= 4;
    }

    public static boolean isPhoneNumberValid(CharSequence charSequence){
        return Patterns.PHONE.matcher(charSequence).matches();
    }


}
