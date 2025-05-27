/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package raven.service;

import raven.model.modelBarangMasuk;
import java.util.List;
/**
 *
 * @author yusuf
 */
public interface serviceBarangMasuk {
    void tambahData (modelBarangMasuk model);
    void updateData (modelBarangMasuk model);
    void hapusData (modelBarangMasuk model);
    List<modelBarangMasuk> showDataByPeriod(String period);
    List<modelBarangMasuk> showData();
    List<modelBarangMasuk> searchData(String id);
}
