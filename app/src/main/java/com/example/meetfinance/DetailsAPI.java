package com.example.meetfinance;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DetailsAPI {


    @GET("/api/v3/stock/real-time-price")
    Call<RestDetailsResponse> getDetailsResponse();
}
