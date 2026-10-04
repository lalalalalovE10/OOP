package com.mycompany.closetta;

public class Accessory extends FashionItem {

    private String jenisAksesori;
    private String material;

    public Accessory(String id, String nama, String brand, String warna,
                     String jenisAksesori, String material) {

        super(id, nama, brand, warna);

        this.setJenisAksesori(jenisAksesori);
        this.setMaterial(material);
    }

    public String getJenisAksesori() {
        return this.jenisAksesori;
    }

    public void setJenisAksesori(String jenisAksesori) {
        if (jenisAksesori != null && !jenisAksesori.trim().isEmpty()) {
            this.jenisAksesori = jenisAksesori;
        } else {
            System.out.println("Jenis aksesori tidak boleh kosong!");
        }
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        if (material != null && !material.trim().isEmpty()) {
            this.material = material;
        } else {
            System.out.println("Material tidak boleh kosong!");
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf(
                "[ACCESSORY] ID: %-8s | Nama: %-18s | Brand: %-15s | Warna: %-10s | Jenis: %-12s | Material: %s%n",
                this.getId(),
                this.getNama(),
                this.getBrand(),
                this.getWarna(),
                this.jenisAksesori,
                this.material
        );
    }
}