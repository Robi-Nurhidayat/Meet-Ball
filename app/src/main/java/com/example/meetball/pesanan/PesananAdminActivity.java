package com.example.meetball.pesanan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.meetball.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PesananAdminActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    AdapterPesanan adapterPesanan;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<PesananAdmin> listP;
    RecyclerView rv_pesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_admin);

        rv_pesanan = findViewById(R.id.rv_pesanan);
        RecyclerView.LayoutManager pesananLayout = new LinearLayoutManager(this);
        rv_pesanan.setLayoutManager(pesananLayout);
        rv_pesanan.setItemAnimator(new DefaultItemAnimator());

        floatingActionButton = findViewById(R.id.fb_add_pesanan);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFormPesanan dialogFormPesanan = new DialogFormPesanan("","","","","","","","","","Tambah");
                dialogFormPesanan.show(getSupportFragmentManager(),"form");

            }
        });

        showData();
    }

    private void showData(){
        database.child("pesanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                listP = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    PesananAdmin pesananAdmin = item.getValue(PesananAdmin.class);
                    pesananAdmin.setKey(item.getKey());
                    listP.add(pesananAdmin);
                }

                adapterPesanan = new AdapterPesanan(listP,PesananAdminActivity.this);
                rv_pesanan.setAdapter(adapterPesanan);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}