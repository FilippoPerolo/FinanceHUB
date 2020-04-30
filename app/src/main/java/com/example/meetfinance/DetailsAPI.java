package com.example.meetfinance;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailsAPI {
    @GET("/api/v3/company/profile/")
    Call<RestDetailsResponse> getDetailsResponse(@Query("query") String companyName);
}
