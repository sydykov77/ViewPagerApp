package com.example.viewpagerapp.data;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.example.viewpagerapp.model.CatM;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CatApi {
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://cat-fact.herokuapp.com/")
            .build();

    ApiCats serrvicess = retrofit.create(ApiCats.class);

    public void getListCats(CatCallback catCallback){
        Call<ArrayList<CatM>> call = serrvicess.getCats("cat","10");
        call.enqueue(new Callback<ArrayList<CatM>>() {
            @Override
            public void onResponse(Call<ArrayList<CatM>> call, Response<ArrayList<CatM>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Log.d("tag", response.body().toString());
                        catCallback.onSuccess(response.body());
                    }else {
                        Log.d("tag", "response body is null");
                        catCallback.onFailure(new NetworkErrorException());
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<CatM>> call, Throwable t) {
                Log.d("tag", "Error");
                catCallback.onFailure(new Exception());

            }
        });
    }

    public void getCatText(CatCallback catCallback){
        Call<CatM> call = serrvicess.getCatById();
        call.enqueue(new Callback<CatM>() {
            @Override
            public void onResponse(Call<CatM> call, Response<CatM> response) {
                if (response.isSuccessful() && response.body() != null){
                    catCallback.onSuccessCat(response.body());
                }
            }

            @Override
            public void onFailure(Call<CatM> call, Throwable t) {
                catCallback.onFailure(new NetworkErrorException());

            }
        });

    }

    public interface CatCallback{
        void onSuccess(ArrayList<CatM> body);

        void onFailure(Exception e);

        void onSuccessCat(CatM body);
    }

    public interface ApiCats{
        @GET("facts/random")
        Call<ArrayList<CatM>> getCats(@Query("animal_type") String animal_type, @Query("amount") String amount);


        @GET("facts/random")
        Call<CatM> getCatById();
    }

}
