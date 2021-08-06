package com.example.meetball.pesanan;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.meetball.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class DialogFormPesanan extends DialogFragment {
     String nama;
     String noMeja;
     String pesanan1;
     String jumlahpesanan1;
     String pesanan2;
     String jumlahPesanan2;
     String keterangan;
     String status,key,pilih;

     DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogFormPesanan(String nama, String noMeja, String pesanan1, String jumlahpesanan1, String pesanan2, String jumlahPesanan2, String keterangan, String status, String key, String pilih) {
        this.nama = nama;
        this.noMeja = noMeja;
        this.pesanan1 = pesanan1;
        this.jumlahpesanan1 = jumlahpesanan1;
        this.pesanan2 = pesanan2;
        this.jumlahPesanan2 = jumlahPesanan2;
        this.keterangan = keterangan;
        this.status = status;
        this.key = key;
        this.pilih = pilih;
    }

    //    public DialogFormPesanan() {
//        this.nama = nama;
//        this.noMeja = noMeja;
//        this.pesanan1 = pesanan1;
//        this.jumlahpesanan1 = jumlahpesanan1;
//        this.pesanan2 = pesanan2;
//        this.jumlahPesanan2 = jumlahPesanan2;
//        this.keterangan = keterangan;
//        this.status = status;
//    }

    EditText et_nama_pemesan,no_meja,pesanan_1,jumlah_pesanan1,pesanan_2,jumlah_pesanan2,keteranganPesanan,status_pesanan;
    Button btnSimpanPesanan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.input_pesanan_admin, container, false);
        et_nama_pemesan = view.findViewById(R.id.et_nama_pemesan);
        no_meja = view.findViewById(R.id.no_meja);
        pesanan_1 = view.findViewById(R.id.pesanan_1);
        jumlah_pesanan1 = view.findViewById(R.id.jumlah_pesanan1);
        pesanan_2 = view.findViewById(R.id.pesanan_2);
        jumlah_pesanan2 = view.findViewById(R.id.jumlah_pesanan2);
        keteranganPesanan = view.findViewById(R.id.keteranganPesanan);
        status_pesanan = view.findViewById(R.id.status_pesanan);
        btnSimpanPesanan = view.findViewById(R.id.btnSimpanPesanan);

        et_nama_pemesan.setText(nama);
        no_meja.setText(noMeja);
        pesanan_1.setText(pesanan1);
        jumlah_pesanan1.setText(jumlahpesanan1);
        pesanan_2.setText(pesanan2);
        jumlah_pesanan2.setText(jumlahPesanan2);
        keteranganPesanan.setText(keterangan);
        status_pesanan.setText(status);

        btnSimpanPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = et_nama_pemesan.getText().toString();
                String noMeja = no_meja.getText().toString();
                String pesanan1 = pesanan_1.getText().toString();
                String jumlahPesanan1 = jumlah_pesanan1.getText().toString();
                String pesanan2 = pesanan_2.getText().toString();
                String jumlahPesanan2 = jumlah_pesanan2.getText().toString();
                String keterangan = keteranganPesanan.getText().toString();
                String status = status_pesanan.getText().toString();

                if (TextUtils.isEmpty(nama)) {
                    input((EditText) et_nama_pemesan, "nama");
                } else if (TextUtils.isEmpty(noMeja)) {
                    input((EditText) no_meja, "No meja");
                } else if (TextUtils.isEmpty(pesanan1)) {
                    input((EditText) pesanan_1, "Pesanan pertama");
                } else if (TextUtils.isEmpty(jumlahPesanan1)) {
                    input((EditText) jumlah_pesanan1, "jumlah pesanan pertama");
                } else if (TextUtils.isEmpty(pesanan2)) {
                    input((EditText) pesanan_2, "pesananan kedua");
                } else if (TextUtils.isEmpty(jumlahPesanan2)) {
                    input((EditText) jumlah_pesanan2, "jumlah pesanan kedua");
                } else if (TextUtils.isEmpty(keterangan)) {
                    input((EditText) keteranganPesanan, "keterangan");
                } else if (TextUtils.isEmpty(status)) {
                    input((EditText) status_pesanan, "status pesanan");
                } else {

                    if (pilih.equals("Tambah")){
                        database.child("pesanan").push().setValue(new PesananAdmin(nama, noMeja, pesanan1, jumlahPesanan1, pesanan2, jumlahPesanan2, keterangan, status)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(view.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(view.getContext(), "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if(pilih.equals("Ubah")) {
                        database.child("pesanan").child(key).setValue(new PesananAdmin(nama, noMeja, pesanan1, jumlahPesanan1, pesanan2, jumlahPesanan2, keterangan, status)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(view.getContext(), "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(view.getContext(), "Data gagal diubah", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }


                }
            }
        });

        return view;
    }

    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }


    private void input(EditText text, String s){
        text.setError(s + "tidak boleh kosong");
        text.requestFocus();
    }

}
