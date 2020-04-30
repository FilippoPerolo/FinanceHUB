package com.example.meetfinance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FourthActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://financialmodelingprep.com/api/v3/company/profile/";
    private TextView textViewResult,  tv_companyName, tv_industry, tv_description, tv_sector;
    private String companyName, industry, companyDesc, sector;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Gson gson;
    private SharedPreferences sharedPreferences;
    String ticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        textViewResult = findViewById(R.id.text_view_result);
        tv_companyName = findViewById(R.id.tv_companyName);
        tv_industry = findViewById(R.id.tv_industry);
        tv_description = findViewById(R.id.tv_description);
        tv_sector = findViewById((R.id.tv_sector));

        ticker = Objects.requireNonNull(getIntent().getExtras()).getString("ticker");
        textViewResult.setText(ticker);


        Bundle bundle = getIntent().getExtras();

        companyName = bundle.getString("txtHeader");
        industry = bundle.getString("txtFooter");
        companyDesc = bundle.getString("txt4");
        sector = bundle.getString("txt3");

        tv_companyName.setText(companyName);
        tv_industry.setText(industry);
        tv_description.setText(companyDesc);
        tv_sector.setText(sector);

        Intent intent = getIntent();

        sharedPreferences = getSharedPreferences("Esiea_3A", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://financialmodelingprep.com/api/v3/company/profile/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getProfiles();

    }

    private void getProfiles(){
        Call<List<Profile>> call = jsonPlaceHolderApi.getProfiles(ticker);
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code : "+ response.code());
                    return;
                }

                List<Profile> profiles = response.body();

                for (Profile profile : profiles){
                    String content = "";
                    content += "Company name: " + profile.getCompanyName()+"\n";
                    content += "Industry: " + profile.getIndustry()+"\n";
                    content += "Description: " + profile.getDescription()+"\n";
                    content += "Sector: " + profile.getSector()+"\n";
                    content += "Symbol: " + profile.getSymbol()+"\n";
                    content += "Price: " + profile.getPrice()+"\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }

    public void initialize() {
        textViewResult = findViewById(R.id.text_view_result);
        tv_companyName = findViewById(R.id.tv_companyName);
        tv_industry = findViewById(R.id.tv_industry);
        tv_description = findViewById(R.id.tv_description);
        tv_sector = findViewById((R.id.tv_sector));
    }
}
