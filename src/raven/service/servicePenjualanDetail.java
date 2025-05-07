/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.service;
import raven.model.modelPenjualanDetail;
import java.util.List;
/**
 *
 * @author yusuf
 */
public interface servicePenjualanDetail {
    void tambah_detail_P(modelPenjualanDetail model);
    void sumTotal(modelPenjualanDetail model);
    void hapusDataSmt();
    List<modelPenjualanDetail> tampil_detail_P(String id);
    List<modelPenjualanDetail> search(String keyword);
}
