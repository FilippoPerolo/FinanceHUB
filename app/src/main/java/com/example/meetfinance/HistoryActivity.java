package com.example.meetfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private String companySymbol;
    private double companyPrice;
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras.getString("SYMBOL") != null && extras.getDouble("PRICE") > 0) {
            companySymbol = extras.getString("SYMBOL");
            companyPrice = extras.getDouble("PRICE");
        }
    }
}
