package com.developer.rjtech.resumebuilder.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.developer.rjtech.resumebuilder.R;
import com.developer.rjtech.resumebuilder.datamodel.Academia;
import com.developer.rjtech.resumebuilder.datamodel.Academic;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AcademiaActivity extends AppCompatActivity {


    @BindViews({R.id.title1textView, R.id.title2textView, R.id.title3textView, R.id.title4textView})
    List<TextView> titles;
    @BindViews({R.id.title1, R.id.institute1, R.id.university1, R.id.year1})
    List<TextInputEditText> PostGraduate;
    @BindViews({R.id.title2, R.id.institute2, R.id.university2, R.id.year2})
    List<TextInputEditText> Graduate;
    @BindViews({R.id.title3, R.id.institute3, R.id.university3, R.id.year3})
    List<TextInputEditText> Intermediate;
    @BindViews({R.id.title4, R.id.institute4, R.id.university4, R.id.year4})
    List<TextInputEditText> School;
    @BindViews({R.id.pgLayout, R.id.gradLayout, R.id.interLayout, R.id.schoolLayout})
    List<LinearLayout> layouts;
    @BindViews({R.id.pgSwitch, R.id.gradSwitch, R.id.srSchoolSwitch, R.id.schoolSwitch})
    List<Switch> switches;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academia);
        ButterKnife.bind(this);
        titles.get(0).setTypeface(fontSelector.font);
        titles.get(1).setTypeface(fontSelector.font);
        titles.get(2).setTypeface(fontSelector.font);
        titles.get(3).setTypeface(fontSelector.font);
        setListeners();
        btn_submit = findViewById(R.id.submitButton);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAcademia();
            }
        });
    }




    public void submitAcademia() {
        if (layouts.get(0).getVisibility() == View.VISIBLE) {
            if (TextUtils.isEmpty(PostGraduate.get(0).getText().toString())) {
                PostGraduate.get(0).setError("Enter Title!");
                return;
            }
            if (TextUtils.isEmpty(PostGraduate.get(1).getText().toString())) {
                PostGraduate.get(1).setError("Enter Institute Name!");
                return;
            }
            if (TextUtils.isEmpty(PostGraduate.get(2).getText().toString())) {
                PostGraduate.get(2).setError("Enter University Name!");
                return;
            }
            if (TextUtils.isEmpty(PostGraduate.get(3).getText().toString())) {
                PostGraduate.get(3).setError("Enter Year!");
                return;
            }
            Academia.PostGraduate = new Academic(PostGraduate.get(0).getText().toString(), PostGraduate.get(1).getText().toString(), PostGraduate.get(2).getText().toString(), PostGraduate.get(3).getText().toString());

        }
        if (layouts.get(1).getVisibility() == View.VISIBLE) {
            if (TextUtils.isEmpty(Graduate.get(0).getText().toString())) {
                Graduate.get(0).setError("Enter Title!");
                return;
            }
            if (TextUtils.isEmpty(Graduate.get(1).getText().toString())) {
                Graduate.get(1).setError("Enter Institute Name!");
                return;
            }
            if (TextUtils.isEmpty(Graduate.get(2).getText().toString())) {
                Graduate.get(2).setError("Enter University!");
                return;
            }
            if (TextUtils.isEmpty(Graduate.get(3).getText().toString())) {
                Graduate.get(3).setError("Enter Year!");
                return;
            }
            Academia.Graduate = new Academic(Graduate.get(0).getText().toString(), Graduate.get(1).getText().toString(), Graduate.get(2).getText().toString(), Graduate.get(3).getText().toString());

        }
        if (layouts.get(2).getVisibility() == View.VISIBLE) {
            if (TextUtils.isEmpty(Intermediate.get(0).getText().toString())) {
                Intermediate.get(0).setError("Enter Title!");
                return;
            }
            if (TextUtils.isEmpty(Intermediate.get(1).getText().toString())) {
                Intermediate.get(1).setError("Enter Institute Name!");
                return;
            }
            if (TextUtils.isEmpty(Intermediate.get(2).getText().toString())) {
                Intermediate.get(2).setError("Enter Board Name!");
                return;
            }
            if (TextUtils.isEmpty(Intermediate.get(3).getText().toString())) {
                Intermediate.get(3).setError("Enter Year!");
                return;
            }
            Academia.Intermediate = new Academic(Intermediate.get(0).getText().toString(), Intermediate.get(1).getText().toString(), Intermediate.get(2).getText().toString(), Intermediate.get(3).getText().toString());

        }
        if (layouts.get(3).getVisibility() == View.VISIBLE) {
            if (TextUtils.isEmpty(School.get(0).getText().toString())) {
                School.get(0).setError("Enter Title!");
                return;
            }
            if (TextUtils.isEmpty(School.get(1).getText().toString())) {
                School.get(1).setError("Enter Institute Name!");
                return;
            }
            if (TextUtils.isEmpty(School.get(2).getText().toString())) {
                School.get(2).setError("Enter Board Name!");
                return;
            }
            if (TextUtils.isEmpty(School.get(3).getText().toString())) {
                School.get(3).setError("Enter Year!");
                return;
            }
            Academia.School = new Academic(School.get(0).getText().toString(), School.get(1).getText().toString(), School.get(2).getText().toString(), School.get(3).getText().toString());

        }
        Intent intent = new Intent(this, trainingActivity.class);
        startActivity(intent);
    }

    public void setListeners() {
        switches.get(0).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    layouts.get(0).setVisibility(View.VISIBLE);
                } else {
                    PostGraduate.get(0).setText("");
                    PostGraduate.get(1).setText("");
                    PostGraduate.get(2).setText("");
                    PostGraduate.get(3).setText("");
                    layouts.get(0).setVisibility(View.GONE);
                    Academia.PostGraduate = null;
                }
            }
        });
        switches.get(1).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    layouts.get(1).setVisibility(View.VISIBLE);
                } else {
                    Graduate.get(0).setText("");
                    Graduate.get(1).setText("");
                    Graduate.get(2).setText("");
                    Graduate.get(3).setText("");
                    layouts.get(1).setVisibility(View.GONE);
                    Academia.Graduate = null;
                }
            }
        });
        switches.get(2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    layouts.get(2).setVisibility(View.VISIBLE);
                } else {
                    Intermediate.get(0).setText("");
                    Intermediate.get(1).setText("");
                    Intermediate.get(2).setText("");
                    Intermediate.get(3).setText("");
                    layouts.get(2).setVisibility(View.GONE);
                    Academia.Intermediate = null;
                }
            }
        });
        switches.get(3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    layouts.get(3).setVisibility(View.VISIBLE);
                } else {
                    School.get(0).setText("");
                    School.get(1).setText("");
                    School.get(2).setText("");
                    School.get(3).setText("");
                    layouts.get(3).setVisibility(View.GONE);
                    Academia.School = null;
                }
            }
        });
    }
}
