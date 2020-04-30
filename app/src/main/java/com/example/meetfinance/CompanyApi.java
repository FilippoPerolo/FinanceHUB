package com.example.meetfinance;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CompanyApi {
    @GET("/api/v3/company/profile/{ticker}")
    Call<RestCompanyResponse> getProfile(@Path("ticker") String ticker);
}
