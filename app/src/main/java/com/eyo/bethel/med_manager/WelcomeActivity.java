package com.eyo.bethel.med_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eyo.bethel.med_manager.ProfileSetUp.EditProfileActivity;


public class WelcomeActivity extends AppCompatActivity {

    Button setupBtn, skipBtn;
    TextView loggedInTxt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        setupBtn = (Button) findViewById(R.id.set_up_btn);
        skipBtn = (Button) findViewById(R.id.skip_btn);
        loggedInTxt = (TextView) findViewById(R.id.logged_in_as);

        Bundle extras = getIntent().getExtras();
        final String profileName = extras.getString("profileName");
        String loggedInStr = "Logged in as " + profileName;
        loggedInTxt.setText(loggedInStr);

        setupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                homeIntent.putExtra("theProfileName", profileName);
                startActivity(homeIntent);
            }
        });
    }
}
