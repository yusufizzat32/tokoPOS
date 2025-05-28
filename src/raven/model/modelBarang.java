/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.model;

/**
 *
 * @author yusuf
 */
public class modelBarang {
    private String barcode;
    private String idProduk;
    private String namaProduk;
    private double stokProduk;
    private int hargaProduk;
    
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public double getStokProduk() {
        return stokProduk;
    }

    public void setStokProduk(double stokProduk) {
        this.stokProduk = stokProduk;
    }

    public int getHargaProduk() {
        return hargaProduk;
    }

    public void setHargaProduk(int hargaProduk) {
        this.hargaProduk = hargaProduk;
    }

//    public int getNilaiObat() {
//        return nilaiProduk;
//    }
//
//    public void setNilaiObat(int nilaiObat) {
//        this.nilaiObat = nilaiObat;
//    }
    private double jumlahMasuk;

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

//    public String getSatuanObat() {
//        return satuanObat;
//    }
//
//    public void setSatuanObat(String satuanObat) {
//        this.satuanObat = satuanObat;
//    }
    
//    public void setJumlahMasuk(double jumlahMasuk){
//        this.jumlahMasuk = jumlahMasuk;
//    }
//    public double getJumlahMasuk(){
//        return jumlahMasuk;
//    }

    public void setIdPenjualan(String idPenjualan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setJumlah(int jumlah) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setAlasan(String alasan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
