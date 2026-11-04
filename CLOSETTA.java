package com.mycompany.closetta;

import java.util.Scanner;

public class CLOSETTA {
    private static Scanner input = new Scanner(System.in);
    private static closettasystem system = new closettasystem();
    private static WardrobeTransaction transaksi = new WardrobeTransaction();

    public static void main(String[] args) {
        isiDataAwal();

        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaAngka("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    system.tampilkanInfoRuang();
                    break;
                case 2:
                    system.lihatSemuaItem();
                    break;
                case 3:
                    tambahItem();
                    break;
                case 4:
                    cariItem();
                    break;
                case 5:
                    menuAnggota();
                    break;
                case 6:
                    jalankanTransaksi();
                    break;
                case 7:
                    System.out.println("Terima kasih telah menggunakan CLOSETTA!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 7);

        input.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n===== CLOSETTA =====");
        System.out.println("1. Informasi wardrobe");
        System.out.println("2. Tampilkan semua item");
        System.out.println("3. Tambah item");
        System.out.println("4. Cari item");
        System.out.println("5. Kelola anggota");
        System.out.println("6. Simulasi transaksi");
        System.out.println("7. Keluar");
    }

    private static void isiDataAwal() {
        try {
            system.tambahItem(new Clothing(
                    "C001", "Kemeja Putih", "Uniqlo",
                    "Putih", "Kemeja", "M"));

            system.tambahItem(new Clothing(
                    "C002", "Cardigan", "H&M",
                    "Cream", "Outerwear", "L"));

            system.tambahItem(new Accessory(
                    "A001", "Jam Tangan", "Casio",
                    "Hitam", "Jam", "Stainless Steel"));

            system.tambahItem(new Bag(
                    "B001", "Tote Bag", "Studio",
                    "Cream", "Tote Bag", "Kanvas"));

        } catch (exception e) {
            System.out.println("Gagal menambahkan item awal: " + e.getMessage());
        } finally {
            System.out.println("Inisialisasi item selesai.");
        }

        system.tambahAnggota(new closettamember("AG001", "Davina"));
        system.tambahAnggota(new closettamember("AG002", "Lisa"));
        system.tambahAnggota(new closettamember("AG003", "Nina"));
    }

    private static void tambahItem() {
        System.out.println("\n=== TAMBAH ITEM ===");
        System.out.println("1. Clothing");
        System.out.println("2. Accessory");
        System.out.println("3. Bag");

        int jenis = bacaAngka("Pilih jenis item: ");
        String id = bacaTeks("ID item: ");
        String nama = bacaTeks("Nama item: ");
        String brand = bacaTeks("Brand: ");
        String warna = bacaTeks("Warna: ");

        try {
            FashionItem item;

            switch (jenis) {
                case 1:
                    String jenisPakaian = bacaTeks("Jenis pakaian: ");
                    String ukuran = bacaTeks("Ukuran: ");
                    item = new Clothing(id, nama, brand, warna, jenisPakaian, ukuran);
                    break;

                case 2:
                    String jenisAksesori = bacaTeks("Jenis aksesori: ");
                    String materialAksesori = bacaTeks("Material: ");
                    item = new Accessory(id, nama, brand, warna, jenisAksesori, materialAksesori);
                    break;

                case 3:
                    String jenisTas = bacaTeks("Jenis tas: ");
                    String materialTas = bacaTeks("Material: ");
                    item = new Bag(id, nama, brand, warna, jenisTas, materialTas);
                    break;

                default:
                    System.out.println("Jenis item tidak valid.");
                    return;
            }

            if (system.cariItemById(id) != null) {
                System.out.println("ID item sudah digunakan.");
                return;
            }

            system.tambahItem(item);

        } catch (exception e) {
            System.out.println("Gagal menambahkan item: " + e.getMessage());
        } finally {
            System.out.println("Proses tambah item selesai.");
        }
    }

    private static void cariItem() {
        String nama = bacaTeks("Masukkan nama item yang dicari: ");
        system.cariItem(nama);
    }

    private static void menuAnggota() {
        System.out.println("\n=== MENU ANGGOTA ===");
        System.out.println("1. Tambah anggota");
        System.out.println("2. Cari anggota");
        System.out.println("3. Tampilkan semua anggota");

        int pilihan = bacaAngka("Pilih menu: ");

        switch (pilihan) {
            case 1:
                String id = bacaTeks("ID anggota: ");

                if (system.cariAnggota(id) != null) {
                    System.out.println("ID anggota sudah digunakan.");
                    return;
                }

                String nama = bacaTeks("Nama anggota: ");
                system.tambahAnggota(new closettamember(id, nama));
                break;

            case 2:
                String idCari = bacaTeks("Masukkan ID anggota: ");
                closettamember anggota = system.cariAnggota(idCari);

                if (anggota != null) {
                    anggota.tampilkanInfo();
                } else {
                    System.out.println("Anggota tidak ditemukan.");
                }
                break;

            case 3:
                system.lihatSemuaAnggota();
                break;

            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private static void jalankanTransaksi() {
        String idAnggota = bacaTeks("Masukkan ID anggota: ");
        closettamember anggota = system.cariAnggota(idAnggota);

        if (anggota == null) {
            System.out.println("Anggota tidak ditemukan.");
            return;
        }

        system.lihatSemuaItem();
        String idItem = bacaTeks("Masukkan ID item: ");
        FashionItem item = system.cariItemById(idItem);

        transaksi.prosesInteraksi(anggota, item);
    }

    private static int bacaAngka(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine();

            try {
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }
    }

    private static String bacaTeks(String pesan) {
        System.out.print(pesan);
        return input.nextLine().trim();
    }
}