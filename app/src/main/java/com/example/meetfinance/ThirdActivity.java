package com.example.meetfinance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "Note";
    private RelativeLayout infoCompany;
    private TableLayout details;
    private TextView companyText;
    private TextView priceText;
    private TextView exchangeText;

    TextView tv_name;
    TextView tv_symbol;
    TextView tv_exchange;
    TextView tv_price;

    String symbolComp;
    String nameComp;
    String exchangeComp;
    Double priceComp;

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
        tv_name = findViewById(R.id.tv_name);
        tv_symbol = findViewById(R.id.tv_symbol);
        tv_exchange = findViewById(R.id.tv_exchange);
        tv_price = findViewById(R.id.tv_price);


        Bundle bundle = getIntent().getExtras();

        nameComp = bundle.getString("txtHeader");
        symbolComp = bundle.getString("txtFooter");
        exchangeComp = bundle.getString("txt4");
        priceComp = bundle.getDouble("txt3");

        tv_name.setText(nameComp);
        tv_symbol.setText(symbolComp);
        tv_exchange.setText(exchangeComp);
        tv_price.setText(""+priceComp);

        Intent intent = getIntent();
        Symbol symbolsList = (Symbol) intent.getSerializableExtra("ONE_COMPANY");

        Symbol extraSent = getIntent().getParcelableExtra("extra");


        initialize();

    /*    assert symbolsList != null;
        companyText.setText(String.valueOf(symbolsList.getName()));
        priceText.setText(symbolsList.getSymbol()); */

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
*/

    public void initialize() {
        infoCompany = findViewById(R.id.info);
        details = (TableLayout) findViewById(R.id.details);
        companyText = (TextView) findViewById(R.id.tv_name);
        priceText = (TextView) findViewById((R.id.tv_price));
        exchangeText = (TextView) findViewById((R.id.tv_exchange));
    }
}
