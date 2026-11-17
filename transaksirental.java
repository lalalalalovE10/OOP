package com.mycompany.kuispbo;


public class transaksirental {
    private penyewa penyewa;
    private Kendaraan kendaraan;
    private int lamaSewa;
    private double totalBiaya;

    public transaksirental(penyewa penyewa,
                           Kendaraan kendaraan,
                           int lamaSewa) {
        if (lamaSewa <= 0) {
            throw new IllegalArgumentException(
                    "Lama sewa harus lebih dari 0 hari.");
        }

        this.penyewa = penyewa;
        this.kendaraan = kendaraan;
        this.lamaSewa = lamaSewa;
        this.totalBiaya = kendaraan.hitungBiaya(lamaSewa);
    }

    public penyewa getPenyewa() {
        return penyewa;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public int getLamaSewa() {
        return lamaSewa;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    @Override
    public String toString() {
        return "Penyewa: " + penyewa.getNama()
                + " | Kendaraan: " + kendaraan.getNamaKendaraan()
                + " | Lama sewa: " + lamaSewa + " hari"
                + " | Total: " + totalBiaya;
    }
}
