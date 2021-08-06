package com.example.meetball.pemasukkan;

public class Pemasukkan {

    private String key;

    private String hari;
    private String tanggal;
    private String bulan;
    private String tahun;
    private String jumlah_pemasukkan;

    public Pemasukkan(){

    }

    public Pemasukkan(String hari, String tanggal, String bulan, String tahun, String jumlah_pemasukkan) {
        this.hari = hari;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jumlah_pemasukkan = jumlah_pemasukkan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getJumlah_pemasukkan() {
        return jumlah_pemasukkan;
    }

    public void setJumlah_pemasukkan(String jumlah_pemasukkan) {
        this.jumlah_pemasukkan = jumlah_pemasukkan;
    }
}
