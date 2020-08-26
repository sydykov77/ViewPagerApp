package com.example.viewpagerapp.fragmets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerapp.App;
import com.example.viewpagerapp.R;
import com.example.viewpagerapp.adapter.FilmAdapter;
import com.example.viewpagerapp.data.CatApi;
import com.example.viewpagerapp.info.FilmInfo;
import com.example.viewpagerapp.interfac.onItemClickListener;
import com.example.viewpagerapp.model.CatM;
import com.example.viewpagerapp.adapter.CatAdapter;
import com.example.viewpagerapp.info.CatInfo;

import java.util.ArrayList;

public class CatFragment extends Fragment implements CatApi.CatCallback {

    private RecyclerView recyclerView;
    private CatAdapter adapter;

    public CatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.apiCat.getListCats(this);
        recyclerView = view.findViewById(R.id.recyclerViewCat);
    }

    @Override
    public void onSuccess(ArrayList<CatM> body) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CatAdapter(body);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemFBNClickListener(new onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("tag", "onItemClick");
                Bundle bundle = new Bundle();
                bundle.putString("id", body.get(position).getId());
                CatInfo fragment = new CatInfo();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.fr_container_cat, fragment).addToBackStack(null).commit();


            }
        });
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onSuccessCat(CatM body) {

    }

}
