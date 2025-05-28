/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.service;
import raven.model.modelPenjualan;
import java.util.List;
import raven.model.modelBarang;

/**
 *
 * @author yusuf
 */
public interface servicePenjualan {
    void tambahPenjualan (modelPenjualan model);
    void hapusPenjualan (modelPenjualan model);
    
    List<modelPenjualan> tampilPenjualan(int id);
    List<modelPenjualan> cariData(String keyword);
    List<modelPenjualan> tampilPenjualanByPeriod(int idUser, String period);
    
    String noTransaksi();
    void printNota (String id);
    modelBarang cariBarangByBarcode(String barcode);
}
