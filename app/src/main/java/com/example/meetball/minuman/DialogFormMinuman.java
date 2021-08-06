package com.example.meetball.minuman;

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
import com.example.meetball.pemasukkan.Pemasukkan;
import com.example.meetball.pesanan.PesananAdmin;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.DialogFragment;

public class DialogFormMinuman extends DialogFragment {

    String namaMinuman;
    String harga;
    String desc, key, pilih;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogFormMinuman(String namaMinuman, String harga, String desc, String key, String pilih) {
        this.namaMinuman = namaMinuman;
        this.harga = harga;
        this.desc = desc;
        this.key = key;
        this.pilih = pilih;
    }

    EditText et_nama_minuman, et_harga, et_minumanDesc;
    Button btnSimpanMinuman;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.input_minuman_admin, container, false);
        et_nama_minuman = view.findViewById(R.id.et_minuman);
        et_harga = view.findViewById(R.id.et_harga_minuman);
        et_minumanDesc = view.findViewById(R.id.et_minuman_desc);

        btnSimpanMinuman = view.findViewById(R.id.btnSimpanMinuman);

        et_nama_minuman.setText(namaMinuman);
        et_harga.setText(harga);
        et_minumanDesc.setText(desc);


        btnSimpanMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaMinuman = et_nama_minuman.getText().toString();
                String harga = et_harga.getText().toString();
                String desc = et_minumanDesc.getText().toString();


                if (TextUtils.isEmpty(namaMinuman)) {
                    input((EditText) et_nama_minuman, "Nama");
                } else if (TextUtils.isEmpty(harga)) {
                    input((EditText) et_harga, "Harga");
                } else if (TextUtils.isEmpty(desc)) {
                    input((EditText) et_minumanDesc, "Deskripsi");
                } else {

                    if (pilih.equals("Tambah")) {
                        database.child("minuman").push().setValue(new Minuman(namaMinuman, harga, desc)).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                        database.child("minuman").child(key).setValue(new Minuman(namaMinuman, harga, desc)).addOnSuccessListener(new OnSuccessListener<Void>() {
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