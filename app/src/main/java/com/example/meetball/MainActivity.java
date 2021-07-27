package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPelanggann = findViewById(R.id.btnPelanggan);
        Button btnAdmin = findViewById(R.id.btnAdmin);

        btnPelanggann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pelanggan = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(pelanggan);
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admin = new Intent(MainActivity.this,AdminLogin.class);
                startActivity(admin);
            }
        });

    }
}