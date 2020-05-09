package com.example.meetfinance.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.meetfinance.ApiRequest;
import com.example.meetfinance.CountryAdapter;
import com.example.meetfinance.CountryItem;
import com.example.meetfinance.R;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    EditText etCompany;
    Button searchButton;
    Button listButton;
    ProgressBar searchBar;
    RequestQueue queue;
    String ticker;
    public ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
    private ApiRequest request;
    private Handler handler;

    private String[] imageUrls = new String[]{
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSWswnkjNX0sC05mNKzDzs9Z3i4w4_ud4eCsIl4xX7ecWMJdsX&s",
            "https://www.investawise.com/wp-content/uploads/2019/12/What-are-financial-markets-and-why-are-they-important-1.jpg",
            "https://www.procurement-academy.com/wp/wp-content/uploads/2018/05/Business-Finance-1.jpg",
            "https://wadv-prod-1f0120db-46d2-4038-90ab-ac2558260610.storage.googleapis.com/s3fs-public/2017-11/law-and-finance-1600x600_0.jpg",
            "https://www.airliquide.com/sites/airliquide.com/files/styles/retina_cover_page/public/investors-figures-banner-mobile.jpg?itok=kkftnImY"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

        initList();

        Spinner spinner = findViewById(R.id.spinner);
        mAdapter = new CountryAdapter(this, mCountryList);
        spinner.setAdapter(mAdapter);

        spinnerClick(spinner);

        initialize();

        searchButtonClick();

        listButtonClick();
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

    private void initialize() {
        // on initialise nos éléments
        request = new ApiRequest(queue, this);
        handler = new Handler();
        etCompany = (EditText) findViewById(R.id.et_company);
        searchButton = (Button) findViewById(R.id.buttonSend);
        searchBar = (ProgressBar) findViewById(R.id.progress_bar);
        listButton = (Button) findViewById(R.id.buttonSend2);
    }

    private void searchButtonClick() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String research = etCompany.getText().toString().trim();

                if (research.length() > 0) {
                    searchBar.setVisibility(View.VISIBLE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            searchBar.setVisibility(View.INVISIBLE); // on rend la barre de progression invisible
                            Intent intent = new Intent(FirstActivity.this, FourthActivity.class); // getApplicationContext()
                            // on crée un Bundle pour ajouter des informations qu'on va passer dans l'autre activité
                            ticker = etCompany.getText().toString().toUpperCase();

                            intent.putExtra("ticker", ticker);
                            startActivity(intent);

                        }
                    }, 2000);

                } else {
                    Toast.makeText(getApplicationContext(), "You must type a company name !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void listButtonClick() {

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                searchBar.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.Tada)
                        .duration(1000)
                        .repeat(1)
                        .playOn(listButton);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        searchBar.setVisibility(View.INVISIBLE); // on rend la barre de progression invisible

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

    private void spinnerClick(Spinner spinner){
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


}
