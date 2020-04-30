package com.example.meetfinance;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("/{ticker}")
    Call<List<Post>> getPosts(@Path("ticker") String ticker);
}
