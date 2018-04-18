package com.eyo.bethel.med_manager.addMedication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eyo.bethel.med_manager.R;

import java.util.Calendar;


public class AddMedicationActivity extends AppCompatActivity implements AddMedicationContract.AddMedicationView {

    // Initialize all editext
    EditText drugName, description,tabsPerIntake, timesPerDay;

    private Calendar calendar;
    private TextView mStartDate, mEndDate;
    private int startYear, startMonth, startDay, endYear, endMonth, endDay;
    private Button startDateBtn, endDateBtn;
    Toolbar toolbar;

    // initiliaze all buttons
    Button addMedicationBtn;

    // initialize all nav imageviews
    ImageView navUpTabsPerIntake, navDownTabsPerIntake,
            navUpTimesPerDay, navDownTimesPerDay;

    // initialize the presenter
    AddMedicationPresenter addMedicationPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_medication);

        addMedicationPresenter = new AddMedicationPresenter(this, this);

        calendar = Calendar.getInstance();
        startYear = calendar.get(Calendar.YEAR);
        startMonth = calendar.get(Calendar.MONTH);
        startDay = calendar.get(Calendar.DAY_OF_MONTH);

        endYear = calendar.get(Calendar.YEAR);
        endMonth = calendar.get(Calendar.MONTH);
        endDay = calendar.get(Calendar.DAY_OF_MONTH);

        toolbar = (Toolbar) findViewById(R.id.add_med_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Medication");
        toolbar.setTitleTextColor(getResources().getColor(R.color.icons));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drugName = (EditText) findViewById(R.id.drug_name_edtxt);
        description = (EditText) findViewById(R.id.description_txt);
        tabsPerIntake = (EditText) findViewById(R.id.tabs_per_intake_edtxt);
        timesPerDay = (EditText) findViewById(R.id.times_per_day_edtxt);

        navUpTabsPerIntake = (ImageView) findViewById(R.id.ic_navup_tabs_per_intake);
        navDownTabsPerIntake = (ImageView) findViewById(R.id.ic_navdown_tabs_per_intake);
        navUpTimesPerDay = (ImageView) findViewById(R.id.ic_navup_times_per_day);
        navDownTimesPerDay = (ImageView) findViewById(R.id.ic_navdown_times_per_day);

        mStartDate = (TextView) findViewById(R.id.start_date);
        mEndDate = (TextView) findViewById(R.id.end_date);

        startDateBtn = (Button) findViewById(R.id.start_date_btn);
        endDateBtn = (Button) findViewById(R.id.end_date_btn);

        addMedicationBtn = (Button) findViewById(R.id.add_med_btn);

        navUpTabsPerIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseTabs(tabsPerIntake);
            }
        });

        navDownTabsPerIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrease(tabsPerIntake);
            }
        });

        navUpTimesPerDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseFrequency(timesPerDay);
            }
        });

        navDownTimesPerDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrease(timesPerDay);
            }
        });

        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
                Toast.makeText(getApplicationContext(), "Pick start date",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        endDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(666);
                Toast.makeText(getApplicationContext(), "Pick end date",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        addMedicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
    }


    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void onMedicationAdded(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        onBackPressed();
        finish();
    }

    @Override
    public void onMedicationAddedError(String error, String errorType) {
        Toast.makeText(this, errorType + ": " + error, Toast.LENGTH_SHORT).show();
    }

    // to add the medication after all parameters have been inputed
    public void add(){

        // to add medication
        addMedicationPresenter.addMedication(drugName.getText().toString(), description.getText().toString(),
                Integer.parseInt(tabsPerIntake.getText().toString()), Integer.parseInt(timesPerDay.getText().toString()),
                mStartDate.getText().toString(), mEndDate.getText().toString());
    }

    // to increase the value in the tabs edtTxt using the nav up icon
    public void increaseTabs(EditText txt){
        if (txt.getText().toString().equals("")){
            txt.setText("1");
        }
        String txtStr = txt.getText().toString();
        int txtInt = Integer.parseInt(txtStr);
        if (txtInt < 6){
            txtInt = txtInt + 1;
            txt.setText(Integer.toString(txtInt));
        } else {
            Toast.makeText(this, "You have reached the maximum value", Toast.LENGTH_SHORT).show();
        }
    }

    public void increaseFrequency(EditText txt){
        if (txt.getText().toString().equals("")){
            txt.setText("1");
        }
        String txtStr = txt.getText().toString();
        int txtInt = Integer.parseInt(txtStr);
        if (txtInt < 3){
            txtInt = txtInt + 1;
            txt.setText(Integer.toString(txtInt));
        } else {
            Toast.makeText(this, "You have reached the maximum value", Toast.LENGTH_SHORT).show();
        }

    }

    /*we use this method to decrease the values in both the tabs per intake
    * and the times per day since they have the same conditions*/
    public void decrease(EditText txt){
        if (txt.getText().toString().equals("")){
            txt.setText("1");
        }
        String txtStr = txt.getText().toString();
        int txtInt = Integer.parseInt(txtStr);
        if (txtInt > 1){
            txtInt = txtInt - 1;
            txt.setText(Integer.toString(txtInt));
        } else {
            Toast.makeText(this, "You have reached the minimum value", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    startDateListener, startYear, startMonth, startDay);
        }else if (id == 666){
            return new DatePickerDialog(this, endDateListener, endYear, endMonth, endDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener startDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showStartDate(arg1, arg2+1, arg3);
                }
            };


    private DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            showEndDate(year, month+1, day);
        }
    };

    private void showStartDate(int year, int month, int day) {
        mStartDate.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));

    }

    private void showEndDate(int year, int month, int day) {
        mEndDate.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

}
