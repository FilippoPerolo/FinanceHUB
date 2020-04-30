package com.example.meetfinance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://financialmodelingprep.com/";
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        String tickerSent = getIntent().getStringExtra("ticker");

        sharedPreferences = getSharedPreferences("Esiea_3A", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Details> detailsList = getDataFromCache();
        if (detailsList != null) {
            showList(detailsList);
        } else {
            makeApiCall();
        }
    }

    private List<Details> getDataFromCache() {
        String jsonDetails = sharedPreferences.getString(Constants.KEY_DETAILS_LIST, null);

        if (jsonDetails == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Details>>() {
            }.getType();
            return gson.fromJson(jsonDetails, listType);
        }
    }

    private void showList(List<Details> detailsList) {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void makeApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final DetailsAPI detailsAPI = retrofit.create(DetailsAPI.class);
        String tickerSent = getIntent().getStringExtra("ticker");


        Call<RestDetailsResponse> call = detailsAPI.getDetailsResponse(tickerSent);
        call.enqueue(new Callback<RestDetailsResponse>() {
            @Override
            public void onResponse(Call<RestDetailsResponse> call, Response<RestDetailsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Details> detailsList = response.body().getDetailsList();
                    Toast.makeText(getApplicationContext(), "Api Success", Toast.LENGTH_SHORT).show();
                    saveList(detailsList);
                    showList(detailsList);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestDetailsResponse> call, Throwable t) {
                showError();
            }
        });

    }

    private void showError() {
        Toast.makeText(this, "Api Error", Toast.LENGTH_SHORT).show();
    }

    private void saveList(List<Details> detailsList) {
        String jsonString = gson.toJson(detailsList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_SYMBOLS_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();

    }

}
