package com.example.viewpagerapp.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerapp.R;
import com.example.viewpagerapp.interfac.onItemClickListener;
import com.example.viewpagerapp.model.CatM;

import java.util.ArrayList;


public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {

    public void setOnItemFBNClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    private onItemClickListener onItemClickListener;

    public CatAdapter(ArrayList<CatM> list) {
        this.list = list;
    }

    private ArrayList<CatM> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_holder,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        if(position % 2 == 0){
            holder.itemView.setBackgroundColor(0x80E0EEEE);
        }else{
            holder.itemView.setBackgroundColor(R.color.orange);
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_book;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            tv_book = itemView.findViewById(R.id.tv_book_holder);
        }
        public void bind(CatM bookM) {
            tv_book.setText(bookM.getType());

        }
    }
}
