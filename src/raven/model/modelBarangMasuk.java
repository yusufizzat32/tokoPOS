/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.model;

/**
 *
 * @author yusuf
 */
public class modelBarangMasuk {
    private int idProdukMasuk;
    private String idProduk;
    private String namaProduk;
    private String satuanProduk;
    private double stokProduk;
    private double hargaProduk;
    private double nilaiProduk;
    private double jumlahMasuk;
    private String tanggal;

    public Integer getIdProdukMasuk() {
        return idProdukMasuk;
    }

    public void setIdProdukMasuk(Integer idProdukMasuk) {
        this.idProdukMasuk = idProdukMasuk;
    }

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

    public String getSatuanObat() {
        return satuanProduk;
    }

    public void setSatuanProduk(String satuanProduk) {
        this.satuanProduk = satuanProduk;
    }

    public double getStokProduk() {
        return stokProduk;
    }

    public void setStokProduk(double stokProduk) {
        this.stokProduk = stokProduk;
    }
    
    public double getHargaProduk() {
        return hargaProduk;
    }

    public void setHargaProduk(double hargaProduk) {
        this.hargaProduk = hargaProduk;
    }
    public double getNilaiProduk() {
        return nilaiProduk;
    }
    
    public void setNilaiProduk(double nilaiProduk) {
        this.nilaiProduk = nilaiProduk;
    }
    
    public void setJumlahMasuk(double jumlahMasuk){
        this.jumlahMasuk = jumlahMasuk;
    }
    public double getJumlahMasuk(){
        return jumlahMasuk;
    }
    public double getHarga_Jual() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
