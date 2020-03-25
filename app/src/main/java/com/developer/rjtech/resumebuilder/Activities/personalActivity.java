package com.developer.rjtech.resumebuilder.Activities;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.developer.rjtech.resumebuilder.R;
import com.developer.rjtech.resumebuilder.datamodel.Personalia;
import com.developer.rjtech.resumebuilder.fragments.ExitDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class personalActivity extends AppCompatActivity {

    @BindViews({R.id.personal, R.id.objective})
    List<TextView> textView;
    @BindViews({R.id.name, R.id.email, R.id.phone, R.id.address, R.id.objectivetext})
    List<TextInputEditText> editTexts;
    Date selectedDate;
    @BindView(R.id.dobText)
    TextInputEditText dob;
    Button button;
    TextInputEditText getDob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
        new fontSelector(this);
//        textView.get(0).setTypeface(fontSelector.font);
//        textView.get(1).setTypeface(fontSelector.font);
//        editTexts.get(4).setTypeface(fontSelector.font);

        button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
        getDob = findViewById(R.id.dobText);
        getDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate();
            }
        });
    }

    public void chooseDate() {
        int day, year, month;
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        if (selectedDate != null) {
            day = calendar.get(Calendar.DAY_OF_MONTH);
            year = calendar.get(Calendar.YEAR) + 1900;
            month = calendar.get(Calendar.MONTH);
        } else {
            year = calendar.get(Calendar.YEAR) - 22;
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.clear();
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.YEAR, year);
                selectedDate = calendar.getTime();
                SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                dob.setText(format.format(selectedDate));
            }
        }, year, month, day);
        dialog.show();
    }


    public void submitData() {
        Personalia.name = editTexts.get(0).getText().toString();
        Personalia.email = editTexts.get(1).getText().toString();
        Personalia.phone = editTexts.get(2).getText().toString();
        Personalia.address = editTexts.get(3).getText().toString();
        Personalia.dateOfBirth = dob.getText().toString();
        Personalia.objective = editTexts.get(4).getText().toString();
        Intent intent = new Intent(this, personalQualityActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new ExitDialogFragment().show(getSupportFragmentManager(), "Exit");
    }

}
