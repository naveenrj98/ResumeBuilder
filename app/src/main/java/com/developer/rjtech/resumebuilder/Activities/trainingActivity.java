package com.developer.rjtech.resumebuilder.Activities;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.developer.rjtech.resumebuilder.R;
import com.developer.rjtech.resumebuilder.adapters.TrainingAdapter;
import com.developer.rjtech.resumebuilder.datamodel.Internships;
import com.developer.rjtech.resumebuilder.datamodel.Training;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class trainingActivity extends AppCompatActivity {

    @BindView(R.id.trainingList)
    ListView trainingList;
    @BindView(R.id.trainingText)
    TextView trainingText;
    @BindViews({R.id.fromText, R.id.toText})
    List<TextInputEditText> Date;
    @BindViews({R.id.trainingTitle, R.id.projecttitle, R.id.projectDetails})
    List<TextInputEditText> editTexts;
    java.util.Date selectedTo, selectedFrom;
    private int trainingCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        ButterKnife.bind(this);
        registerForContextMenu(trainingList);
//        trainingText.setTypeface(fontSelector.font);
        if (Internships.internships.size() > 0) {
            TrainingAdapter adapter = new TrainingAdapter(this, Internships.internships);
            trainingList.setAdapter(adapter);
        } else {
            editTexts.get(0).setText(R.string.trainingTitle0);
            editTexts.get(1).setText(R.string.projectTitle0);
            editTexts.get(2).setText(R.string.projectDetails0);
            Date.get(0).setText(R.string.trainingFrom0);
            Date.get(1).setText(R.string.trainingTo0);
            trainingCount++;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.trainingList) {
            menu.setHeaderIcon(R.drawable.ic_delete);
            menu.setHeaderTitle("Delete this Item?");
            menu.add(0, v.getId(), 0, "Yes");
            menu.add(0, v.getId(), 0, "Cancel");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selected = info.position;
        if (item.getTitle() == "Yes") {
            Internships.internships.remove(selected);
            trainingCount--;
            TrainingAdapter adapter = new TrainingAdapter(this, Internships.internships);
            trainingList.setAdapter(adapter);
        } else {
            return false;
        }
        return true;
    }

    @OnClick(R.id.fromText)
    public void getFromDate() {
        int day, month, year;
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        if (selectedFrom != null) {
            day = selectedFrom.getDate();
            month = selectedFrom.getMonth();
            year = selectedFrom.getYear() + 1900;
        } else {
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
        }
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.clear();
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.YEAR, year);

                selectedFrom = calendar.getTime();

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date.get(0).setError(null);
                Date.get(0).setText(format.format(selectedFrom));
            }
        }, year, month, day);
        dialog.show();
    }

    @OnClick(R.id.toText)
    public void getToDate() {
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int day, month, year;
        if (selectedTo != null) {
            day = selectedTo.getDate();
            month = selectedTo.getMonth();
            year = selectedTo.getYear() + 1900;
        } else {
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
        }

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.clear();
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.YEAR, year);

                selectedTo = calendar.getTime();

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date.get(1).setError(null);
                Date.get(1).setText(format.format(selectedTo));
            }
        }, year, month, day);
        dialog.show();
    }

    @OnClick(R.id.addButton)
    public void addSkills() {
        String ttitle = editTexts.get(0).getText().toString();
        String tdate = Date.get(0).getText().toString() + "-" + Date.get(1).getText().toString();
        String ptittle = editTexts.get(1).getText().toString();
        String pdetail = editTexts.get(2).getText().toString();
        if (TextUtils.isEmpty(ttitle)) {
            editTexts.get(0).setError("Please Enter Training Title!");
            return;
        }
        if (TextUtils.isEmpty(ptittle)) {
            editTexts.get(1).setError("Please Enter Project Title!");
            return;
        }
        if (TextUtils.isEmpty(pdetail)) {
            editTexts.get(2).setError("Please Enter Project Details!");
            return;
        }
        if (TextUtils.isEmpty(Date.get(0).getText().toString())) {
            Date.get(0).setError("Enter Date");
            return;
        }
        if (TextUtils.isEmpty(Date.get(1).getText().toString())) {
            Date.get(1).setError("Enter Date");
            return;
        }
        Internships.internships.add(new Training(ttitle, tdate, ptittle, pdetail));

        switch (trainingCount) {
            case 0:
                editTexts.get(0).setText(R.string.trainingTitle0);
                editTexts.get(1).setText(R.string.projectTitle0);
                editTexts.get(2).setText(R.string.projectDetails0);
                Date.get(0).setText(R.string.trainingFrom0);
                Date.get(1).setText(R.string.trainingTo0);
            case 1:
                editTexts.get(0).setText(R.string.trainingTitle1);
                editTexts.get(1).setText(R.string.projectTitle1);
                editTexts.get(2).setText(R.string.projectDetails1);
                Date.get(0).setText("");
                Date.get(1).setText("");
                break;
            default:
                editTexts.get(0).setText("");
                editTexts.get(1).setText("");
                editTexts.get(2).setText("");
                Date.get(0).setText("");
                Date.get(1).setText("");
        }
        trainingCount++;
        TrainingAdapter adapter = new TrainingAdapter(this, Internships.internships);
        trainingList.setAdapter(adapter);
        editTexts.get(0).requestFocus();
    }

    @OnClick(R.id.submitButton)
    public void submitData() {
        Intent intent = new Intent(this, InterestActivity.class);
        startActivity(intent);
    }

}
