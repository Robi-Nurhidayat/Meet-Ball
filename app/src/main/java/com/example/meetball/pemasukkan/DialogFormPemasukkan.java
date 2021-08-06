package com.example.meetball.pemasukkan;

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
import com.example.meetball.pesanan.PesananAdmin;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class DialogFormPemasukkan extends DialogFragment {

    String hari;
    String tanggal;
    String bulan;
    String tahun;
    String jumlah_pemasukkan,key,pilih;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogFormPemasukkan(String hari, String tanggal, String bulan, String tahun, String jumlah_pemasukkan, String key, String pilih) {
        this.hari = hari;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jumlah_pemasukkan = jumlah_pemasukkan;
        this.key = key;
        this.pilih = pilih;
    }

    EditText et_pemasukkan_hari,et_tanggal,et_bulan,et_tahun,et_jumlah_pemasukkan;
    Button btnSimpanPemasukkan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.input_pemasukkan_admin, container, false);
        et_pemasukkan_hari = view.findViewById(R.id.et_pemasukkan_hari);
        et_tanggal = view.findViewById(R.id.et_tanggal);
        et_bulan = view.findViewById(R.id.et_bulan);
        et_tahun = view.findViewById(R.id.et_tahun);
        et_jumlah_pemasukkan = view.findViewById(R.id.et_jumlah_pemasukkan);
        btnSimpanPemasukkan = view.findViewById(R.id.btnSimpanPemasukkan);

        et_pemasukkan_hari.setText(hari);
        et_tanggal.setText(tanggal);
        et_bulan.setText(bulan);
        et_tahun.setText(tahun);
        et_jumlah_pemasukkan.setText(jumlah_pemasukkan);


        btnSimpanPemasukkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hari = et_pemasukkan_hari.getText().toString();
                String tanggal = et_tanggal.getText().toString();
                String bulan = et_bulan.getText().toString();
                String tahun = et_tahun.getText().toString();
                String jumlah_pemasukkan = et_jumlah_pemasukkan.getText().toString();


                if (TextUtils.isEmpty(hari)) {
                    input((EditText) et_pemasukkan_hari, "hari");
                } else if (TextUtils.isEmpty(tanggal)) {
                    input((EditText) et_tanggal, "Tanggal");
                } else if (TextUtils.isEmpty(bulan)) {
                    input((EditText) et_bulan, "Bulan");
                } else if (TextUtils.isEmpty(tahun)) {
                    input((EditText) et_tahun, "Tahun");
                } else if (TextUtils.isEmpty(jumlah_pemasukkan)) {
                    input((EditText) et_jumlah_pemasukkan, "Pemasukkan");
                } else {

                    if (pilih.equals("Tambah")){
                        database.child("pemasukkan").push().setValue(new Pemasukkan(hari, tanggal, bulan, tahun, jumlah_pemasukkan)).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                        database.child("pemasukkan").child(key).setValue(new Pemasukkan(hari, tanggal, bulan, tahun, jumlah_pemasukkan)).addOnSuccessListener(new OnSuccessListener<Void>() {
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
