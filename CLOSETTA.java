package com.mycompany.closetta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class CLOSETTA {
    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<FashionItem> wardrobe = new ArrayList<>();
    private static final HashMap<String, String> dataAnggota = new HashMap<>();

    public static void main(String[] args) {
        try {
            isiDataAwal();
            isiAnggotaAwal();
        } catch (exception e) {
            System.out.println("Gagal menyiapkan data awal: " + e.getMessage());
        }

        int pilihan = 0;

        do {
            try {
                tampilkanMenu();
                System.out.print("Pilih menu: ");
                pilihan = bacaAngka();

                switch (pilihan) {
                    case 1:
                        tambahItem();
                        break;
                    case 2:
                        lihatWardrobe();
                        break;
                    case 3:
                        menuCariItem();
                        break;
                    case 4:
                        menuProsesItem();
                        break;
                    case 5:
                        menuAnggota();
                        break;
                    case 6:
                        System.out.println("Terima kasih telah menggunakan CLOSETTA!");
                        System.out.println("Total item berhasil dibuat: "
                                + FashionItem.totalItemBerhasilDibuat);
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            } catch (exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            } finally {
                System.out.println("Blok finally: Proses menu selesai.");
            }
        } while (pilihan != 6);

        input.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n==================================");
        System.out.println("       CLOSETTA WARDROBE");
        System.out.println("    Every piece has its place.");
        System.out.println("==================================");
        System.out.println("1. Tambah Item");
        System.out.println("2. Lihat Wardrobe");
        System.out.println("3. Cari Item");
        System.out.println("4. Proses Item");
        System.out.println("5. Kelola Anggota");
        System.out.println("6. Keluar");
    }

    private static int bacaAngka() {
        String teks = input.nextLine();

        try {
            return Integer.parseInt(teks.trim());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Masukkan angka yang valid!");
        }
    }

    private static String bacaTeks(String pesan) {
        System.out.print(pesan);
        return input.nextLine();
    }

    private static void isiDataAwal() throws exception {
        wardrobe.add(new Clothing(
            "C001", "Kemeja Putih", "Uniqlo", "Putih", "Kemeja", "M"
        ));
        wardrobe.add(new Accessory(
            "A001", "Jam Tangan", "Casio", "Hitam", "Jam", "Stainless Steel"
        ));
        wardrobe.add(new Bag(
            "B001", "Tote Bag", "Studio", "Cream", "Tote Bag", "Kanvas"
        ));
    }

    private static void isiAnggotaAwal() {
        dataAnggota.put("AG001", "Davina");
        dataAnggota.put("AG002", "Lisa");
        dataAnggota.put("AG003", "Nina");
    }

    private static void tambahItem() throws exception {
        System.out.println("\n--- Tambah Item ---");
        System.out.println("1. Clothing");
        System.out.println("2. Accessory");
        System.out.println("3. Bag");

        System.out.print("Pilih jenis item: ");
        int jenis = bacaAngka();

        if (jenis < 1 || jenis > 3)
            throw new exception("Jenis item tidak valid!");

        String id = bacaTeks("ID: ");
        String nama = bacaTeks("Nama item: ");
        String brand = bacaTeks("Brand: ");
        String warna = bacaTeks("Warna: ");

        for (FashionItem item : wardrobe) {
            if (item.getId().equalsIgnoreCase(id.trim()))
                throw new exception("ID item sudah digunakan!");
        }

        FashionItem item;

        switch (jenis) {
            case 1:
                String jenisPakaian = bacaTeks("Jenis pakaian: ");
                String ukuran = bacaTeks("Ukuran: ");
                item = new Clothing(id, nama, brand, warna,
                        jenisPakaian, ukuran);
                break;
            case 2:
                String jenisAksesori = bacaTeks("Jenis aksesori: ");
                String materialAksesori = bacaTeks("Material: ");
                item = new Accessory(id, nama, brand, warna,
                        jenisAksesori, materialAksesori);
                break;
            default:
                String jenisTas = bacaTeks("Jenis tas: ");
                String materialTas = bacaTeks("Material: ");
                item = new Bag(id, nama, brand, warna,
                        jenisTas, materialTas);
                break;
        }

        wardrobe.add(item);
        System.out.println("Item berhasil ditambahkan ke wardrobe!");
    }

    private static void lihatWardrobe() {
        System.out.println("\n--- Isi Wardrobe CLOSETTA ---");

        if (wardrobe.isEmpty()) {
            System.out.println("Wardrobe masih kosong.");
            return;
        }

        for (int i = 0; i < wardrobe.size(); i++) {
            System.out.print((i + 1) + ". ");
            wardrobe.get(i).tampilkanInfo();
        }

        System.out.println("Total item di wardrobe: " + wardrobe.size());
    }

    private static void menuCariItem() throws exception {
        if (wardrobe.isEmpty())
            throw new exception("Wardrobe masih kosong!");

        System.out.println("\n--- Cari Item ---");
        System.out.println("1. Cari berdasarkan nama");
        System.out.println("2. Cari berdasarkan nomor item");
        System.out.print("Pilih metode pencarian: ");

        int pilihan = bacaAngka();

        if (pilihan == 1) {
            String nama = bacaTeks("Masukkan nama item: ");
            cariItem(nama);
        } else if (pilihan == 2) {
            System.out.print("Masukkan nomor item: ");
            int nomor = bacaAngka();
            cariItem(nomor);
        } else {
            throw new exception("Pilihan pencarian tidak valid!");
        }
    }

    public static void cariItem(String nama) throws exception {
        if (nama == null || nama.trim().isEmpty())
            throw new exception("Nama item tidak boleh kosong!");

        boolean ditemukan = false;

        for (FashionItem item : wardrobe) {
            if (item.getNama().equalsIgnoreCase(nama.trim())) {
                System.out.println("Item ditemukan:");
                item.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan)
            throw new exception(
                "Item dengan nama \"" + nama + "\" tidak ditemukan."
            );
    }

    public static void cariItem(int nomorItem) throws exception {
        if (nomorItem < 1 || nomorItem > wardrobe.size())
            throw new exception("Nomor item tidak ditemukan!");

        System.out.println("Item ditemukan:");
        wardrobe.get(nomorItem - 1).tampilkanInfo();
    }

    private static void menuProsesItem() throws exception {
        if (wardrobe.isEmpty())
            throw new exception("Wardrobe masih kosong!");

        lihatWardrobe();
        System.out.print("Pilih nomor item yang ingin diproses: ");
        int nomor = bacaAngka();

        if (nomor < 1 || nomor > wardrobe.size())
            throw new exception("Nomor item tidak valid!");

        prosesItem(wardrobe.get(nomor - 1));
    }

    public static void prosesItem(FashionItem item) {
        System.out.println("\n--- Proses Item CLOSETTA ---");

        item.tampilkanInfo();
        item.gunakanItem();

        if (item instanceof DapatDipadukan) {
            DapatDipadukan styling = (DapatDipadukan) item;
            styling.tampilkanTipsStyling();
        }

        if (item instanceof DapatDirawat) {
            DapatDirawat perawatan = (DapatDirawat) item;
            perawatan.rawatItem();
        }

        System.out.println("Proses item selesai.");
    }

    private static void menuAnggota() throws exception {
        int pilihan;

        do {
            System.out.println("\n--- Kelola Anggota CLOSETTA ---");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Cari Anggota");
            System.out.println("3. Tampilkan Semua Anggota");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");

            pilihan = bacaAngka();

            switch (pilihan) {
                case 1:
                    tambahAnggota();
                    break;
                case 2:
                    cariAnggota();
                    break;
                case 3:
                    tampilkanAnggota();
                    break;
                case 4:
                    System.out.println("Kembali ke menu utama.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 4);
    }

    private static void tambahAnggota() throws exception {
        String id = bacaTeks("Masukkan ID anggota: ");
        String nama = bacaTeks("Masukkan nama anggota: ");

        if (id.trim().isEmpty() || nama.trim().isEmpty())
            throw new exception(
                "ID dan nama anggota tidak boleh kosong!"
            );

        if (dataAnggota.containsKey(id.trim()))
            throw new exception("ID anggota sudah terdaftar!");

        dataAnggota.put(id.trim(), nama.trim());
        System.out.println("Anggota berhasil ditambahkan!");
    }

    private static void cariAnggota() throws exception {
        String id = bacaTeks("Masukkan ID anggota yang dicari: ");

        if (id.trim().isEmpty())
            throw new exception("ID anggota tidak boleh kosong!");

        if (dataAnggota.containsKey(id.trim())) {
            System.out.println("Anggota ditemukan: "
                    + dataAnggota.get(id.trim()));
        } else {
            throw new exception(
                "Anggota dengan ID " + id + " tidak ditemukan!"
            );
        }
    }

    private static void tampilkanAnggota() {
        System.out.println("\n--- Daftar Anggota CLOSETTA ---");

        if (dataAnggota.isEmpty()) {
            System.out.println("Belum ada anggota terdaftar.");
            return;
        }

        System.out.println("Total anggota: " + dataAnggota.size());

        for (Map.Entry<String, String> anggota : dataAnggota.entrySet()) {
            System.out.println("ID: " + anggota.getKey()
                    + " | Nama: " + anggota.getValue());
        }
    }
}