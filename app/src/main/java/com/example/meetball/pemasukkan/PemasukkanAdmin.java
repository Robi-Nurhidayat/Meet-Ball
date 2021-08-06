package com.example.meetball.pemasukkan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.meetball.R;
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

public class PemasukkanAdmin extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    AdapterPemasukkan adapterPemasukkan;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<Pemasukkan> listPemasukkan;
    RecyclerView rv_pemasukkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukkan_admin);


        rv_pemasukkan = findViewById(R.id.rv_pemasukkan);
        RecyclerView.LayoutManager pemasukkanLayout = new LinearLayoutManager(this);
        rv_pemasukkan.setLayoutManager(pemasukkanLayout);
        rv_pemasukkan.setItemAnimator(new DefaultItemAnimator());

        floatingActionButton = findViewById(R.id.fb_add_pemasukkan);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFormPemasukkan dialogFormPemasukkan = new DialogFormPemasukkan("","","","","","","Tambah");
                dialogFormPemasukkan.show(getSupportFragmentManager(),"form");

            }
        });

        showData();
    }

    private void showData(){
        database.child("pemasukkan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listPemasukkan = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    Pemasukkan pemasukkan = item.getValue(Pemasukkan.class);
                    pemasukkan.setKey(item.getKey());
                    listPemasukkan.add(pemasukkan);
                }

                adapterPemasukkan = new AdapterPemasukkan(listPemasukkan, PemasukkanAdmin.this);
                rv_pemasukkan.setAdapter(adapterPemasukkan);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}