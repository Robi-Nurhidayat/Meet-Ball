package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meetball.SqliteLogin.DBHelper;

public class LoginActivity extends AppCompatActivity {

    Button btnSignUp,loginUser;
    EditText username,password;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.textUsername);
        password = findViewById(R.id.textPwd);
        loginUser = findViewById(R.id.btnLoginUser);
        btnSignUp = findViewById(R.id.btnSignUp);
        dbHelper = new DBHelper(this);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("")||pass.equals("")){
                    Toast.makeText(LoginActivity.this,"masukkan input",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkUserPassword = dbHelper.checkUserNamePassword(user,pass);
                    if (checkUserPassword == true){
                        Toast.makeText(LoginActivity.this,"Login berhasil",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeUser.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"Invalid",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}