package com.example.meetball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.meetball.adapter.AdapterProduk;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListViewProduk extends AppCompatActivity {

    private DatabaseReference database;
    private ArrayList<RequestTambahProduk> daftarProduk;
    private AdapterProduk adapterProduk;


    private RecyclerView rc_list_request;
    private ProgressDialog loading;
    private FloatingActionButton fab_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_produk);

        database = FirebaseDatabase.getInstance().getReference();

        rc_list_request = findViewById(R.id.list_produk);
//        fab_add = findViewById(R.id.fab_add);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rc_list_request.setLayoutManager(mLayoutManager);
        rc_list_request.setItemAnimator(new DefaultItemAnimator());

        loading = ProgressDialog.show(ListViewProduk.this,
                null,
                "Please wait...",
                true,
                false);


        database.child("RequestTambahProduk").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarProduk = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object Wisata
                     * Dan juga menyimpan primary key pada object Wisata
                     * untuk keperluan Edit dan Delete data
                     */
                    RequestTambahProduk requests = noteDataSnapshot.getValue(RequestTambahProduk.class);
                    requests.setKey(noteDataSnapshot.getKey());

                    /**
                     * Menambahkan object Wisata yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarProduk.add(requests);
                }

                /**
                 * Inisialisasi adapter dan data hotel dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapterProduk = new AdapterProduk(daftarProduk, ListViewProduk.this);
                rc_list_request.setAdapter(adapterProduk);
                loading.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Kode ini akan dipanggil ketika ada error dan
                 * pengambilan data gagal dan memprint error nya
                 * ke LogCat
                 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                loading.dismiss();
            }
        });

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListViewProduk.this, TambahProduk.class)
                        .putExtra("id", "")
                        .putExtra("namaProduk", "")
                        .putExtra("status", "")
                        .putExtra("desc", ""));
            }
        });
    }
}