package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeUser extends AppCompatActivity {
    Button logoutUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        logoutUser = findViewById(R.id.logoutUser);

        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outUser = new Intent(HomeUser.this, LoginActivity.class);
                startActivity(outUser);
            }
        });
    }
}