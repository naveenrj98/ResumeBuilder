package com.developer.rjtech.resumebuilder.adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.developer.rjtech.resumebuilder.R;
import com.developer.rjtech.resumebuilder.datamodel.Training;

import java.util.List;

public class TrainingAdapter extends BaseAdapter {
    private final LayoutInflater layoutInflater;
    private final List<Training> mtrainings;

    public TrainingAdapter(Activity activity, List<Training> trainings) {
        layoutInflater = LayoutInflater.from(activity);
        mtrainings = trainings;
    }

    @Override
    public int getCount() {
        return mtrainings.size();
    }

    @Override
    public Object getItem(int i) {
        return mtrainings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.training_list_row, viewGroup, false);
        }
        final TextView trainingTitle = (TextView) view.findViewById(R.id.trainingrowTitle);
        final TextView trainingDate = (TextView) view.findViewById(R.id.trainingrowDate);
        final TextView projecttitle = (TextView) view.findViewById(R.id.projectrowtitle);
        final TextView projectDetails = (TextView) view.findViewById(R.id.projectrowDetails);
        final Training training = mtrainings.get(i);

        trainingTitle.setText(training.trainingTitle + " :");
        trainingDate.setText(training.trainingDate);
        projecttitle.setText(training.pName + " :");
        projectDetails.setText(training.pDetails);
        return view;
    }
}
