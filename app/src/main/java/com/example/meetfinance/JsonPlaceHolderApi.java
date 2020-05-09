package com.example.meetfinance;

import com.example.meetfinance.presentation.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("/{ticker}")
    Call<List<Post>> getPosts(@Path("ticker") String ticker);
    //    Call<List<Profile>> getProfiles(@Path("ticker") String ticker);

}
