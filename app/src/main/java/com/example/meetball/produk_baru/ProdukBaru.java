package com.example.meetball.produk_baru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.meetball.R;
import com.example.meetball.minuman.AdapterMinuman;
import com.example.meetball.minuman.DialogFormMinuman;
import com.example.meetball.minuman.Minuman;
import com.example.meetball.minuman.MinumanAdmin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProdukBaru extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    AdapterBakso adapterBakso;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<Bakso> listBakso;
    RecyclerView rv_produk_baru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_baru);

        rv_produk_baru = findViewById(R.id.rv_produk_baru);
        RecyclerView.LayoutManager minumanLayout = new LinearLayoutManager(this);
        rv_produk_baru.setLayoutManager(minumanLayout);
        rv_produk_baru.setItemAnimator(new DefaultItemAnimator());

        floatingActionButton = findViewById(R.id.fb_add_produk_baru);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFormBakso dialogFormBakso = new DialogFormBakso("","","","","Tambah");
                dialogFormBakso.show(getSupportFragmentManager(),"form");

            }
        });

        showData();
    }

    private void showData(){
        database.child("bakso").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listBakso = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    Bakso bakso = item.getValue(Bakso.class);
                    bakso.setKey(item.getKey());
                    listBakso.add(bakso);
                }

                adapterBakso = new AdapterBakso(listBakso, ProdukBaru.this);
                rv_produk_baru.setAdapter(adapterBakso);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}