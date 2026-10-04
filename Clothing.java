package com.mycompany.closetta;

public class Clothing extends FashionItem {

    private String jenisPakaian;
    private String ukuran;

    public Clothing(String id, String nama, String brand, String warna,
                     String jenisPakaian, String ukuran) {

        super(id, nama, brand, warna);

        this.setJenisPakaian(jenisPakaian);
        this.setUkuran(ukuran);
    }

    public String getJenisPakaian() {
        return this.jenisPakaian;
    }

    public void setJenisPakaian(String jenisPakaian) {
        if (jenisPakaian != null && !jenisPakaian.trim().isEmpty()) {
            this.jenisPakaian = jenisPakaian;
        } else {
            System.out.println("Jenis pakaian tidak boleh kosong!");
        }
    }

    public String getUkuran() {
        return this.ukuran;
    }

    public void setUkuran(String ukuran) {
        if (ukuran != null && !ukuran.trim().isEmpty()) {
            this.ukuran = ukuran;
        } else {
            System.out.println("Ukuran tidak boleh kosong!");
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf(
                "[CLOTHING] ID: %-8s | Nama: %-18s | Brand: %-15s | Warna: %-10s | Jenis: %-12s | Ukuran: %s%n",
                this.getId(),
                this.getNama(),
                this.getBrand(),
                this.getWarna(),
                this.jenisPakaian,
                this.ukuran
        );
    }
}