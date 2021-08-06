package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.meetball.minuman.MinumanAdmin;
import com.example.meetball.pesanan_user.PesananUser;
import com.example.meetball.produk_baru.ProdukBaru;

public class HomeUser extends AppCompatActivity {
    Button logoutUser;
    CardView card_view_pesan_makanan_user;
    CardView card_view_daftar_produk;
    CardView card_view_daftar_minuman;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        logoutUser = findViewById(R.id.logoutUser);
        card_view_pesan_makanan_user = findViewById(R.id.card_view_pesan_makanan_user);
        card_view_daftar_produk = findViewById(R.id.card_view_daftar_produk);
        card_view_daftar_minuman = findViewById(R.id.card_view_daftar_minuman);

        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outUser = new Intent(HomeUser.this, LoginActivity.class);
                startActivity(outUser);
            }
        });

        card_view_pesan_makanan_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outUser = new Intent(HomeUser.this, PesananUser.class);
                startActivity(outUser);
            }
        });

        card_view_daftar_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outUser = new Intent(HomeUser.this, ProdukBaru.class);
                startActivity(outUser);
            }
        });

        card_view_daftar_minuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outUser = new Intent(HomeUser.this, MinumanAdmin.class);
                startActivity(outUser);
            }
        });
    }
}