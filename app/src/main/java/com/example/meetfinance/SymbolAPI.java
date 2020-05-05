package com.example.meetfinance;

import com.example.meetfinance.presentation.model.RestFinanceResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SymbolAPI {
    @GET("/api/v3/company/stock/list")
    Call<RestFinanceResponse> getSymbolResponse();
}