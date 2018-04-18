package com.eyo.bethel.med_manager.ProfileSetUp;


import android.net.Uri;

import com.eyo.bethel.med_manager.BasePresenter;
import com.eyo.bethel.med_manager.BaseView;

public class EditProfileContract {
    /* handles the results of the actions */
    interface EditProfileView extends BaseView {
        void onProfileUpdated(String message);
        void onProfileUpdateError(String message, String type);
        void onImageUpload(Uri imageUpload);
    }

    interface EditProfilePresenter extends BasePresenter {
        void updateProfile(String profileImage, String firstName, String lastName, String phoneNumber);
        void uploadImageFromGallery(Uri imageUri);
    }
}
