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
    String ambilNamaBarangId(int id);

    public List<modelBarang> searchByBarcode(String id);
}
