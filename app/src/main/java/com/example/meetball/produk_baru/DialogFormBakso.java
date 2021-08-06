package com.example.meetball.produk_baru;


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
import androidx.annotation.StringRes;
import androidx.fragment.app.DialogFragment;

import com.example.meetball.R;
import com.example.meetball.minuman.Minuman;
import com.example.meetball.pemasukkan.Pemasukkan;
import com.example.meetball.pesanan.PesananAdmin;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.DialogFragment;

public class DialogFormBakso extends DialogFragment {

    String namaBakso;
    String harga;
    String desc, key, pilih;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogFormBakso(String namaBakso, String harga, String desc, String key, String pilih) {
        this.namaBakso = namaBakso;
        this.harga = harga;
        this.desc = desc;
        this.key = key;
        this.pilih = pilih;
    }


    EditText et_nama_bakso, et_harga_bakso, et_bakso_desc;
    Button btnSimpanBakso;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.input_produk_baru_admin, container, false);
        et_nama_bakso = view.findViewById(R.id.et_nama_bakso);
        et_harga_bakso = view.findViewById(R.id.et_harga_bakso);
        et_bakso_desc = view.findViewById(R.id.et_bakso_desc);

        btnSimpanBakso = view.findViewById(R.id.btnSimpanBakso);

        et_nama_bakso.setText(namaBakso);
        et_harga_bakso.setText(harga);
        et_bakso_desc.setText(desc);


        btnSimpanBakso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaBakso = et_nama_bakso.getText().toString();
                String harga = et_harga_bakso.getText().toString();
                String desc = et_bakso_desc.getText().toString();


                if (TextUtils.isEmpty(namaBakso)) {
                    input((EditText) et_nama_bakso, "Nama");
                } else if (TextUtils.isEmpty(harga)) {
                    input((EditText) et_harga_bakso, "Harga");
                } else if (TextUtils.isEmpty(desc)) {
                    input((EditText) et_bakso_desc, "Deskripsi");
                } else {

                    if (pilih.equals("Tambah")) {
                        database.child("bakso").push().setValue(new Bakso(namaBakso, harga, desc)).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                    } else if (pilih.equals("Ubah")) {
                        database.child("bakso").child(key).setValue(new Bakso(namaBakso, harga, desc)).addOnSuccessListener(new OnSuccessListener<Void>() {
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
