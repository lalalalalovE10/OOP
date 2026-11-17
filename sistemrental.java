package com.mycompany.kuispbo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class sistemrental {
    private String namaRental;
    private ArrayList<penyewa> daftarPenyewa;
    private ArrayList<Kendaraan> daftarKendaraan;
    private ArrayList<transaksirental> daftarTransaksi;

    public sistemrental(String namaRental) {
        this.namaRental = namaRental;
        daftarPenyewa = new ArrayList<>();
        daftarKendaraan = new ArrayList<>();
        daftarTransaksi = new ArrayList<>();
    }

    public void tambahPenyewa(String kode, String nama) {
        if (caripenyewa(kode) != null) {
            System.out.println("Penyewa " + kode + " sudah terdaftar.");
            return;
        }

        daftarPenyewa.add(new penyewa(kode, nama));
        System.out.println("Penyewa berhasil ditambahkan: " + nama);
    }

    public void tambahKendaraan(String kode, String nama, double tarif) {
        if (cariKendaraan(kode) != null) {
            System.out.println("Kendaraan " + kode + " sudah terdaftar.");
            return;
        }

        if (tarif < 0) {
            System.out.println("Tarif tidak boleh negatif.");
            return;
        }

        daftarKendaraan.add(
                new Kendaraan(kode, nama, tarif));

        System.out.println("Kendaraan berhasil ditambahkan: " + nama);
    }

    private penyewa caripenyewa(String kode) {
        for (penyewa p : daftarPenyewa) {
            if (p.getKodePenyewa().equalsIgnoreCase(kode)) {
                return p;
            }
        }

        return null;
    }

    private Kendaraan cariKendaraan(String kode) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                return k;
            }
        }

        return null;
    }

    public void jalankanTransaksiDariFile(String namaFile)
            throws IOException {

        int jumlahBerhasil = 0;
        int jumlahGagal = 0;

        
InputStream stream = sistemrental.class.getResourceAsStream(
        "/input_transaksi.txt");

if (stream == null) {
    throw new IOException(
            "File input_transaksi.txt tidak ditemukan di resources.");
}

try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(stream, StandardCharsets.UTF_8))) {

 

            String baris;
            int nomorBaris = 0;

            while ((baris = reader.readLine()) != null) {
                nomorBaris++;
                baris = baris.trim();

                if (baris.isEmpty() || baris.startsWith("#")) {
                    continue;
                }

                try {
                    String[] data = baris.split(",", -1);

                    if (data.length != 3) {
                        throw new IllegalArgumentException("Format harus: kodePenyewa," + "kodeKendaraan,lamaSewa");
                    }

                    String kodePenyewa = data[0].trim();
                    String kodeKendaraan = data[1].trim();
                    int lamaSewa = Integer.parseInt(data[2].trim());

                    penyewa penyewa = caripenyewa(kodePenyewa);
                    Kendaraan kendaraan = cariKendaraan(kodeKendaraan);

                    if (penyewa == null) {
                        throw new IllegalArgumentException("Kode penyewa tidak ditemukan: " + kodePenyewa);
                    }

                    if (kendaraan == null) {
                        throw new IllegalArgumentException("Kode kendaraan tidak ditemukan: " + kodeKendaraan);
                    }

                    transaksirental transaksi = new transaksirental(penyewa, kendaraan, lamaSewa);

                    daftarTransaksi.add(transaksi);
                    jumlahBerhasil++;

                    System.out.println(
                            "[TRANSAKSI BERHASIL] " + transaksi);

                } catch (NumberFormatException e) {
                    jumlahGagal++;
                    System.out.println("[BARIS " + nomorBaris + " GAGAL] Lama sewa harus berupa angka.");

                } catch (IllegalArgumentException e) {
                    jumlahGagal++;
                    System.out.println("[BARIS " + nomorBaris + " GAGAL] " + e.getMessage());
                }
            }

        } finally {
            System.out.println(
                    "--- PROSES PEMBACAAN FILE SELESAI ---");
        }

        System.out.println("Transaksi berhasil: " + jumlahBerhasil);
        System.out.println("Transaksi gagal: " + jumlahGagal);
    }

    public void simpanLaporanAkhir() {
        String namaFile = "laporan_rental.txt";
        double totalPendapatan = 0;

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(namaFile))) {

            writer.write("LAPORAN AKHIR SISTEM RENTAL");
            writer.newLine();
            writer.write("Nama rental: " + namaRental);
            writer.newLine();
            writer.write("========================================");
            writer.newLine();

            writer.write("DAFTAR PENYEWA");
            writer.newLine();

            for (penyewa p : daftarPenyewa) {
                writer.write(p.toString());
                writer.newLine();
            }

            writer.newLine();
            writer.write("DAFTAR KENDARAAN");
            writer.newLine();

            for (Kendaraan k : daftarKendaraan) {
                writer.write(k.toString());
                writer.newLine();
            }

            writer.newLine();
            writer.write("DAFTAR TRANSAKSI");
            writer.newLine();

            for (transaksirental t : daftarTransaksi) {
                writer.write(t.toString());
                writer.newLine();
                totalPendapatan += t.getTotalBiaya();
            }

            writer.newLine();
            writer.write("========================================");
            writer.newLine();
            writer.write("Jumlah transaksi: " + daftarTransaksi.size());
            writer.newLine();
            writer.write("Total pendapatan: " + String.format(Locale.US, "%.2f", totalPendapatan));
            writer.newLine();

            System.out.println(
                    "Laporan berhasil disimpan ke " + namaFile);
            System.out.println("Total pendapatan: " + String.format(Locale.US, "%.2f", totalPendapatan));

        } catch (IOException e) {
            System.out.println("[ERROR LAPORAN] Gagal menyimpan laporan: " + e.getMessage());
        }
    }
}
