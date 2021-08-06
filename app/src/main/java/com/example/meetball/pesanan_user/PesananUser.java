package com.example.meetball.pesanan_user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.meetball.R;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meetball.RequestTambahProduk;
import com.example.meetball.TambahProduk;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PesananUser extends AppCompatActivity {

    private static final String TAG = "produkBakso";
    private DatabaseReference database;

    private EditText et_nama_pemesan, no_meja, pesanan_1, jumlah_pesanan1, pesanan_2, jumlah_pesanan2, keteranganPesanan, status_pesanan;
    private ProgressDialog dialog;

    Button btnSimpanPesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_user);

        database = FirebaseDatabase.getInstance().getReference();
        et_nama_pemesan = findViewById(R.id.et_nama_pemesan);
        no_meja = findViewById(R.id.no_meja);
        pesanan_1 = findViewById(R.id.pesanan_1);
        jumlah_pesanan1 = findViewById(R.id.jumlah_pesanan1);
        pesanan_2 = findViewById(R.id.pesanan_2);
        jumlah_pesanan2 = findViewById(R.id.jumlah_pesanan2);
        keteranganPesanan = findViewById(R.id.keteranganPesanan);
        status_pesanan = findViewById(R.id.status_pesanan);
        btnSimpanPesanan = findViewById(R.id.btnSimpanPesanan);

        btnSimpanPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namapemesan = et_nama_pemesan.getText().toString();
                String noMeja = no_meja.getText().toString();
                String pesanan1 = pesanan_1.getText().toString();
                String jumlahPesanan1 = jumlah_pesanan1.getText().toString();
                String pesanan2 = pesanan_2.getText().toString();
                String jumlahPesanan2 = jumlah_pesanan2.getText().toString();
                String ket = keteranganPesanan.getText().toString();
                String status = status_pesanan.getText().toString();


                if (namapemesan.equals("")) {
                    et_nama_pemesan.setError("silahkan masukkan nama produk");
                    et_nama_pemesan.requestFocus();
                } else if (noMeja.equals("")) {
                    no_meja.setError("silahkan masukkan status produk ");
                    no_meja.requestFocus();
                } else if (pesanan1.equals("")) {
                    pesanan_1.setError("silahkan masukkan deskripsi produk ");
                } else if (jumlahPesanan1.equals("")) {
                    jumlah_pesanan1.setError("silahkan masukkan deskripsi produk ");
                } else if (pesanan2.equals("")) {
                    pesanan_2.setError("silahkan masukkan deskripsi produk ");
                } else if (jumlahPesanan2.equals("")) {
                    jumlah_pesanan2.setError("silahkan masukkan deskripsi produk ");
                } else if (ket.equals("")) {
                    keteranganPesanan.setError("silahkan masukkan deskripsi produk ");
                } else if (status.equals("")) {
                    status_pesanan.setError("silahkan masukkan deskripsi produk ");
                } else {
                    dialog = ProgressDialog.show(PesananUser.this, null, "Please wait...", true, false);
                    SubmitUser(new RequestPesananUser(
                            namapemesan.toLowerCase(),
                            noMeja.toLowerCase(),
                            pesanan1.toLowerCase(),
                            jumlahPesanan1.toLowerCase(),
                            pesanan2.toLowerCase(),
                            jumlahPesanan2.toLowerCase(),
                            ket.toLowerCase(),
                            status.toLowerCase()
                    ));
                }
            }
        });

    }

    public void SubmitUser(RequestPesananUser requestPesananUser) {

        database.child("pesanan").push()
                .setValue(requestPesananUser)
                .addOnSuccessListener(this, (OnSuccessListener) (avoid) -> {
                    dialog.dismiss();
                    et_nama_pemesan.setText("");
                    no_meja.setText("");
                    pesanan_1.setText("");
                    jumlah_pesanan1.setText("");
                    pesanan_2.setText("");
                    jumlah_pesanan2.setText("");
                    keteranganPesanan.setText("");
                    status_pesanan.setText("");

                    Toast.makeText(PesananUser.this, "Berhasil Melakukan Pemesanan", Toast.LENGTH_SHORT).show();
                });
    }
}
