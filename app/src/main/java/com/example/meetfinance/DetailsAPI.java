package com.example.meetfinance;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DetailsAPI {
    @GET("/api/v3/company/profile/{ticker}")
    Call<RestDetailsResponse> getDetailsResponse(@Path("ticker") String ticker);
}
