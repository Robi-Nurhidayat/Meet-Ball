package com.example.meetball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.meetball.adapter.AdapterProduk;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListViewProduk extends AppCompatActivity {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private ArrayList<RequestTambahProduk> daftarProduk;
    private AdapterProduk adapterProduk;


    private RecyclerView listProduk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_produk);

        listProduk = findViewById(R.id.list_produk);

        RecyclerView.LayoutManager pLayout = new LinearLayoutManager(this);
        listProduk.setLayoutManager(pLayout);
        listProduk.setItemAnimator(new DefaultItemAnimator());
        showData();
    }

    private void showData(){
        database.child("RequestTambahProduk").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                daftarProduk = new ArrayList<>();

                for (DataSnapshot item : snapshot.getChildren()){
                    RequestTambahProduk requestTambahProduk = item.getValue(RequestTambahProduk.class);
                    requestTambahProduk.setKey(item.getKey());
                    daftarProduk.add(requestTambahProduk);
                }

                adapterProduk = new AdapterProduk(daftarProduk);
                listProduk.setAdapter(adapterProduk);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}