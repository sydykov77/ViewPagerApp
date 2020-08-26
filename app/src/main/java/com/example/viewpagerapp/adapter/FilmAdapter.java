package com.example.viewpagerapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerapp.R;
import com.example.viewpagerapp.model.Film;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    public void setOnItemClickListener(com.example.viewpagerapp.interfac.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private com.example.viewpagerapp.interfac.onItemClickListener onItemClickListener;

    private List<Film> filmList;

    public FilmAdapter(List<Film> list) {
        this.filmList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_film_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(filmList.get(position));
        if(position % 2 == 0){
            holder.itemView.setBackgroundColor(0x80E0EEEE);
        }else{
            holder.itemView.setBackgroundColor(Color.GRAY);
        }

    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textFilm;
        private ImageView imageView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            textFilm = itemView.findViewById(R.id.text_film_to_holder);
            imageView = itemView.findViewById(R.id.image_to_holder);
        }

        private void bind(Film film) {
            textFilm.setText(film.getTitle());
            imageView.setBackgroundResource(film.getImageName);

        }
    }
}
