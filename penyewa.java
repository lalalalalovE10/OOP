package com.mycompany.kuispbo;


public class penyewa {
    private String kodePenyewa;
    private String nama;

    public penyewa(String kodePenyewa, String nama) {
        this.kodePenyewa = kodePenyewa;
        this.nama = nama;
    }

    public String getKodePenyewa() {
        return kodePenyewa;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return kodePenyewa + " - " + nama;
    }
}
