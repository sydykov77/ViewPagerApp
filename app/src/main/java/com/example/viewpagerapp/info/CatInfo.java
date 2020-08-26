package com.example.viewpagerapp.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.viewpagerapp.App;
import com.example.viewpagerapp.R;
import com.example.viewpagerapp.data.CatApi;
import com.example.viewpagerapp.model.CatM;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatInfo extends Fragment implements CatApi.CatCallback {
    private ImageView imageViewCat;
    private TextView typeCat,descCat;
    private EditText editText;

    public CatInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        App.apiCat.getCatText(this);
        return inflater.inflate(R.layout.fragment_info_cat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewCat = view.findViewById(R.id.image_cat);
        typeCat = view.findViewById(R.id.type_cat);
        descCat = view.findViewById(R.id.tv_book_holder);
        editText = view.findViewById(R.id.edit_text);


    }

    @Override
    public void onSuccess(ArrayList<CatM> body) {

    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onSuccessCat(CatM body) {
        typeCat.setText(body.getType());
        descCat.setText(body.getText());

    }
}
