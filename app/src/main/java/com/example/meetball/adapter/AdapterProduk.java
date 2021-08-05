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
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetball.ListViewProduk;
import com.example.meetball.R;
import com.example.meetball.RequestTambahProduk;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.MyViewHolder> {

    private List<RequestTambahProduk> mList;


    @NonNull
    @NotNull
    @Override
    public AdapterProduk.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterProduk.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
    }
}
