package com.example.meetball.minuman;

public class Minuman {

    private String key;

    private String namaMinuman;
    private String harga;
    private String desc;

    public Minuman(){

    }

    public Minuman(String namaMinuman, String harga, String desc) {
        this.namaMinuman = namaMinuman;
        this.harga = harga;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamaMinuman() {
        return namaMinuman;
    }

    public void setNamaMinuman(String namaMinuman) {
        this.namaMinuman = namaMinuman;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
