package com.mycompany.closetta;

public class closettamember {
    private final String id;
    private final String nama;

    public closettamember(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void tampilkanInfo() {
        System.out.println("ID Anggota: " + id
                + " | Nama: " + nama);
    }
}