package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuHalamanProduk extends AppCompatActivity {

    private CardView viewProduk,tambahProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_halaman_produk);

        viewProduk = findViewById(R.id.card_view_lihat_produk);
        tambahProduk = findViewById(R.id.card_view_tambah_produk);
        viewProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuHalamanProduk.this, ViewProduk.class);
                startActivity(intent);
            }
        });

        tambahProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuHalamanProduk.this, TambahProduk.class);
                startActivity(intent);
            }
        });
    }
}