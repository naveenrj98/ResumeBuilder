package com.developer.rjtech.resumebuilder.Activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.developer.rjtech.resumebuilder.R;
import com.developer.rjtech.resumebuilder.datamodel.PdfHelper;
import com.google.android.material.snackbar.Snackbar;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;


import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.android.material.snackbar.Snackbar.make;

public class finalActivity extends AppCompatActivity {

    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.successText)
    TextView successText;
    Document document = new Document();
    PdfHelper pdf = new PdfHelper(this, document);

    private String path = Environment.getExternalStorageDirectory() + "/Resumia";
    private String filename = "Resume.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        ButterKnife.bind(this);
        successText.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        requestPermission();
    }

    public void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 2909);
            } else {
                Log.i("Permission", "Permission already Granted");
                createPdf();
            }
        } else {
            Log.i("Permission", "Permission Request not needed!");
            createPdf();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 2909: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("Permission", "Permission Granted!");
                    createPdf();
                } else {
                    Log.i("Permission", "Permission Denied!");
                    Snackbar snackbar = make(progressBar, "Permission Denied!", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            requestPermission();
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    TextView mainTextView = (snackbar.getView()).findViewById(R.id.snackbar_text);
                    mainTextView.setTextColor(Color.CYAN);
                    snackbar.show();
                }
            }
            break;
        }
    }

    public void createPdf() {
        try {
            pdf.createPdf(path, filename);
        } catch (FileNotFoundException | DocumentException e) {
            Log.i("Exception", String.valueOf(e));
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setvisibility();
            }
        }, 1000);
    }

    public void setvisibility() {
        progressBar.setVisibility(View.GONE);
        successText.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.successText)
    public void openPdf() {
        pdf.viewPdf(filename);
    }
}
