package com.example.meetfinance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class FirstActivity extends AppCompatActivity {

    EditText etCompany;
    Button searchButton;
    Button listButton;
    ProgressBar searchBar;
    ProgressBar listBar;
    ListView recentResearch;
    private RequestQueue queue;
    private ApiRequest request;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // on initialise la RequestQueue
        queue = MySingleton.getInstance(this).getRequestQueue();
        request = new ApiRequest(queue, this);
        handler = new Handler();

        etCompany = (EditText) findViewById(R.id.et_company);
        searchButton = (Button) findViewById(R.id.buttonSend);
        searchBar = (ProgressBar) findViewById(R.id.progress_bar);
        recentResearch = (ListView) findViewById(R.id.recentResearch);

        listButton = (Button) findViewById(R.id.buttonSend2);
        listBar = (ProgressBar) findViewById(R.id.progress_bar2);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String research = etCompany.getText().toString().trim();

                if (research.length() > 0) {
                    searchBar.setVisibility(View.VISIBLE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            request.checkCompanyName(research, new ApiRequest.CheckCompanyCallback() {
                                @Override
                                public void onSuccess(String symbol, double price) {
                                    searchBar.setVisibility(View.INVISIBLE); // on rend la barre de progression invisible

                                    // on crée un Intent pour passer dans une autre activité
                                    Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                                    // on crée un Bundle pour ajouter des informations qu'on va passer dans l'autre activité
                                    Bundle extras = new Bundle();
                                    extras.putString("SYMBOL", symbol);
                                    extras.putDouble("PRICE", price);
                                    intent.putExtras(extras);
                                    startActivity(intent);

                                }

                                @Override
                                public void notExist(String message) {
                                    searchBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(String message) {
                                    searchBar.setVisibility(View.INVISIBLE);

                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }, 2000);

                } else {
                    Toast.makeText(getApplicationContext(), "You must type a company name !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBar.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listBar.setVisibility(View.INVISIBLE); // on rend la barre de progression invisible

                        // on crée un Intent pour passer dans une autre activité
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        // on crée un Bundle pour ajouter des informations qu'on va passer dans l'autre activité
                        //Bundle extras = new Bundle();
                        //intent.putExtras(extras);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
    }

}
