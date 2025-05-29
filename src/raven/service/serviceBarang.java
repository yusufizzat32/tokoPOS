/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package raven.service;

import raven.model.modelBarang;
import java.util.List;

/**
 *
 * @author yusuf
 */
public interface serviceBarang {
    void tambahData (modelBarang model);
    void updateData (modelBarang model);
    void hapusData (modelBarang model);
    void updateStok (String id, double stok);
    
    
    List<modelBarang> showData();
    List<modelBarang> searchData(String keyword);
    List<modelBarang> ambilNamaBarang();
    List<String> getKategoriList();
    List<modelBarang> searchDataByKategori(String keyword, String kategori);
    String ambilNamaBarangId(int id);
    String generateProductId();
    List<modelBarang> getDataWithPagination(int offset, int limit);
    int getTotalRowCount();
    modelBarang getByKode(String kodeProduk);
    List<modelBarang> searchDataWithPagination(String keyword, int offset, int limit);
    int getSearchRowCount(String keyword);

    List<modelBarang> searchDataByKategoriWithPagination(String keyword, String kategori, int offset, int limit);
    int getSearchByKategoriRowCount(String keyword, String kategori);

    modelBarang cariBarangByBarcode(String id); // Perubahan di sini
}
