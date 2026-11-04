package com.mycompany.closetta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class closettasystem {
    private wardrobespace ruangWardrobe;
    private ArrayList<FashionItem> wardrobe;
    private HashMap<String, closettamember> dataAnggota;

    public closettasystem() {
        this.ruangWardrobe = new wardrobespace("CLOSETTA Personal Wardrobe", 100);
        this.wardrobe = new ArrayList<>();
        this.dataAnggota = new HashMap<>();
    }

    public void tampilkanInfoRuang() {
        System.out.println("\n--- Komponen Internal (Composition) ---");
        ruangWardrobe.tampilkanInfo();
    }

    public void tambahItem(FashionItem item) {
        if (item == null) {
            System.out.println("Item tidak valid.");
            return;
        }

        if (cariItemById(item.getId()) != null) {
            System.out.println("ID item sudah digunakan.");
            return;
        }

        wardrobe.add(item);
        System.out.println("Item " + item.getNama()
                + " berhasil ditambahkan.");
    }

    public void lihatSemuaItem() {
        System.out.println("\n--- Daftar Item Wardrobe ---");

        if (wardrobe.isEmpty()) {
            System.out.println("Wardrobe masih kosong.");
            return;
        }

        for (int i = 0; i < wardrobe.size(); i++) {
            System.out.print((i + 1) + ". ");
            wardrobe.get(i).tampilkanInfo();
        }

        System.out.println("Total item: " + wardrobe.size());
    }

    public FashionItem cariItem(String kataKunci) {
        if (kataKunci == null) {
            return null;
        }

        for (FashionItem item : wardrobe) {
            if (item.getId().equalsIgnoreCase(kataKunci) || item.getNama().equalsIgnoreCase(kataKunci)) {
                return item;
            }
        }

        return null;
    }

    public FashionItem cariItemById(String id) {
        if (id == null) {
            return null;
        }

        for (FashionItem item : wardrobe) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }

        return null;
    }

    public void tambahAnggota(closettamember anggota) {
        if (anggota == null) {
            System.out.println("Data anggota tidak valid.");
            return;
        }

        if (dataAnggota.containsKey(anggota.getId())) {
            System.out.println("ID anggota sudah digunakan.");
            return;
        }

        dataAnggota.put(anggota.getId(), anggota);
        System.out.println("Anggota " + anggota.getNama() + " berhasil ditambahkan.");
    }

    public closettamember cariAnggota(String id) {
        if (id == null || !dataAnggota.containsKey(id)) {
            return null;
        }

        return dataAnggota.get(id);
    }

    public void lihatSemuaAnggota() {
        System.out.println("\n--- Daftar Anggota ---");

        if (dataAnggota.isEmpty()) {
            System.out.println("Belum ada anggota terdaftar.");
            return;
        }

        for (Map.Entry<String, closettamember> entry : dataAnggota.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().getNama());
        }

        System.out.println("Total anggota: " + dataAnggota.size());
    }

    void tambahitem(Bag bag) {
        throw new UnsupportedOperationException("Belum tersedia."); 
    }
}