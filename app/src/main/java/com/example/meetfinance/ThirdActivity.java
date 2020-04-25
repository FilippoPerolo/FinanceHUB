package com.example.meetfinance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ThirdActivity extends AppCompatActivity {

    private RelativeLayout infoCompany;
    private TableLayout details;

    private static final String BASE_URL = "https://financialmodelingprep.com/";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        /*Intent intent = getIntent();
        Symbol currentSymbol = (Symbol) intent.getSerializableExtra("ONE_COMPANY");
        initialize();*/
        sharedPreferences = getSharedPreferences("Esiea_3A", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
/*
        List<Symbol> symbolsList = getDataFromCache();
        if (symbolsList != null) {
            showList(symbolsList);
        } else {
            makeApiCall();
        } */


    }
/*
    private List<Symbol> getDataFromCache() {
        String jsonSymbols = sharedPreferences.getString(Constants.KEY_SYMBOLS_LIST, null);

        if (jsonSymbols == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Symbol>>() {
            }.getType();
            return gson.fromJson(jsonSymbols, listType);
        }
    }

    private void showList(List<Symbol> symbolsList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(symbolsList, getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        DetailsAPI detailsAPI = retrofit.create(DetailsAPI.class);

        Call<RestDetailsResponse> call = detailsAPI.getDetailsResponse();
        call.enqueue(new Callback<RestDetailsResponse>() {
            @Override
            public void onResponse(Call<RestDetailsResponse> call, Response<RestDetailsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String detailsAPIList = response.body().getSymbol2();
                } else {
                    showError();
                }

        }


        @Override
        public void onFailure (Call < RestDetailsResponse > call, Throwable t){

        }
    });

}


    private void saveList(List<Symbol> symbolsList) {
        String jsonString = gson.toJson(symbolsList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_SYMBOLS_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();

    }

    private void showError() {
        Toast.makeText(this, "Api Error", Toast.LENGTH_SHORT).show();
    }


    public void initialize() {
        infoCompany = findViewById(R.id.info);
        details = (TableLayout) findViewById(R.id.details);

    } */
}
