package com.example.meetball.produk_baru;

public class Bakso {

    private String key;

    private String namaBakso;
    private String harga;
    private String desc;

    public Bakso(){

    }

    public Bakso(String namaBakso, String harga, String desc) {
        this.namaBakso = namaBakso;
        this.harga = harga;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamaBakso() {
        return namaBakso;
    }

    public void setNamaBakso(String namaBakso) {
        this.namaBakso = namaBakso;
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
