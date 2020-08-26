package com.example.viewpagerapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerapp.R;
import com.example.viewpagerapp.interfac.onItemClickListener;
import com.example.viewpagerapp.model.Article;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    public void setOnItemFBNClickListener(onItemClickListener onItemClickListener) {
        this.onItemFBNClickListener = onItemClickListener;
    }

    private onItemClickListener onItemFBNClickListener;


    public NewsAdapter(ArrayList<Article> newsMList) {
        this.newsMList = newsMList;
    }

    private ArrayList<Article> newsMList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(newsMList.get(position));

    }

    @Override
    public int getItemCount() {
        return newsMList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView newsHolder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsHolder = itemView.findViewById(R.id.tv_news_holder);
        }

        public void bind(Article newsM) {
            newsHolder.setText(newsM.getTitle());

        }
    }
}
