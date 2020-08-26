package com.example.viewpagerapp.fragmets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerapp.App;
import com.example.viewpagerapp.R;
import com.example.viewpagerapp.adapter.NewsAdapter;
import com.example.viewpagerapp.data.ApiNews;
import com.example.viewpagerapp.model.Article;

import java.util.ArrayList;

public class NewsFragment extends Fragment implements ApiNews.NewsCallback {

    private NewsAdapter adapterNews;
    private ArrayList<Article> newsMList;
    //private NewsM newsM;
    RecyclerView recyclerViewFilm;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.apiNews.getListNews(this);
        recyclerViewFilm = view.findViewById(R.id.recyclerViewNews);

    }

    @Override
    public void onSuccess(ArrayList<Article> newss) {
        Log.e("tag","onSuccess_newsFragment");
        newsMList = newss;
        adapterNews = new NewsAdapter(newsMList);
        recyclerViewFilm.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        recyclerViewFilm.setAdapter(adapterNews);

    }

    @Override
    public void onFailure(Exception e) {

    }
}
