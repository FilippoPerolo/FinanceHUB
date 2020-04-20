package com.example.meetfinance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryActivity extends AppCompatActivity {

    private String companySymbol;
    private double companyPrice;
    private TextView test;
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

       // mAdapter = new MyAdapter(this, data);
        recyclerView.setAdapter(mAdapter);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras.getString("SYMBOL") != null && extras.getDouble("PRICE") > 0) {
            companySymbol = extras.getString("SYMBOL");
            companyPrice = extras.getDouble("PRICE");
        }
    }
}
