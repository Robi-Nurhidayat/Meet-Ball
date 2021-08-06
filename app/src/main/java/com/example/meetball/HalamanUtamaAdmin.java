package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.meetball.minuman.MinumanAdmin;
import com.example.meetball.pemasukkan.PemasukkanAdmin;
import com.example.meetball.pesanan.PesananAdminActivity;
import com.example.meetball.produk_baru.ProdukBaru;

public class HalamanUtamaAdmin extends AppCompatActivity {

    CardView card_produk,card_pesanan_admin,card_pemasukkan,card_minuman;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama_admin);

        card_minuman = findViewById(R.id.card_view_minuman);
        card_pesanan_admin = findViewById(R.id.card_view_pesanan);
        card_produk = findViewById(R.id.card_produk);
        card_pemasukkan = findViewById(R.id.card_view_pemasukkan);
        card_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanUtamaAdmin.this, ProdukBaru.class);
                startActivity(intent);
            }
        });

        card_pesanan_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanUtamaAdmin.this, PesananAdminActivity.class);
                startActivity(intent);
            }
        });

        card_pemasukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanUtamaAdmin.this, PemasukkanAdmin.class);
                startActivity(intent);
            }
        });


        card_minuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanUtamaAdmin.this, MinumanAdmin.class);
                startActivity(intent);
            }
        });

    }
}