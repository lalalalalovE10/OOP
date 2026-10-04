package com.mycompany.closetta;

import java.util.ArrayList;
import java.util.Scanner;

public class CLOSETTA {

    public static void cariItem(
            String nama,
            ArrayList<FashionItem> wardrobe) {

        System.out.println("\nMencari Item dengan Nama: " + nama);

        boolean ditemukan = false;

        for (FashionItem item : wardrobe) {

            if (item.getNama().equalsIgnoreCase(nama)) {

                System.out.print("- Ditemukan: ");
                item.tampilkanInfo();

                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Item tidak ditemukan.");
        }
    }

    public static void cariItem(
            boolean hanyaClothing,
            ArrayList<FashionItem> wardrobe) {

        String jenisYangDicari;

        if (hanyaClothing) {
            jenisYangDicari = "Clothing";
        } else {
            jenisYangDicari = "Accessory";
        }

        System.out.println(
                "\nMenampilkan Koleksi: " + jenisYangDicari
        );

        boolean ditemukan = false;

        for (FashionItem item : wardrobe) {

            if (hanyaClothing && item instanceof Clothing) {

                System.out.print("- ");
                item.tampilkanInfo();

                ditemukan = true;

            } else if (!hanyaClothing
                    && item instanceof Accessory) {

                System.out.print("- ");
                item.tampilkanInfo();

                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println(
                    "Belum ada item dengan jenis tersebut."
            );
        }
    }

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {


            ArrayList<FashionItem> wardrobe = new ArrayList<>();

            boolean isRunning = true;

            System.out.println("==============================================");
            System.out.println("             WELCOME TO CLOSETTA             ");
            System.out.println("        Personal Wardrobe Management         ");
            System.out.println("==============================================");

            while (isRunning) {

                System.out.println("\nMenu Utama:");
                System.out.println("1. Tambah Item Baru");
                System.out.println("2. Tampilkan Seluruh Wardrobe");
                System.out.println("3. Cari Item / Filter Wardrobe");
                System.out.println("4. Keluar");
                System.out.print("Pilih Menu (1-4): ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {

                    case 1 -> {

                        System.out.println("\n-- Pilih Jenis Item --");
                        System.out.println("1. Clothing");
                        System.out.println("2. Accessory");
                        System.out.print("Pilihan (1/2): ");

                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        if (jenis != 1 && jenis != 2) {

                            System.out.println(
                                    "Jenis item tidak valid."
                            );

                        } else {

                            System.out.print("Masukkan ID Item: ");
                            String idBaru = scanner.nextLine();

                            System.out.print("Masukkan Nama Item: ");
                            String namaBaru = scanner.nextLine();

                            System.out.print("Masukkan Brand: ");
                            String brandBaru = scanner.nextLine();

                            System.out.print("Masukkan Warna: ");
                            String warnaBaru = scanner.nextLine();

                            if (jenis == 1) {

                                System.out.print(
                                        "Masukkan Jenis Pakaian: "
                                );
                                String jenisPakaian =
                                        scanner.nextLine();

                                System.out.print(
                                        "Masukkan Ukuran: "
                                );
                                String ukuran =
                                        scanner.nextLine();

                                wardrobe.add(
                                        new Clothing(
                                                idBaru,
                                                namaBaru,
                                                brandBaru,
                                                warnaBaru,
                                                jenisPakaian,
                                                ukuran
                                        )
                                );

                                System.out.println(
                                        "Sukses! Clothing "
                                        + "berhasil ditambahkan."
                                );

                            } else {

                                System.out.print(
                                        "Masukkan Jenis Aksesori: "
                                );
                                String jenisAksesori =
                                        scanner.nextLine();

                                System.out.print(
                                        "Masukkan Material: "
                                );
                                String material =
                                        scanner.nextLine();

                                wardrobe.add(
                                        new Accessory(
                                                idBaru,
                                                namaBaru,
                                                brandBaru,
                                                warnaBaru,
                                                jenisAksesori,
                                                material
                                        )
                                );

                                System.out.println(
                                        "Sukses! Accessory "
                                        + "berhasil ditambahkan."
                                );
                            }
                        }

                        System.out.print(
                                "Tekan Enter untuk melanjutkan..."
                        );
                        scanner.nextLine();
                    }

                    case 2 -> {

                        System.out.println(
                                "\n========== ISI WARDROBE CLOSETTA =========="
                        );

                        if (wardrobe.isEmpty()) {

                            System.out.println(
                                    "Belum ada item yang tersimpan."
                            );

                        } else {

                            for (int i = 0;
                                    i < wardrobe.size();
                                    i++) {

                                System.out.print(
                                        (i + 1) + ". "
                                );

                                wardrobe.get(i).tampilkanInfo();

                                System.out.println();
                            }

                            System.out.println(
                                    "* Total Item Berhasil Dibuat: "
                                    + FashionItem
                                            .totalItemBerhasilDibuat
                            );
                        }

                        System.out.println(
                                "============================================"
                        );

                        System.out.print(
                                "Tekan Enter untuk melanjutkan..."
                        );
                        scanner.nextLine();
                    }

                    case 3 -> {

                        System.out.println(
                                "\n-- Cari / Filter Wardrobe --"
                        );

                        System.out.println(
                                "1. Cari berdasarkan Nama"
                        );

                        System.out.println(
                                "2. Tampilkan Clothing"
                        );

                        System.out.println(
                                "3. Tampilkan Accessory"
                        );

                        System.out.print("Pilih (1-3): ");

                        int modeCari = scanner.nextInt();
                        scanner.nextLine();

                        switch (modeCari) {

                            case 1 -> {

                                System.out.print(
                                        "Masukkan Nama Item: "
                                );

                                String kataKunci =
                                        scanner.nextLine();

                                cariItem(
                                        kataKunci,
                                        wardrobe
                                );
                            }

                            case 2 -> {

                                cariItem(
                                        true,
                                        wardrobe
                                );
                            }

                            case 3 -> {

                                cariItem(
                                        false,
                                        wardrobe
                                );
                            }

                            default -> {

                                System.out.println(
                                        "Pilihan tidak valid."
                                );
                            }
                        }

                        System.out.print(
                                "Tekan Enter untuk melanjutkan..."
                        );

                        scanner.nextLine();
                    }

                    case 4 -> {

                        System.out.println(
                                "\nTerima kasih telah menggunakan CLOSETTA!"
                        );

                        isRunning = false;
                    }

                    default -> {

                        System.out.println(
                                "Pilihan tidak valid. "
                                + "Silakan masukkan angka 1-4."
                        );
                    }
                }
            }
        }
    }
}