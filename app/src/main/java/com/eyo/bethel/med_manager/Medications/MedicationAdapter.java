package com.eyo.bethel.med_manager.Medications;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.eyo.bethel.med_manager.R;
import com.eyo.bethel.med_manager.data.Medication;
import com.eyo.bethel.med_manager.Medications.SearchActivity.*;
import com.eyo.bethel.med_manager.data.UserDataContract;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedViewHolder>{

    private List<Medication> nMedication;
    Context mContext;
    Cursor mCursor;
    String mDrugName, mDescription, mTabsPerIntake, mFrequency, mStartDate, mEndDate;


    public MedicationAdapter(Context mContext, Cursor mCursor) {
        this.mContext = mContext;
        this.mCursor = mCursor;
        nMedication = new ArrayList<Medication>();
    }

    @Override
    public MedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_med_view, parent, false);
        return new MedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedViewHolder holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return;

        // Update the view holder with the information needed to display
        mDrugName = mCursor.getString(mCursor
                .getColumnIndex(UserDataContract.MedParameters.DRUG_NAME));
        mDescription = mCursor.getString(mCursor
                .getColumnIndex(UserDataContract.MedParameters.DESCRIPTION));
        mTabsPerIntake = mCursor.getString(mCursor
                .getColumnIndex(UserDataContract.MedParameters.TABLETS_PER_INTAKE));
        mFrequency = mCursor.getString(mCursor
                .getColumnIndex(UserDataContract.MedParameters.TIMES_PER_DAY));
        mStartDate = mCursor.getString(mCursor
                .getColumnIndex(UserDataContract.MedParameters.START_DATE));
        mEndDate = mCursor.getString(mCursor
                .getColumnIndex(UserDataContract.MedParameters.END_DATE));
        long id = mCursor.getLong(mCursor.getColumnIndex(UserDataContract.MedParameters._ID));

        // set them to their appropriate views
        holder.drugName.setText(mDrugName);
        holder.description.setText(mDescription);
        holder.frequency.setText(mTabsPerIntake + " x " + mFrequency);
        holder.startDate.setText(mStartDate);
        holder.endDate.setText(mEndDate);

        // Set the tag of the itemView in the holder to the id
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class MedViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        // initialize custom views
        TextView drugName, description, frequency, startDate, endDate;

        public MedViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            drugName = (TextView) itemView.findViewById(R.id.custom_drug_name);
            description = (TextView) itemView.findViewById(R.id.custom_description);
            frequency = (TextView) itemView.findViewById(R.id.custom_frequency);
            startDate = (TextView) itemView.findViewById(R.id.custom_start_date);
            endDate = (TextView) itemView.findViewById(R.id.custom_end_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if (pos != RecyclerView.NO_POSITION){
                        Medication clickedMed = nMedication.get(pos);
                        Intent intent = new Intent(mContext, MedDetailsActivity.class);
                        intent.putExtra("drugName", clickedMed.getDrugName());
                        intent.putExtra("description", clickedMed.getDescription());
                        intent.putExtra("tabsPerIntake", clickedMed.getTabletsPerIntake());
                        intent.putExtra("frequency", clickedMed.getTimesPerday());
                        intent.putExtra("startDate", clickedMed.getStartDate());
                        intent.putExtra("endDate", clickedMed.getEndDate());
                        mContext.startActivity(intent);
                    }
                }
            });
        }

    }


}
