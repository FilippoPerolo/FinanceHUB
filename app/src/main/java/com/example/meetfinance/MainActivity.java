package com.example.meetfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meetfinance.presentation.view.FirstActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CountryAdapter mAdapter;
    private ArrayList<CountryItem> mCountryList;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.customButton);
        Switch switchEnabled = findViewById(R.id.switch_bull);

        Spinner spinner = findViewById(R.id.spinner);
        // ajout images au spinner
        initList();

        // adapter pour spinner
        mAdapter = new CountryAdapter(this, mCountryList);
        spinner.setAdapter(mAdapter);

        // gestion clicks sur spinner
        spinnerClick(spinner);

        buttonManag(button);

        switcherManag(switchEnabled, button);

    }

    private void initList() {
        mCountryList = new ArrayList<>();
        mCountryList.add(new CountryItem(R.drawable.france));
        mCountryList.add(new CountryItem(R.drawable.allemagne));
        mCountryList.add(new CountryItem(R.drawable.italie));
        mCountryList.add(new CountryItem(R.drawable.russie));
        mCountryList.add(new CountryItem(R.drawable.chinne2));
        mCountryList.add(new CountryItem(R.drawable.espagne));
    }

    private void spinnerClick(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void buttonManag(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });
    }


    private void switcherManag(Switch switchEnabled, final Button button){
        switchEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
            }
        });
    }
    
    
}
