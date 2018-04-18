package com.eyo.bethel.med_manager.Session;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.eyo.bethel.med_manager.HomeActivity;
import com.eyo.bethel.med_manager.R;
import com.eyo.bethel.med_manager.Utilities.Utils;
import com.eyo.bethel.med_manager.WelcomeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SignInActivity extends AppCompatActivity {

    public static final String TAG = "SignInActivity";
    RelativeLayout signInBtn;

    public GoogleSignInClient mGoogleSignInClient;
    // Choose an arbituary request code value
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        signInBtn = (RelativeLayout) findViewById(R.id.btn_sign_in);

        /* Configure sign-in to request the user's ID, email
        * address and basic profile*/
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                .build();

        // to build a GoogleSignInClient with the options specified by gso
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // check for existing google sign in account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        checkSessionState(account);
    }

    public void SignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    public void checkSessionState(@NonNull GoogleSignInAccount account) {
        // if user account is null, prompt user to sign in, else move to homeActivity
        if (account != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please sign in with Google acc", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the intent from GoogleSignInClient..
        if (requestCode == RC_SIGN_IN){
            // The task returned from this call is always completed, no need for listener
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    public void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, go to welcome page
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.putExtra("profileName", account.getDisplayName());
            startActivity(intent);
        }catch (ApiException ex){
            // The ApiException status code indicates the detailed failure reason
            Log.w(TAG, "SignInResult:Failed code= " + ex.getStatusCode());
            checkSessionState(null);
        }
    }

}

