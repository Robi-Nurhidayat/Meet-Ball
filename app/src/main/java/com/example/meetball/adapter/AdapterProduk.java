package com.example.meetball.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetball.R;
import com.example.meetball.RequestTambahProduk;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.MyViewHolder> {

    private List<RequestTambahProduk> moviesList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView tv_title, tv_email;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.rl_layout);
            tv_nama = view.findViewById(R.id.tv_nn);
            tv_email = view.findViewById(R.id.tv_email);
        }
    }

    public RequestAdapterRecyclerView(List<RequestTambahProduk> moviesList, Activity activity) {
        this.moviesList = moviesList;
        this.mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produk, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Requests movie = moviesList.get(position);

        holder.tv_title.setText(movie.getNama());
        holder.tv_email.setText(movie.getEmail());

        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goDetail = new Intent(mActivity, MainActivity.class);
                goDetail.putExtra("id", movie.getKey());
                goDetail.putExtra("title", movie.getNama());
                goDetail.putExtra("email", movie.getEmail());
                goDetail.putExtra("desk", movie.getDesk());

                mActivity.startActivity(goDetail);


            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
