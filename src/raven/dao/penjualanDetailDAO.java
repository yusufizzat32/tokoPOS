/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.dao;
import raven.config.connectionDB;
import raven.model.modelPenjualanDetail;
import raven.model.modelPenjualan;
import raven.service.servicePenjualanDetail;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import raven.model.modelBarang;
/**
 *
 * @author yusuf
 */
public class penjualanDetailDAO implements servicePenjualanDetail{
    
    private final Connection conn;

    public penjualanDetailDAO() {
        conn = connectionDB.getConnection();
    }

    @Override
    public void tambah_detail_P(modelPenjualanDetail model) {
        if (!cekStok(model.getModelBaraang().getIdProduk(), model.getQty())) {
            System.out.println(
                    "Stok tidak mencukupi untuk obat ID: " + model.getModelBaraang().getIdProduk());
            return; // Hentikan proses jika stok tidak cukup
        }

        String sql =
                "INSERT INTO tabel_transaksidetail (Ref, Kd_Produk, Nama_Produk,Harga_Satuan, Quantity, Subtotal) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, model.getModelPenjualan().getRef());
            st.setString(2, model.getModelBaraang().getIdProduk());
            st.setString(3, model.getModelBaraang().getNamaProduk());
            st.setDouble(4, model.getModelBaraang().getHargaProduk());
            st.setDouble(5, model.getQty());
            st.setDouble(6, model.getNilai());
            st.executeUpdate();


            // Update stok setelah menambahkan detail penjualan
//            updateStok(model.getModelBaraang().getIdProduk(), model.getQty());
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan detail penjualan: " + e.getMessage());
        }
    }

    @Override
    public void sumTotal(modelPenjualanDetail model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(subtotal) FROM tabel_rekaptransaksi";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                model.setNilai(rs.getInt(1));
            }  
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void hapusDataSmt() {
        String sql = "DELETE FROM tabel_rekaptransaksi";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }

    @Override
    public List<modelPenjualanDetail> tampil_detail_P(String id) {
        List<modelPenjualanDetail> details = new ArrayList<>();

        // SQL untuk mengambil detail penjualan berdasarkan id transaksi
        String sql = "SELECT td.*, m.* "
                + "FROM tabel_transaksidetail td "
                + "JOIN tabel_barang m ON td.Kd_Produk = m.Kd_Produk " + "WHERE td.Ref = ?"; 

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    // Set ModelMasterObat
                    modelBarang obat = new modelBarang();
                    obat.setIdProduk(rs.getString("Kd_Produk"));
                    obat.setNamaProduk(rs.getString("Nama_Produk"));
                    obat.setHargaProduk(rs.getInt("Harga_Jual"));

                    // Set ModelPenjualan (asumsi sudah ada, Anda bisa menyesuaikan)
                    modelPenjualan penjualan = new modelPenjualan();
                    penjualan.setRef(id); // ID transaksi di sini bisa disesuaikan

                    // Set qty dan nilai
                    modelPenjualanDetail detail = new modelPenjualanDetail();
                    detail.setModelBarang(obat);
                    detail.setModelPenjualan(penjualan);
                    detail.setQty(rs.getInt("Qty"));
                    detail.setNilai(rs.getInt("Subtotal"));

                    // Tambahkan detail ke list
                    details.add(detail);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data detail penjualan: " + e.getMessage());
        }

        return details;
    }

    @Override
    public List<modelPenjualanDetail> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void updateStok(String idProduk, double qty) {
        String sql = "UPDATE tabel_barang SET Stok = Stok - ? WHERE Kd_Produk = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDouble(1, qty);
            st.setString(2, idProduk);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui stok: " + e.getMessage());
        }
    }
    public boolean cekStok(String idProduk, double qty) {
        String sql = "SELECT Stok FROM tabel_barang WHERE Kd_Produk = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, idProduk);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    double stok = rs.getDouble("Stok");
                    return stok >= qty; // True jika stok mencukupi
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengecek stok: " + e.getMessage());
        }
        return false; // False jika stok tidak mencukupi atau ada kesalahan
    }
    
    public void updateQty(String idPenjualanrinci, String idObat, double qtyBaru) {
        String getQtySql = "SELECT Qty FROM tabel_transaksidetail WHERE Kd_Trx = ? AND Kd_Produk = ?";
        String updateQtySql =
                "UPDATE tbl_transaksidetail SET Qty = ? WHERE Kd_Trx = ? AND Kd_Produk = ?";
        String updateStokSql = "UPDATE tabel_barang SET Stok = ? WHERE Kd_Produk = ?";
        String getStok = "SELECT Stok FROM tabel_barang WHERE Kd_Produk = ?";

        try {
            double qtyLama = 0;
            try (PreparedStatement stGet = conn.prepareStatement(getQtySql)) {
                stGet.setString(1, idPenjualanrinci);
                stGet.setString(2, idObat);

                try (ResultSet rs = stGet.executeQuery()) {
                    if (rs.next()) {
                        qtyLama = rs.getDouble("Quantity");
                    }
                }
            }
            double stok = 0;
            try (PreparedStatement stStok = conn.prepareStatement(getStok)) {
                stStok.setString(1, idObat);

                try (ResultSet rs = stStok.executeQuery()) {
                    if (rs.next()) {
                        stok = rs.getDouble("Stok");
                    }
                }
            }


            double stokTotal = stok + qtyLama;
            double stokReal;
            if (stokTotal < qtyBaru) {
                System.out.println("Gagal memperbarui stok");
                return;
            } else {
                stokReal = stokTotal - qtyLama;
            }

            try (PreparedStatement stUpdateQty = conn.prepareStatement(updateQtySql)) {
                stUpdateQty.setDouble(1, qtyBaru);
                stUpdateQty.setString(2, idPenjualanrinci);
                stUpdateQty.setString(3, idObat);
                stUpdateQty.executeUpdate();
            }


            try (PreparedStatement stUpdateStok = conn.prepareStatement(updateStokSql)) {
                stUpdateStok.setDouble(1, stokReal);
                stUpdateStok.setString(2, idObat);
                stUpdateStok.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Gagal memperbarui qty: " + e.getMessage());
        }
    }
    
    
}
