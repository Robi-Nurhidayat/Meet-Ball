package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class MenuHalamanProduk extends AppCompatActivity {

    private CardView viewProduk,tambahProduk;
    private Button dashboardAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_halaman_produk);

        viewProduk = findViewById(R.id.card_view_lihat_produk);
        tambahProduk = findViewById(R.id.card_view_tambah_produk);
        dashboardAdmin = findViewById(R.id.dashboardAdmin);


        tambahProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuHalamanProduk.this, TambahProduk.class);
                startActivity(intent);
            }
        });

        viewProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuHalamanProduk.this, ListViewProduk.class);
                startActivity(intent);
            }
        });

        // tombol back
        dashboardAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboardAdmin = new Intent(MenuHalamanProduk.this, HalamanUtamaAdmin.class);
                startActivity(dashboardAdmin);
            }
        });
    }
}