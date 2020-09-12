package com.testexamples.retrofit;

import com.testexamples.models.Images;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("https://picsum.photos/v2/list?page=2&limit=20")
    Call<List<Images>> getMovies();
}
