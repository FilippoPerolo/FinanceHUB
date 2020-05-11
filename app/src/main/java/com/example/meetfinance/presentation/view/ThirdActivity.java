package com.example.meetfinance.presentation.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.meetfinance.R;
import com.squareup.picasso.Picasso;

public class ThirdActivity extends AppCompatActivity {
    // Variables
    private ImageView imageView;
    private TextView tv_name;
    private TextView tv_symbol;
    private TextView tv_exchange;
    private TextView tv_price;
    private String symbolComp;
    private String nameComp;
    private String exchangeComp;
    private Double priceComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initialize(); // initialise les éléments

        getBundle(); // chope les informations de l'activité précédente

        settingText();

        imageView = findViewById(R.id.imagePicasso);

        String url = "https://financialmodelingprep.com/images-New-jpg/" + symbolComp + ".jpg";
        animate(url); // insère une image diff pour chaque élément de la liste avec Picasso

    }

    private void initialize() {
        tv_name = findViewById(R.id.tv_name);
        tv_symbol = findViewById(R.id.tv_symbol);
        tv_exchange = findViewById(R.id.tv_exchange);
        tv_price = findViewById(R.id.tv_price);
    }

    private void animate(String url) {
        YoYo.with(Techniques.Wave)
                .duration(2000)
                .repeat(1)
                .playOn(imageView);
        Picasso.get()
                .load(url).
                into(imageView);
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        nameComp = bundle.getString("txtHeader");
        symbolComp = bundle.getString("txtFooter");
        exchangeComp = bundle.getString("txt4");
        priceComp = bundle.getDouble("txt3");
    }

    private void settingText() {
        tv_name.setText(nameComp);
        tv_symbol.setText(symbolComp);
        tv_exchange.setText(exchangeComp);
        tv_price.setText("" + priceComp);
    }
}
