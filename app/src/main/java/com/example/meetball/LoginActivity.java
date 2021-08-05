package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private ImageView back_button;
    private TextInputEditText textemail, textpassword;
    private Button signUpbtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Deklarasi
        back_button = findViewById(R.id.back);
        textemail = findViewById(R.id.textEmail);
        textpassword = findViewById(R.id.textPwd);
        signUpbtn = findViewById(R.id.signUpBtn);
        loginBtn = findViewById(R.id.btnLogin);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(kembali);
            }
        });

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(daftar);
            }
        });

    }
}