package com.developer.rjtech.resumebuilder.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.developer.rjtech.resumebuilder.R;
import com.developer.rjtech.resumebuilder.datamodel.Qualities;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class personalQualityActivity extends AppCompatActivity {

    @BindView(R.id.qualitylist)
    ListView qualityList;
    @BindView(R.id.pqualText)
    TextView textView;
    @BindView(R.id.addqual)
    TextInputEditText editText;
    private ArrayAdapter<String> qualAdapter;
    private int qualCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_quality);
        ButterKnife.bind(this);
//        textView.setTypeface(fontSelector.font);
        qualAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        qualityList.setAdapter(qualAdapter);
        registerForContextMenu(qualityList);
        if (Qualities.qualities.size() > 0) {
            qualAdapter.clear();
            qualAdapter.addAll(Qualities.qualities);
        } else {
            editText.setText(R.string.quality0);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.qualitylist) {
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
            qualCount--;
            Qualities.qualities.remove(selected);
            qualAdapter.clear();
            qualAdapter.addAll(Qualities.qualities);
        } else {
            return false;
        }
        return true;
    }

    @OnClick(R.id.addButton)
    public void addQualities() {
        String quality = editText.getText().toString();
        if (TextUtils.isEmpty(quality)) {
            editText.setError("Please Enter Quality");
            return;
        }
        qualAdapter.add(quality);
        qualAdapter.notifyDataSetChanged();
        Qualities.qualities.add(quality);
        switch (qualCount) {
            case 1:
                editText.setText(R.string.quality1);
                break;
            case 2:
                editText.setText(R.string.quality2);
                break;
            case 3:
                editText.setText(R.string.quality3);
                break;
            case 4:
                editText.setText(R.string.quality4);
                break;
            default:
                editText.setText("");
        }
        qualCount++;
    }

    @OnClick(R.id.submitButton)
    public void submitData() {
        Intent intent = new Intent(this, techSkillsActivity.class);
        startActivity(intent);
    }

}
