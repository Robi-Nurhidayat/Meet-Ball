package com.example.meetball;

import java.io.Serializable;

public class RequestTambahProduk implements Serializable {

    private String namaProduk;
    private String status;
    private String desc;
    private String key;

    public RequestTambahProduk(){

    }

    public RequestTambahProduk(String namaProduk, String status, String desc) {
        this.namaProduk = namaProduk;
        this.status = status;
        this.desc = desc;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
