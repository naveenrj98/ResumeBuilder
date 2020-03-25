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
import com.developer.rjtech.resumebuilder.datamodel.AoiData;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InterestActivity extends AppCompatActivity {

    @BindView(R.id.aoiList)
    ListView aoiList;
    @BindView(R.id.aoiText)
    TextView textView;
    @BindView(R.id.interest)
    TextInputEditText editText;
    private ArrayAdapter<String> aoiAdapter;
    private int interestCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        ButterKnife.bind(this);
        registerForContextMenu(aoiList);
       // textView.setTypeface(fontSelector.font);
        aoiAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        aoiList.setAdapter(aoiAdapter);
        if (AoiData.interests.size() > 0) {
            aoiAdapter.clear();
            aoiAdapter.addAll(AoiData.interests);
        } else {
            editText.setText(R.string.interest0);
            interestCount++;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.aoiList) {
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
            AoiData.interests.remove(selected);
            interestCount--;
            aoiAdapter.clear();
            aoiAdapter.addAll(AoiData.interests);
        } else {
            return false;
        }
        return true;
    }

    @OnClick(R.id.addButton)
    public void addQualities() {
        String interest = editText.getText().toString();
        if (TextUtils.isEmpty(interest)) {
            editText.setError("Please Enter Interest!");
            return;
        }
        aoiAdapter.add(interest);
        aoiAdapter.notifyDataSetChanged();
        switch (interestCount) {
            case 0:
                editText.setText(R.string.interest0);
                break;
            case 1:
                editText.setText(R.string.interest1);
                break;
            case 2:
                editText.setText(R.string.interest2);
                break;
            case 3:
                editText.setText(R.string.interest3);
                break;
            default:
                editText.setText("");
                break;
        }
        interestCount++;
        AoiData.interests.add(interest);
    }

    @OnClick(R.id.submitButton)
    public void submitData() {
        Intent intent = new Intent(this, finalActivity.class);
        startActivity(intent);
    }

}
