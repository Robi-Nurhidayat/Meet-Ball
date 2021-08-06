package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meetball.SqliteLogin.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameReg,passwordReg,passwordCon;
    Button btnSignUp;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameReg = findViewById(R.id.textUsernameReg);
        passwordReg = findViewById(R.id.textPwdReg);
        passwordCon = findViewById(R.id.textPwdCon);

        btnSignUp = findViewById(R.id.signUp);

        dbHelper = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameReg.getText().toString();
                String pass = passwordReg.getText().toString();
                String repass = passwordCon.getText().toString();

                if (user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(RegisterActivity.this,"masukkan input",Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(repass)){
                        Boolean chekuser = dbHelper.checkusername(user);
                        if (chekuser == false){
                            Boolean insert = dbHelper.insertData(user,pass);
                            if (insert == true){
                                Toast.makeText(RegisterActivity.this,"Registrasi Berhasil, Silahkan Login",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),RegistrasiBerhasil.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(RegisterActivity.this,"Gagal",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(RegisterActivity.this,"Anda sudah terdaftar",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this,"Password salah",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}