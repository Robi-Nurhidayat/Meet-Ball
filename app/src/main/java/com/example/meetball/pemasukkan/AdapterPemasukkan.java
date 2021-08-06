package com.example.meetball.pemasukkan;

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
import com.example.meetball.pesanan.AdapterPesanan;
import com.example.meetball.pesanan.DialogFormPesanan;
import com.example.meetball.pesanan.PesananAdmin;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterPemasukkan extends RecyclerView.Adapter<AdapterPemasukkan.MyViewHolder>{
    private List<Pemasukkan> pemasukkanList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterPemasukkan(List<Pemasukkan> pemasukkanList, Activity activity){
        this.pemasukkanList = pemasukkanList;
        this.activity = activity;
    }

    @NotNull
    @Override
    public AdapterPemasukkan.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_pemasukkan_admin,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterPemasukkan.MyViewHolder holder, int position) {

        final Pemasukkan p = pemasukkanList.get(position);
        holder.tv_hari.setText("Hari : "+p.getHari());
        holder.tv_tanggal.setText("Tanggal : "+p.getTanggal());
        holder.tv_bulan.setText("Bulan : "+p.getBulan());
        holder.tv_tahun.setText("Tahun : "+p.getTahun());
        holder.tv_pemasukkan.setText("Pemasukkan : "+p.getJumlah_pemasukkan());

        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.child("pemasukkan").child(p.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
                }).setMessage("Apakah yakin mau dihapus " + p.getHari());
                builder.show();
            }
        });

        holder.item_pemasukkan_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogFormPemasukkan dialogFormPemasukkan = new DialogFormPemasukkan(p.getHari(),
                        p.getTanggal(),p.getBulan(),p.getTahun(),p.getJumlah_pemasukkan(),p.getKey(),"Ubah");
                dialogFormPemasukkan.show(manager,"form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return pemasukkanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_hari;
        TextView tv_tanggal;
        TextView tv_bulan;
        TextView tv_tahun;
        TextView tv_pemasukkan;
        ImageView hapus;
        CardView item_pemasukkan_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_hari = itemView.findViewById(R.id.tv_hari);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
            tv_bulan = itemView.findViewById(R.id.tv_bulan);
            tv_tahun = itemView.findViewById(R.id.tv_tahun);
            tv_pemasukkan = itemView.findViewById(R.id.tv_pemasukkan);

            item_pemasukkan_layout = itemView.findViewById(R.id.item_pemasukkan_layout);
            hapus = itemView.findViewById(R.id.hapus_pemasukkan);
        }
    }
}
