package com.example.meetfinance.presentation.controller;

import android.content.SharedPreferences;

import com.example.meetfinance.Singletons;
import com.example.meetfinance.data.Constants;
import com.example.meetfinance.presentation.model.RestFinanceResponse;
import com.example.meetfinance.presentation.model.Symbol;
import com.example.meetfinance.presentation.view.SecondActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondController {
    // Variables
    public static List<Symbol> symbolsList;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private SecondActivity view;

    public SecondController(SecondActivity secondActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = secondActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {
        symbolsList = getDataFromCache();

        if (symbolsList != null) {
            view.showList(symbolsList);
        } else {
            makeApiCall();
        }
    }

    private void makeApiCall() { // appel à l'API Rest
        Call<RestFinanceResponse> call = Singletons.getSymbolAPI().getSymbolResponse();
        call.enqueue(new Callback<RestFinanceResponse>() {
            @Override
            public void onResponse(Call<RestFinanceResponse> call, Response<RestFinanceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Symbol> symbolsList = response.body().getSymbolsList();
                    saveList(symbolsList);
                    view.showList(symbolsList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestFinanceResponse> call, Throwable t) {
                view.showError();
            }
        });

    }

    private void saveList(List<Symbol> symbolsList) { // sauvegarde la liste des éléments
        String jsonString = gson.toJson(symbolsList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_SYMBOLS_LIST, jsonString)
                .apply();
    }

    private List<Symbol> getDataFromCache() { // sauvegarde les données en cache
        String jsonSymbols = sharedPreferences.getString(Constants.KEY_SYMBOLS_LIST, null);
        if (jsonSymbols == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Symbol>>() {
            }.getType();
            return gson.fromJson(jsonSymbols, listType);
        }
    }

}
