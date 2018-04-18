package com.eyo.bethel.med_manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.eyo.bethel.med_manager.Medications.MedicationActivity;
import com.eyo.bethel.med_manager.Medications.SearchActivity;
import com.eyo.bethel.med_manager.Session.SignInActivity;

import com.eyo.bethel.med_manager.ProfileSetUp.EditProfileActivity;
import com.eyo.bethel.med_manager.addMedication.AddMedicationActivity;
import com.eyo.bethel.med_manager.data.ComplexPreferences;
import com.eyo.bethel.med_manager.data.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.lib.circularlayoutlib.CircularLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    CircleImageView profilepix;
    TextView fullName, phoneNumber;
    Bundle extras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        profilepix = (CircleImageView) findViewById(R.id.profile_pix);
        fullName = (TextView) findViewById(R.id.sample_name);
        phoneNumber = (TextView) findViewById(R.id.sample_number);

        /* Configure sign-in to request the user's ID, email
        * address and basic profile*/
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                .build();

        // to build a GoogleSignInClient with the options specified by gso
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        getUserProfileData(this);

        CircularLayout circularLayout = (CircularLayout) findViewById(R.id.circular_layout);
        if (circularLayout != null) {
            circularLayout.setOnCircularItemClickListener(new CircularLayout
                    .OnCircularItemClickListener(){
                @Override
                public void onCircularItemClick(int index) {
                    Toast.makeText(getApplicationContext(), "Item " + index + " clicked",
                             Toast.LENGTH_SHORT).show();
                    if (index == 0){
                        // Edit profile item was clicked
                        Intent profileIntent = new Intent(getApplicationContext(), EditProfileActivity.class);
                        startActivity(profileIntent);
                    }else if (index == 1){
                        // Add medication item was clicked
                        Intent addMedIntent = new Intent(getApplicationContext(), AddMedicationActivity.class);
                        startActivity(addMedIntent);
                    }else if (index == 2){
                        // search item was clicked
                        Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(searchIntent);
                    }else if (index == 3){
                        // view medication item was clicked
                        Intent viewMedIntent = new Intent(getApplicationContext(), MedicationActivity.class);
                        startActivity(viewMedIntent);
                    }else if (index == 4){
                        // sign out item was clicked
                        signOut();
                    }
                }
            });
        }
    }

    public void signOut(){
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        Intent signInIntent = new Intent(getApplicationContext(), SignInActivity.class);
                        startActivity(signInIntent);
                    }
                });
    }

    public void getUserProfileData(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "object_prefs", 0);
        User currentProfileData = complexPreferences.getObject("object_value", User.class);
        if (currentProfileData != null){
            profilepix.setImageURI(Uri.parse(currentProfileData.getImageUrl()));
            fullName.setText(currentProfileData.getFirstName() + " " + currentProfileData.getLastName());
            phoneNumber.setText(currentProfileData.getPhoneNumber());
        } else {
            extras = getIntent().getExtras();
            fullName.setText(extras.getString("theProfileName"));
        }

    }
}
