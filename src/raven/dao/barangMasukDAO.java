/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.dao;

import raven.config.connectionDB;
import raven.model.modelBarangMasuk;
import raven.service.serviceBarangMasuk;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import raven.service.serviceBarang;

/**
 *
 * @author yusuf
 */
public class barangMasukDAO implements serviceBarangMasuk {
    
    private final serviceBarang barang = new barangDAO();
    private Connection conn;
    public barangMasukDAO() {
        conn = connectionDB.getConnection();
    }
    @Override
    public void tambahData(modelBarangMasuk model) {
        PreparedStatement st = null;
        String sql = "INSERT INTO tabel_produkmasuk (Kd_produkmasuk, Nama_Produk,Kd_Produk, Harga_Satuan,Jumlah,Jumlah_Masuk, Nilai) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            
            st = conn.prepareStatement(sql);
            st.setInt(1, model.getIdProdukMasuk());
            st.setString(2, model.getNamaProduk());
            st.setString(3, model.getIdProduk());
            st.setDouble(4, model.getHargaProduk());
            st.setDouble(5, model.getStokProduk());
            st.setDouble(6, model.getJumlahMasuk());
            st.setDouble(7, model.getNilaiProduk());
            barang.updateStok(model.getIdProduk(),model.getJumlahMasuk());

            
            st.executeUpdate();
            System.out.println("Data berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }

    @Override
    public void updateData(modelBarangMasuk model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void hapusData(modelBarangMasuk model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<modelBarangMasuk> showData() {
        List listBarang = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tabel_produkmasuk";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);
            System.out.println("Menampilkan");

            while (rs.next()) {
                modelBarangMasuk barang = new modelBarangMasuk();
                barang.setIdProdukMasuk(rs.getInt("Kd_Produkmasuk"));
                barang.setIdProduk(rs.getString("Kd_Produk"));
                barang.setNamaProduk(rs.getString("Nama_Produk"));
                barang.setHargaProduk(rs.getLong("Harga_Satuan"));
                barang.setNilaiProduk(rs.getLong("Nilai"));
                barang.setStokProduk(rs.getLong("Jumlah"));
                barang.setJumlahMasuk(rs.getLong("Jumlah_masuk"));
                barang.setTanggal(rs.getString("Tanggal_masuk"));
                listBarang.add(barang);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return listBarang;
    }

    @Override
    public List<modelBarangMasuk> searchData(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
}
