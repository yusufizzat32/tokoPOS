/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.dao;
import raven.config.connectionDB;
import raven.model.modelPenjualanDetail;
import raven.model.modelRekapTransaksi;
import raven.model.modelBarang;
import raven.service.serviceRekapTransaksi;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author yusuf
 */
public class rekapTransaksiDAO implements serviceRekapTransaksi{
    private Connection conn;

    public rekapTransaksiDAO() {
        conn = connectionDB.getConnection();
    }
    
    @Override
    public void tambahData(modelRekapTransaksi model) {
        if (!cekStok(model.getModelBarang().getIdProduk(), model.getModelPendet().getQty())) {
            JOptionPane.showMessageDialog(null, "Stok tidak mencukupi untuk obat ID: " + model.getModelBarang().getIdProduk());
            throw new IllegalArgumentException("Stok tidak mencukupi untuk obat ID: " + model.getModelBarang().getIdProduk());
        }
        
        String sql = "INSERT INTO tabel_rekaptransaksi (Kd_Produk, Barcode, Nama_Produk, Harga_Produk, Quantity, Subtotal,Stok) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model.getModelBarang().getIdProduk());  // Kode Obat
            stmt.setString(2, model.getModelBarang().getBarcode());  // Kode Obat
            stmt.setString(3, model.getModelBarang().getNamaProduk()); // Nama Obat
            stmt.setDouble(4, model.getModelBarang().getHargaProduk());  // Harga Obat
            stmt.setDouble(5, model.getModelPendet().getQty());  // Kuantitas
            stmt.setDouble(6, model.getModelPendet().getNilai()); // Subtotal (Harga * Qty)
            stmt.setDouble(7, model.getModelBarang().getStokProduk());
            stmt.executeUpdate();
//            updateStok(model.getModelObat().getIdObat(), model.getModelPendet().getQty());
        } catch (SQLException e) {
            System.out.println("Error while adding data: " + e.getMessage());
        }
    }
    
     public void updateStok(String idProduk, int qty) {
        String sql = "UPDATE tabel_barang SET Stok = Stok - ? WHERE Kd_Produk = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDouble(1, qty);
            st.setString(2, idProduk);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui stok: " + e.getMessage());
        }
    }
     public boolean cekStok(String idProduk, int qty) {
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

    @Override
    public void updateData(modelRekapTransaksi model) {
        String sql = "UPDATE tabel_rekaptransaksi SET Nama_Produk = ?, Harga_Produk = ?, Quantity = ?, Subtotal = ? WHERE Kd_Produk = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model.getModelBarang().getNamaProduk()); // Nama Obat
            stmt.setDouble(2, model.getModelBarang().getHargaProduk()); // Harga Obat
            stmt.setDouble(3, model.getModelPendet().getQty());  // Kuantitas
            stmt.setDouble(4, model.getModelPendet().getNilai()); // Subtotal
            stmt.setString(5, model.getModelBarang().getIdProduk());  // Kode Obat
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating data: " + e.getMessage());
        }
    }

    @Override
    public void hapusData(modelRekapTransaksi model) {
        String sql = "DELETE FROM tabel_rekaptransaksi WHERE Kd_Produk = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model.getModelBarang().getIdProduk());  // Kode Obat
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting data: " + e.getMessage());
        }
    }

    @Override
    public void updateQty(String idProduk, int qtyBaru) {
        String getQtySql = "SELECT Quantity FROM tabel_rekaptransaksi WHERE Kd_Produk = ?";
        String updateQtySql =
                "UPDATE tabel_rekaptransaksi SET Stok = ?, Quantity = ? WHERE Kd_Produk = ?";
        String updateStokSql = "UPDATE tabel_barang SET Stok = ? WHERE Kd_Produk = ?";
        String getStok = "SELECT Stok FROM tabel_rekaptransaksi WHERE Kd_Produk = ?";

        try {
            double qtyLama = 0;
            try (PreparedStatement stGet = conn.prepareStatement(getQtySql)) {
                stGet.setString(1, idProduk);

                try (ResultSet rs = stGet.executeQuery()) {
                    if (rs.next()) {
                        qtyLama = rs.getInt("Quantity");
                    }
                }
            }
            double stok = 0;
            try (PreparedStatement stStok = conn.prepareStatement(getStok)) {
                stStok.setString(1, idProduk);

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
                stokReal = stokTotal - qtyBaru;
            }

            try (PreparedStatement stUpdateQty = conn.prepareStatement(updateQtySql)) {
                stUpdateQty.setDouble(1, stokReal);
                stUpdateQty.setDouble(2, qtyBaru);
                stUpdateQty.setString(3, idProduk);
                stUpdateQty.executeUpdate();
            }


            try (PreparedStatement stUpdateStok = conn.prepareStatement(updateStokSql)) {
                stUpdateStok.setDouble(1, stokReal);
                stUpdateStok.setString(2, idProduk);
                stUpdateStok.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Gagal memperbarui qty: " + e.getMessage());
        }
    }

    @Override
    public List<modelRekapTransaksi> tampilData() {
        List<modelRekapTransaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_rekaptransaksi";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                modelRekapTransaksi model = new modelRekapTransaksi();
                modelPenjualanDetail detail = new modelPenjualanDetail();
                modelBarang obat = new modelBarang();
                
                obat.setIdProduk(rs.getString("Kd_Produk"));
                obat.setBarcode(rs.getString("Barcode"));
                obat.setNamaProduk(rs.getString("Nama_Produk"));
                obat.setHargaProduk(rs.getInt("Harga_Produk"));
                obat.setStokProduk(rs.getInt("Stok"));
                
                detail.setQty(rs.getInt("Quantity"));
                detail.setNilai(rs.getInt("Subtotal"));
                
                
                model.setModelBarang(obat);
                model.setModelPendet(detail);
                
                list.add(model);
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching data: " + e.getMessage());
        }
        
        return list;
    }
    
}
