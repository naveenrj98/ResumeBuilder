package com.developer.rjtech.resumebuilder.adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.developer.rjtech.resumebuilder.R;
import com.developer.rjtech.resumebuilder.datamodel.TechSkills;

import java.util.List;

public class SkillAdapter extends BaseAdapter {
    private final LayoutInflater layoutInflater;
    private final List<TechSkills> mskills;

    public SkillAdapter(Activity activity, List<TechSkills> skills) {
        layoutInflater = LayoutInflater.from(activity);
        mskills = skills;
    }

    @Override
    public int getCount() {
        return mskills.size();
    }

    @Override
    public Object getItem(int i) {
        return mskills.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.skill_list_row, viewGroup, false);
        }
        final TextView titleText = (TextView) view.findViewById(R.id.rowTitle);
        final TextView subtitleText = (TextView) view.findViewById(R.id.rowsubtitle);
        final TechSkills skills = mskills.get(i);

        titleText.setText(skills.title + " :");
        subtitleText.setText(skills.subtitle);
        return view;
    }
}
