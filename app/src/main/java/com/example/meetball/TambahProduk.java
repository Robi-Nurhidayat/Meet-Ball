package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahProduk extends AppCompatActivity {

    private static final String TAG = "produkBakso";
    private DatabaseReference database;

    private EditText et_nama,et_status,et_desc;
    private ProgressDialog dialog;

    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);

        database = FirebaseDatabase.getInstance().getReference();
        et_nama = findViewById(R.id.et_nama);
        et_status = findViewById(R.id.et_status);
        et_desc = findViewById(R.id.et_desc);
        btnSimpan = findViewById(R.id.btnSimpanProduk);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaProduk = et_nama.getText().toString();
                String status = et_status.getText().toString();
                String desc = et_desc.getText().toString();

                if (namaProduk.equals("")){
                    et_nama.setError("silahkan masukkan nama produk");
                    et_nama.requestFocus();
                }else if (status.equals("")){
                    et_status.setError("silahkan masukkan status produk ");
                    et_status.requestFocus();
                }else if(desc.equals("")){
                    et_desc.setError("silahkan masukkan deskripsi produk ");
                }else {
                    dialog = ProgressDialog.show(TambahProduk.this,null,"Please wait...",true,false);
                    SubmitUser(new RequestTambahProduk(
                            namaProduk.toLowerCase(),
                            status.toLowerCase(),
                            desc.toLowerCase()
                    ));
                }
            }
        });
    }

    public void SubmitUser(RequestTambahProduk requestTambahProduk){

        database.child("RequestTambahProduk").push()
                                            .setValue(requestTambahProduk)
                                            .addOnSuccessListener(this, (OnSuccessListener) (avoid) -> {
                                               dialog.dismiss();
                                               et_nama.setText("");
                                               et_status.setText("");
                                               et_desc.setText("");

                                                Toast.makeText(TambahProduk.this,"data berhasil ditambahkan",Toast.LENGTH_SHORT).show();
                                            });
    }
}