package com.example.viewpagerapp.data;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.example.viewpagerapp.model.Article;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiNews {

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://newsapi.org/")
            .build();
    NewsApi services = retrofit.create(NewsApi.class);

    public void getListNews(NewsCallback newscallback) {
        Call<ArrayList<Article>> call = services.getNews("bitcoin");
        call.enqueue(new Callback<ArrayList<Article>>() {
            @Override
            public void onResponse(Call<ArrayList<Article>> call, Response<ArrayList<Article>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        newscallback.onSuccess(response.body());
                        Log.d("tag", response.body().toString());
                    } else {
                        Log.d("tag", "response body is null");
                        newscallback.onFailure(new NetworkErrorException());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Article>> call, Throwable t) {
                Log.d("tag", "Error");
                newscallback.onFailure(new Exception());
            }
        });
    }

    public interface NewsCallback {
        void onSuccess(ArrayList<Article> newsMS);

        void onFailure(Exception e);
    }

    public interface NewsApi {
        @GET("v2/everything")
        Call<ArrayList<Article>> getNews(@Query("q") String q);

    }
}
