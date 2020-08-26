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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerapp.App;
import com.example.viewpagerapp.R;
import com.example.viewpagerapp.data.GhibliService;
import com.example.viewpagerapp.interfac.onItemClickListener;
import com.example.viewpagerapp.model.Film;
import com.example.viewpagerapp.adapter.FilmAdapter;
import com.example.viewpagerapp.info.FilmInfo;

import java.util.List;

public class FilmFragment extends Fragment implements GhibliService.GhibliCallback {

    private RecyclerView recyclerView;
    private FilmAdapter adapter;

    public FilmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.ghibliService.getListFilms(this);
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void createRecyclerView(List<Film> films) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FilmAdapter(films);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("tag", "onItemClick");
                Bundle bundle = new Bundle();
                bundle.putString("id", films.get(position).getId());
                FilmInfo fragment = new FilmInfo();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.fr_container, fragment).addToBackStack(null).commit();


            }
        });
    }

    @Override
    public void onSuccess(List<Film> films) {
        Log.d("tag", "id");
        createRecyclerView(films);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onResponseFilm(Film film) {
        Log.e("TAG", "onResponseFilm: "+film.getImageName );
    }

    @Override
    public void onFailure(Exception e) {
        Log.e("TAG", "onFailure: "+e.getMessage() );
    }


}
