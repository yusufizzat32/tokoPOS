/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package raven.service;
import java.sql.SQLException;
import raven.model.modelRekapTransaksi;
import java.util.List;
import raven.model.modelReturBarang;

/**
 *
 * @author revi
 */
public interface serviceRetur {
    void insertRetur(modelReturBarang retur);
    void updateRetur(modelReturBarang retur);
    void deleteRetur(int idRetur);

    void updateQty (String idProduk, int Qty);
      List<modelReturBarang> getAllRetur() throws SQLException;
}
