/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package raven.service;
import java.sql.SQLException;
import raven.model.modelRekapTransaksi;
import java.util.List;
import raven.model.modelDetailRetur;
import raven.model.modelReturBarang;

/**
 *
 * @author yusuf
*/
public interface serviceReturDetail {
    void insertReturDetail(modelDetailRetur detail);
    void updateReturDetail(modelDetailRetur detail);
     void deleteReturDetail(int idDetailRetur);
    
      List<modelDetailRetur> getAllReturDetail() throws SQLException;
}
