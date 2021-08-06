package com.example.meetball.pesanan;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterPesanan extends RecyclerView.Adapter<AdapterPesanan.MyViewHolder> {

    private List<PesananAdmin> pesananList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterPesanan(List<PesananAdmin> pesananList, Activity activity){
        this.pesananList = pesananList;
        this.activity = activity;
    }

    @NotNull
    @Override
    public AdapterPesanan.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_pesanan_admin,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterPesanan.MyViewHolder holder, int position) {

        final PesananAdmin pesanan = pesananList.get(position);
        holder.tv_namaPesanan.setText("Nama : "+pesanan.getNama());
        holder.tv_noMeja.setText("No Meja : "+pesanan.getNoMeja());
        holder.tv_pesananPertama.setText("Pesanan Pertama : "+pesanan.getPesanan1());
        holder.tv_jumlahPesananPertama.setText("Jumlah Pesanan : "+pesanan.getJumlahpesanan1());
        holder.tv_pesananKeDua.setText("Pesanan Kedua : "+pesanan.getPesanan2());
        holder.tv_jumlahPesananKedua.setText("Jumlah Pesanan : "+pesanan.getJumlahPesanan2());
        holder.tv_ket.setText("Keterangan : "+pesanan.getKeterangan());
        holder.tv_status_pesanan.setText("Status Pesanan : "+pesanan.getStatus());
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.child("pesanan").child(pesanan.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
                }).setMessage("Apakah yakin mau dihapus " + pesanan.getNama());
                builder.show();
            }
        });

        holder.item_pesanan_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogFormPesanan dialogFormPesanan = new DialogFormPesanan(pesanan.getNama(),
                        pesanan.getNoMeja(),pesanan.getPesanan1(),pesanan.getJumlahpesanan1(),pesanan.getPesanan2(),
                        pesanan.getJumlahPesanan2(),pesanan.getKeterangan(),pesanan.getStatus(),pesanan.getKey(),"Ubah");
                dialogFormPesanan.show(manager, "form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return pesananList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_namaPesanan;
        TextView tv_noMeja;
        TextView tv_pesananPertama;
        TextView tv_jumlahPesananPertama;
        TextView tv_pesananKeDua;
        TextView tv_jumlahPesananKedua;
        TextView tv_ket;
        TextView tv_status_pesanan;
        ImageView hapus;
        CardView item_pesanan_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_namaPesanan = itemView.findViewById(R.id.tv_namaPesanan);
            tv_noMeja = itemView.findViewById(R.id.tv_noMeja);
            tv_pesananPertama = itemView.findViewById(R.id.tv_pesananPertama);
            tv_jumlahPesananPertama = itemView.findViewById(R.id.tv_jumlahPesananPertama);
            tv_pesananKeDua = itemView.findViewById(R.id.tv_pesananKeDua);
            tv_jumlahPesananKedua = itemView.findViewById(R.id.tv_jumlahPesananKedua);
            tv_ket = itemView.findViewById(R.id.tv_ket);
            tv_status_pesanan = itemView.findViewById(R.id.tv_status_pesanan);
            item_pesanan_layout = itemView.findViewById(R.id.item_pesanan_layout);
            hapus = itemView.findViewById(R.id.hapus);
        }
    }
}
