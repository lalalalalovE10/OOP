package com.mycompany.closetta;

public class wardrobespace {
    private String namaRuang;
    private int kapasitas;

    public wardrobespace(String namaRuang, int kapasitas) {
        this.namaRuang = namaRuang;
        this.kapasitas = kapasitas;
    }

    public void tampilkanInfo() {
        System.out.println("Nama komponen : " + namaRuang);
        System.out.println("Kapasitas     : " + kapasitas + " item");
        System.out.println("Status        : Komponen internal ClosettaSystem.");
    }
}