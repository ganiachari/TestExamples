package com.testexamples.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.testexamples.R;
import com.testexamples.adapters.AdapterImageList;
import com.testexamples.databinding.ActivityMainBinding;
import com.testexamples.models.Images;
import com.testexamples.retrofit.ApiClient;
import com.testexamples.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*List of images we are going to display over here
* implemented on pull refresh */

public class ActivityImagesList extends AppCompatActivity {
    ActivityMainBinding binding;
    List<Images> movieList;
    RecyclerView recyclerView;
    AdapterImageList adapterImageList;
    private SwipeRefreshLayout swipeRefreshLayout;
    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ActivityImagesList.this, R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        movieList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterImageList = new AdapterImageList(getApplicationContext(), movieList);
        recyclerView.setAdapter(adapterImageList);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        callApi();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                callApi();
            }
        });

    }

    private void callApi() {
        Call<List<Images>> call = apiService.getMovies();
        call.enqueue(new Callback<List<Images>>() {
            @Override
            public void onResponse(Call<List<Images>> call, Response<List<Images>> response) {
                movieList = response.body();
                Log.d("TAG", "Response = " + movieList);
                adapterImageList.setImagesList(movieList);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Images>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }


}