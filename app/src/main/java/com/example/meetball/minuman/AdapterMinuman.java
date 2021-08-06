package com.example.meetball.minuman;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetball.R;
import com.example.meetball.pemasukkan.AdapterPemasukkan;
import com.example.meetball.pemasukkan.DialogFormPemasukkan;
import com.example.meetball.pemasukkan.Pemasukkan;
import com.example.meetball.pesanan.AdapterPesanan;
import com.example.meetball.pesanan.DialogFormPesanan;
import com.example.meetball.pesanan.PesananAdmin;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterMinuman extends RecyclerView.Adapter<AdapterMinuman.MyViewHolder>{
    private List<Minuman> minumanList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterMinuman(List<Minuman> minumanList, Activity activity){
        this.minumanList = minumanList;
        this.activity = activity;
    }

    @NotNull
    @Override
    public AdapterMinuman.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_minuman_admin,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterMinuman.MyViewHolder holder, int position) {

        final Minuman m = minumanList.get(position);
        holder.tv_namaMinuman.setText("Hari : "+m.getNamaMinuman());
        holder.tv_harga_minuman.setText("Tanggal : "+m.getHarga());
        holder.tv_MinumanDesc.setText("Bulan : "+m.getDesc());

        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.child("minuman").child(m.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity,"Berhasil dihapus", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(activity,"Gagal dihapus", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setMessage("Apakah yakin mau dihapus " + m.getNamaMinuman());
                builder.show();
            }
        });

        holder.item_minuman_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogFormMinuman dialogFormMinuman = new DialogFormMinuman(m.getNamaMinuman(),
                        m.getHarga(),m.getDesc(),m.getKey(),"Ubah");
                dialogFormMinuman.show(manager,"form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return minumanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_namaMinuman;
        TextView tv_harga_minuman;
        TextView tv_MinumanDesc;
        ImageView hapus;
        CardView item_minuman_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_namaMinuman = itemView.findViewById(R.id.tv_namaMinuman);
            tv_harga_minuman = itemView.findViewById(R.id.tv_harga_minuman);
            tv_MinumanDesc = itemView.findViewById(R.id.tv_MinumanDesc);


            item_minuman_layout = itemView.findViewById(R.id.item_minuman_layout);
            hapus = itemView.findViewById(R.id.hapus_minuman);
        }
    }
}
