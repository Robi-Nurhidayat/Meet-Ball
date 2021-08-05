package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private ImageView back_button;
    private TextInputEditText textUsername, textemail, textpassword;
    private Button signUpbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Deklarasi
        back_button = findViewById(R.id.back);
        textUsername = findViewById(R.id.textUsername);
        textemail = findViewById(R.id.textEmail);
        textpassword = findViewById(R.id.textPwd);
        signUpbtn = findViewById(R.id.signUpBtn);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(kembali);
            }
        });
    }
}