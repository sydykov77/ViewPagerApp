package com.example.viewpagerapp;

import android.app.Application;

import com.example.viewpagerapp.data.ApiNews;
import com.example.viewpagerapp.data.CatApi;
import com.example.viewpagerapp.data.GhibliService;

public class App extends Application {
    public static GhibliService ghibliService;
    public static CatApi apiCat;
    public static ApiNews apiNews;

    @Override
    public void onCreate() {
        super.onCreate();
        ghibliService = new GhibliService();
        apiCat = new CatApi();
        apiNews = new ApiNews();
    }
}
