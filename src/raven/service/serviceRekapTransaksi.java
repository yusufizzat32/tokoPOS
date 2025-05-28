/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package raven.service;
import raven.model.modelRekapTransaksi;
import java.util.List;

/**
 *
 * @author yusuf
*/
public interface serviceRekapTransaksi {
    void tambahData (modelRekapTransaksi model);
    void updateData (modelRekapTransaksi model);
    void hapusData (modelRekapTransaksi model);
    void updateQty (String idProduk, int Qty);
    void updateStok (String idProduk, int qty);
    boolean cekStok(String idProduk, int qty);
    List<modelRekapTransaksi> tampilData();
}
