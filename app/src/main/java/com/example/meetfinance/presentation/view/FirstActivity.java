package com.example.meetfinance.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.meetfinance.R;

public class FirstActivity extends AppCompatActivity {
    private Button listButton;
    private ProgressBar searchBar;
   // private ApiRequest request;
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

        // Viewpager pour faire défiler images avec Picasso
        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

        // initialise variables
        initialize();

        // gestion bouton search
        //searchButtonClick();

        // gestion bouton list
        listButtonClick();
    }

    private void initialize() {
        // on initialise nos éléments
       // request = new ApiRequest(queue, this);
        handler = new Handler();
        EditText etCompany = findViewById(R.id.et_company);
        Button searchButton = findViewById(R.id.buttonSend);
        searchBar = findViewById(R.id.progress_bar);
        listButton = findViewById(R.id.buttonSend2);
    }

    private void listButtonClick() {
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                searchBar.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.Tada)  // animation avec Yoyo, le bouton tremble
                        .duration(1000)
                        .repeat(1)
                        .playOn(listButton);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        searchBar.setVisibility(View.INVISIBLE); // on rend la barre invisible
                        // on crée un Intent pour passer dans une autre activité
                        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                        // on crée un Bundle pour ajouter des informations qu'on va passer dans l'autre activité
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
    }

}
