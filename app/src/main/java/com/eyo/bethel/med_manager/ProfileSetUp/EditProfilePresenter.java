package com.eyo.bethel.med_manager.ProfileSetUp;

import android.content.Context;
import android.net.Uri;

import com.eyo.bethel.med_manager.Utilities.Keys;
import com.eyo.bethel.med_manager.Utilities.Utils;
import com.eyo.bethel.med_manager.data.User;

public class EditProfilePresenter implements EditProfileContract.EditProfilePresenter {

    EditProfileContract.EditProfileView editProfileView;
    Context mContext;

    public EditProfilePresenter(EditProfileContract.EditProfileView editProfileView, Context context) {
        this.editProfileView = editProfileView;
        this.mContext = context;
    }

    @Override
    public void start() {

    }

    @Override
    public void updateProfile(String profileImage, String firstName, String lastName, String phoneNumber) {
        if (!Utils.isValidName(firstName)){
            editProfileView.onProfileUpdateError(Keys.UI.NAME_ERR_MSG, Keys.UI.FIRST_NAME);
        } else if (!Utils.isValidName(lastName)){
            editProfileView.onProfileUpdateError(Keys.UI.NAME_ERR_MSG, Keys.UI.LAST_NAME);
        } else if (!Utils.isPhoneNumberValid(phoneNumber)){
            editProfileView.onProfileUpdateError(Keys.UI.PHONE_ERR_MSG, Keys.UI.PHONE_NUMBER);
        } else {
            try{
                User user = new User(profileImage, firstName, lastName, phoneNumber);
                user.updateUserProfile(mContext);
                editProfileView.onProfileUpdated(Keys.UI.PROFILE_UPDATED);
            } catch (Exception ex){
            }
        }

    }

    @Override
    public void uploadImageFromGallery(Uri imageUri) {
        editProfileView.onImageUpload(imageUri);
    }
}
