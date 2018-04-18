package com.eyo.bethel.med_manager.data;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private String imageUrl;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public User(String imageUrl, String firstName, String lastName, String phoneNumber) {
        this.imageUrl = imageUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public void updateUserProfile(Context context){
        User newData = new User(getImageUrl(), getFirstName(), getLastName(), getPhoneNumber());
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(context, "object_prefs", 0);
        complexPreferences.putObject("object_value", newData);
        complexPreferences.commit();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
