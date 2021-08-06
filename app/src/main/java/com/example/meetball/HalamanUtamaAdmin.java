package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.meetball.pesanan.PesananAdminActivity;

public class HalamanUtamaAdmin extends AppCompatActivity {

    CardView card_produk,card_pesanan_admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama_admin);

        card_pesanan_admin = findViewById(R.id.card_view_pesanan);
        card_produk = findViewById(R.id.card_produk);
        card_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanUtamaAdmin.this,MenuHalamanProduk.class);
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
    }
}