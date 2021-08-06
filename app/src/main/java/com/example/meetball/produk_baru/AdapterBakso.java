package com.example.meetball.produk_baru;

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
import com.example.meetball.minuman.AdapterMinuman;
import com.example.meetball.minuman.DialogFormMinuman;
import com.example.meetball.minuman.Minuman;
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

public class AdapterBakso extends RecyclerView.Adapter<AdapterBakso.MyViewHolder> {

    private List<Bakso> baksoList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterBakso(List<Bakso> baksoList, Activity activity) {
        this.baksoList = baksoList;
        this.activity = activity;
    }

    @NotNull
    @Override
    public AdapterBakso.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_produk_baru_admin, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterBakso.MyViewHolder holder, int position) {

        final Bakso b = baksoList.get(position);
        holder.tv_namaBakso.setText("Nama : " + b.getNamaBakso());
        holder.tv_hargaBakso.setText("Harga : " + b.getHarga());
        holder.tv_BaksoDesc.setText("Deskripsi : " + b.getDesc());

        holder.hapus_bakso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.child("bakso").child(b.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(activity, "Gagal dihapus", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setMessage("Apakah yakin mau dihapus " + b.getNamaBakso());
                builder.show();
            }
        });

        holder.item_bakso_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FragmentManager manager = ((AppCompatActivity) activity).getSupportFragmentManager();
                DialogFormBakso dialogFormBakso = new DialogFormBakso(b.getNamaBakso(),
                        b.getHarga(), b.getDesc(), b.getKey(), "Ubah");
                dialogFormBakso.show(manager, "form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return baksoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_namaBakso;
        TextView tv_hargaBakso;
        TextView tv_BaksoDesc;
        ImageView hapus_bakso;
        CardView item_bakso_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_namaBakso = itemView.findViewById(R.id.tv_namaBakso);
            tv_hargaBakso = itemView.findViewById(R.id.tv_hargaBakso);
            tv_BaksoDesc = itemView.findViewById(R.id.tv_BaksoDesc);


            item_bakso_layout = itemView.findViewById(R.id.item_bakso_layout);
            hapus_bakso = itemView.findViewById(R.id.hapus_bakso);
        }
    }
}
