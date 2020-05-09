package com.example.meetfinance.data;

import com.example.meetfinance.presentation.model.RestCompanyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CompanyApi {
    @GET("/api/v3/company/profile/{ticker}")
    Call<RestCompanyResponse> getProfile(@Path("ticker") String ticker);
}
