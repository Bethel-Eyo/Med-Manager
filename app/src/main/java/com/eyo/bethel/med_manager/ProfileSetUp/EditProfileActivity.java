package com.eyo.bethel.med_manager.ProfileSetUp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.eyo.bethel.med_manager.HomeActivity;
import com.eyo.bethel.med_manager.R;


public class EditProfileActivity extends AppCompatActivity implements EditProfileContract.EditProfileView {

    Button chooseImageBtn, updateProfileBtn;
    EditText firstName, lastName, phoneNumber;
    ImageView profileImage;

    EditProfilePresenter editProfilePresenter;
    Uri selectedImageUri;

    private static final int RC_PHOTO_PICKER = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        editProfilePresenter = new EditProfilePresenter(this, this);
        profileImage = (ImageView) findViewById(R.id.profile_img_view);
        chooseImageBtn = (Button) findViewById(R.id.choose_img_btn);
        firstName = (EditText) findViewById(R.id.first_name_edTxt);
        lastName = (EditText) findViewById(R.id.last_name_edTxt);
        phoneNumber = (EditText) findViewById(R.id.phone_num_edTxt);
        updateProfileBtn = (Button) findViewById(R.id.update_profile_btn);

        chooseImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfilePresenter.uploadImageFromGallery(selectedImageUri);
            }
        });

        updateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void onProfileUpdated(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProfileUpdateError(String message, String type) {
        Toast.makeText(this, type + ": " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageUpload(Uri imageUpload) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < 19) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        }

        startActivityForResult(Intent.createChooser(intent, "Complete action using"),
                RC_PHOTO_PICKER);
    }

    public void update(){
        editProfilePresenter.updateProfile(selectedImageUri.toString(), firstName.getText().toString(),
                lastName.getText().toString(), phoneNumber.getText().toString());
        finish();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK){
            /* to handle the photo_picker result, the chosen photo will
            come in as a uri from data.getData()*/
            selectedImageUri = data.getData();
            // to set the picture to the profile ImageView
            profileImage.setImageURI(selectedImageUri);
        }
    }
}
