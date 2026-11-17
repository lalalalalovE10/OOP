package com.mycompany.kuispbo;


public class Kendaraan {
    private String kodeKendaraan;
    private String namaKendaraan;
    private double tarifPerHari;

    public Kendaraan(String kodeKendaraan,
                     String namaKendaraan,
                     double tarifPerHari) {
        this.kodeKendaraan = kodeKendaraan;
        this.namaKendaraan = namaKendaraan;
        this.tarifPerHari = tarifPerHari;
    }

    public String getKodeKendaraan() {
        return kodeKendaraan;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public double getTarifPerHari() {
        return tarifPerHari;
    }

    public double hitungBiaya(int lamaSewa) {
        return tarifPerHari * lamaSewa;
    }

    @Override
    public String toString() {
        return kodeKendaraan + " - " + namaKendaraan
                + " (Tarif/hari: " + tarifPerHari + ")";
    }
}
