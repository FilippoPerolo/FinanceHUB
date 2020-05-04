package com.example.meetfinance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.meetfinance.data.SymbolAPI;
import com.example.meetfinance.presentation.model.RestFinanceResponse;
import com.example.meetfinance.presentation.model.Symbol;
import com.example.meetfinance.presentation.view.FirstActivity;
import com.example.meetfinance.presentation.view.ListAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String BASE_URL = "https://financialmodelingprep.com/";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private RequestQueue queue;
    List<Symbol> symbolsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        queue = MySingleton.getInstance(this).getRequestQueue();


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);


        sharedPreferences = getSharedPreferences("Esiea_3A", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        symbolsList = getDataFromCache();
        if (symbolsList != null) {
            showList(symbolsList);
        } else {
            makeApiCall();
        }

        EditText editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }


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
        mAdapter = new ListAdapter(symbolsList, new ListAdapter.OnItemClickListener() {
            public void onItemClick(Symbol symbolsList) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.putExtra("txtHeader", symbolsList.getName());
                intent.putExtra("txtFooter", symbolsList.getSymbol());
                intent.putExtra("txt3", symbolsList.getPrice());
                intent.putExtra("txt4", symbolsList.getExchange());
                YoYo.with(Techniques.RubberBand)
                        .duration(2000)
                        .repeat(1)
                        .playOn(mDrawerLayout);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }


    private void makeApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        SymbolAPI symbolAPI = retrofit.create(SymbolAPI.class);

        Call<RestFinanceResponse> call = symbolAPI.getSymbolResponse();
        call.enqueue(new Callback<RestFinanceResponse>() {
            @Override
            public void onResponse(Call<RestFinanceResponse> call, Response<RestFinanceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Symbol> symbolsList = response.body().getSymbolsList();
                    Toast.makeText(getApplicationContext(), "Api Success", Toast.LENGTH_SHORT).show();
                    saveList(symbolsList);
                    showList(symbolsList);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestFinanceResponse> call, Throwable t) {
                showError();
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


    // gestion des clicks sur les items dans la navigationView
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()) { // on vérifie si l'item sur lequel on clique est coché
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        mDrawerLayout.closeDrawers();

        switch (item.getItemId()) {
            case R.id.home:
                Intent intent1 = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(intent1);
                finish();
                return true;
            case R.id.back:
                Intent intent2 = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent2);
                finish();
                return true;
            case R.id.quit:
                Toast.makeText(getApplicationContext(), "Quitter...", Toast.LENGTH_SHORT).show();
                return true;
        }
        return true;
    }

    private void filter(String text){
        ArrayList<Symbol> filteredList = new ArrayList<>();

        for (Symbol symbol : symbolsList){
            if(symbol.getSymbol().toUpperCase().contains(text.toUpperCase())){
                filteredList.add(symbol);
            }
        }


        mAdapter.filterList(filteredList);
    }
}

