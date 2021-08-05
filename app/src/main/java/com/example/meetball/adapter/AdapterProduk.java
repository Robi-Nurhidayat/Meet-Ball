package com.example.meetball.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetball.ListViewProduk;
import com.example.meetball.R;
import com.example.meetball.RequestTambahProduk;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.MyViewHolder> {

    private List<RequestTambahProduk> mList;

    public AdapterProduk(List<RequestTambahProduk> plist){
        this.mList = plist;
    }


    @NonNull
    @NotNull
    @Override
    public AdapterProduk.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_produk,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterProduk.MyViewHolder holder, int position) {
        RequestTambahProduk request = mList.get(position);
        holder.tv_nama.setText("Nama : " + request.getNamaProduk());
        holder.tv_status.setText("Status : " + request.getStatus());
        holder.tv_desc.setText("Desc : " + request.getDesc());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama;
        TextView tv_status;
        TextView tv_desc;

        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);

            tv_nama = itemView.findViewById(R.id.tv_namaProduk);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            cardView = itemView.findViewById(R.id.item_produk_layout);
        }
    }
}
