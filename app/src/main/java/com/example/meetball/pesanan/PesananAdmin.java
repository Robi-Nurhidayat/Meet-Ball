package com.example.meetball.pesanan;

public class PesananAdmin {

    private String key;

    private String nama;
    private String noMeja;
    private String pesanan1;
    private String jumlahpesanan1;
    private String pesanan2;
    private String jumlahPesanan2;
    private String keterangan;
    private String status;

    public  PesananAdmin(){

    }

    public PesananAdmin(String nama, String noMeja, String pesanan1, String jumlahpesanan1, String pesanan2, String jumlahPesanan2, String keterangan, String status) {
        this.nama = nama;
        this.noMeja = noMeja;
        this.pesanan1 = pesanan1;
        this.jumlahpesanan1 = jumlahpesanan1;
        this.pesanan2 = pesanan2;
        this.jumlahPesanan2 = jumlahPesanan2;
        this.keterangan = keterangan;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoMeja() {
        return noMeja;
    }

    public void setNoMeja(String noMeja) {
        this.noMeja = noMeja;
    }

    public String getPesanan1() {
        return pesanan1;
    }

    public void setPesanan1(String pesanan1) {
        this.pesanan1 = pesanan1;
    }

    public String getJumlahpesanan1() {
        return jumlahpesanan1;
    }

    public void setJumlahpesanan1(String jumlahpesanan1) {
        this.jumlahpesanan1 = jumlahpesanan1;
    }

    public String getPesanan2() {
        return pesanan2;
    }

    public void setPesanan2(String pesanan2) {
        this.pesanan2 = pesanan2;
    }

    public String getJumlahPesanan2() {
        return jumlahPesanan2;
    }

    public void setJumlahPesanann2(String jumlahPesanann2) {
        this.jumlahPesanan2 = jumlahPesanann2;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
