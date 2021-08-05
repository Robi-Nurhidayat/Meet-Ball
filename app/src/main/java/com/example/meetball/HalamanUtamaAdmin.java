package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class HalamanUtamaAdmin extends AppCompatActivity {

    CardView card_produk;
    Button logoutAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama_admin);

        logoutAdmin = findViewById(R.id.logoutAdmin);

        logoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(HalamanUtamaAdmin.this)
                        .setTitle("Logout Admin")
                        .setMessage("Apakah Anda Yakin Ingin Keluar?")
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent logoutAdmin = new Intent(HalamanUtamaAdmin.this, AdminLogin.class);
                                startActivity(logoutAdmin);
                            }
                        }).show();
            }
        });

        card_produk = findViewById(R.id.card_produk);
        card_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HalamanUtamaAdmin.this,MenuHalamanProduk.class);
                startActivity(intent);
            }
        });
    }


}