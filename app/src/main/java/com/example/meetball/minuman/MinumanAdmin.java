package com.example.meetball.minuman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.meetball.R;
import com.example.meetball.pemasukkan.AdapterPemasukkan;
import com.example.meetball.pemasukkan.DialogFormPemasukkan;
import com.example.meetball.pemasukkan.Pemasukkan;
import com.example.meetball.pemasukkan.PemasukkanAdmin;
import com.example.meetball.pesanan.AdapterPesanan;
import com.example.meetball.pesanan.DialogFormPesanan;
import com.example.meetball.pesanan.PesananAdmin;
import com.example.meetball.pesanan.PesananAdminActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.meetball.R;

public class MinumanAdmin extends AppCompatActivity {


    FloatingActionButton floatingActionButton;
    AdapterMinuman adapterMinuman;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<Minuman> listMinuman;
    RecyclerView rv_minuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minuman_admin);

        rv_minuman = findViewById(R.id.rv_minuman);
        RecyclerView.LayoutManager minumanLayout = new LinearLayoutManager(this);
        rv_minuman.setLayoutManager(minumanLayout);
        rv_minuman.setItemAnimator(new DefaultItemAnimator());

        floatingActionButton = findViewById(R.id.fb_add_minuman);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFormMinuman dialogFormMinuman = new DialogFormMinuman("","","","","Tambah");
                dialogFormMinuman.show(getSupportFragmentManager(),"form");

            }
        });

        showData();
    }

    private void showData(){
        database.child("minuman").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listMinuman = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    Minuman minuman = item.getValue(Minuman.class);
                    minuman.setKey(item.getKey());
                    listMinuman.add(minuman);
                }

                adapterMinuman = new AdapterMinuman(listMinuman, MinumanAdmin.this);
                rv_minuman.setAdapter(adapterMinuman);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}