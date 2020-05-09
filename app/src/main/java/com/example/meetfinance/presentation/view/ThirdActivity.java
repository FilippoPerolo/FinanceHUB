package com.example.meetfinance.presentation.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.meetfinance.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

public class ThirdActivity extends AppCompatActivity {
    private RelativeLayout infoCompany;
    private TableLayout details;
    private TextView companyText;
    private TextView priceText;
    private TextView exchangeText;

    ImageView imageView;

    TextView tv_name;
    TextView tv_symbol;
    TextView tv_exchange;
    TextView tv_price;

    String symbolComp;
    String nameComp;
    String exchangeComp;
    Double priceComp;

    private SharedPreferences sharedPreferences;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initialize();

        getBundle();

        settingText();

        imageView = (ImageView) findViewById(R.id.imagePicasso);

        String url = "https://financialmodelingprep.com/images-New-jpg/" + symbolComp + ".jpg";
        animate(url);

        sharedPreferences = getSharedPreferences("Esiea_3A", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
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
