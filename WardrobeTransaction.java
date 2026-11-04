package com.mycompany.closetta;

public class WardrobeTransaction {

    public void prosesInteraksi(
            closettamember anggota, FashionItem item) {

        if (anggota == null || item == null) {
            System.out.println("Interaksi gagal: data tidak valid.");
            return;
        }

        System.out.println("\n=== HASIL INTERAKSI CLOSETTA ===");
        System.out.println("Anggota yang memilih : "
                + anggota.getNama() + " (" + anggota.getId() + ")");
        System.out.println("Item yang dipilih    : "
                + item.getNama() + " (" + item.getId() + ")");
        System.out.println("Status               : Interaksi berhasil.");

        item.gunakanItem();

        if (item instanceof DapatDipadukan) {
            DapatDipadukan styling = (DapatDipadukan) item;
            styling.tampilkanTipsStyling();
        }

        if (item instanceof DapatDirawat) {
            DapatDirawat perawatan = (DapatDirawat) item;
            perawatan.rawatItem();
        }

        System.out.println("=== INTERAKSI SELESAI ===");
    }
}