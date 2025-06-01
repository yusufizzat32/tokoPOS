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
    String sql = "INSERT INTO tabel_produkmasuk (Kd_produkmasuk, Nama_Produk, Kd_Produk, Harga_Satuan, Jumlah, Jumlah_Masuk, Nilai, tanggal_kadaluarsa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        st = conn.prepareStatement(sql);
        st.setInt(1, model.getIdProdukMasuk());
        st.setString(2, model.getNamaProduk());
        st.setString(3, model.getIdProduk());
        st.setDouble(4, model.getHargaProduk());
        st.setDouble(5, model.getStokProduk());
        st.setDouble(6, model.getJumlahMasuk());
        st.setDouble(7, model.getNilaiProduk());
        
        // Set tanggal kadaluarsa
        java.sql.Date tanggalKadaluarsa = new java.sql.Date(model.getTanggalKadaluarsa().getTime());
        st.setDate(8, tanggalKadaluarsa);
        
        barang.updateStok(model.getIdProduk(), model.getJumlahMasuk());
        
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
        PreparedStatement st = null;
        String sql = "UPDATE tabel_produkmasuk SET Nama_Produk = ?, Kd_Produk=?, Harga_Produk = ?, Jumlah = ?,Jumlah_Masuk = ?, Nilai = ?, tanggal_kadaluarsa = ? WHERE Kd_Produkmasuk = ?";

        try {
            st = conn.prepareStatement(sql);
            
            st.setString(1, model.getNamaProduk());
            st.setString(2, model.getIdProduk());
            st.setDouble(3, model.getHargaProduk());
            st.setDouble(4, model.getStokProduk());
            st.setDouble(5, model.getJumlahMasuk());
            st.setDouble(6, model.getNilaiProduk());
            st.setInt(7, model.getIdProdukMasuk());
            java.sql.Date tanggalKadaluarsa = new java.sql.Date(model.getTanggalKadaluarsa().getTime());
        st.setDate(8, tanggalKadaluarsa);

            st.executeUpdate();
            System.out.println("Data berhasil diperbarui.");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui data: " + e.getMessage());
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.out. println(e);
                }
            }
        }
    }

    @Override
    public void hapusData(modelBarangMasuk model) {
       PreparedStatement st = null;
        String sql = "DELETE FROM tabel_produkmasuk WHERE Kd_Produkmasuk = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, model.getIdProdukMasuk()); // Kondisi berdasarkan Kd_Obat

            
            st.executeUpdate();
            System.out.println("Data berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
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
public List<modelBarangMasuk> showData() {
    List<modelBarangMasuk> listBarang = new ArrayList<>();
    PreparedStatement st = null;
    ResultSet rs = null;
    String sql = "SELECT *, DATE_FORMAT(tanggal_kadaluarsa, '%Y-%m-%d') as formatted_kadaluarsa FROM tabel_produkmasuk";

    try {
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();

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
            
            // Ambil tanggal kadaluarsa yang sudah diformat
            barang.setTanggalKadaluarsaFormatted(rs.getString("formatted_kadaluarsa"));

            listBarang.add(barang);
        }
    } catch (SQLException e) {
        System.out.println("Gagal menampilkan data: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
    return listBarang;
}

    @Override
    public List<modelBarangMasuk> searchData(String id) {
          List<modelBarangMasuk> listBarang = new ArrayList<>();
    PreparedStatement st = null;
    ResultSet rs = null;
    
    // Mencari berdasarkan Kd_Produk atau Nama_Produk yang mengandung keyword
    String sql = "SELECT * FROM tabel_produkmasuk WHERE Kd_Produk LIKE ? OR Nama_Produk LIKE ?";
    
    try {
        st = conn.prepareStatement(sql);
        st.setString(1, "%" + id + "%");
        st.setString(2, "%" + id + "%");
        
        rs = st.executeQuery();
        
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
    } catch (SQLException e) {
        System.out.println("Gagal melakukan pencarian: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    return listBarang;
    }

    @Override
    public List<modelBarangMasuk> showDataByPeriod(String period) {
        List<modelBarangMasuk> listBarang = new ArrayList<>();
    String sql = "";
    
    switch(period) {
        case "Hari Ini":
            sql = "SELECT * FROM tabel_produkmasuk WHERE DATE(Tanggal_masuk) = CURDATE()";
            break;
        case "Minggu Ini":
            sql = "SELECT * FROM tabel_produkmasuk WHERE YEARWEEK(Tanggal_masuk, 1) = YEARWEEK(CURDATE(), 1)";
            break;
        case "Bulan Ini":
            sql = "SELECT * FROM tabel_produkmasuk WHERE MONTH(Tanggal_masuk) = MONTH(CURDATE()) AND YEAR(Tanggal_masuk) = YEAR(CURDATE())";
            break;
        default: // Semua
            sql = "SELECT * FROM tabel_produkmasuk";
    }
    
    try (PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
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
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data: " + e.getMessage());
    }
    return listBarang;
    }
    // Tambahkan method ini di barangMasukDAO.java
@Override
public List<modelBarangMasuk> showDataWithPagination(int page, int rowsPerPage) {
    List<modelBarangMasuk> listBarang = new ArrayList<>();
    PreparedStatement st = null;
    ResultSet rs = null;
    String sql = "SELECT *, DATE_FORMAT(tanggal_kadaluarsa, '%Y-%m-%d') as formatted_kadaluarsa FROM tabel_produkmasuk LIMIT ? OFFSET ?";

    try {
        st = conn.prepareStatement(sql);
        st.setInt(1, rowsPerPage);
        st.setInt(2, (page - 1) * rowsPerPage);
        
        rs = st.executeQuery();

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
            barang.setTanggalKadaluarsaFormatted(rs.getString("formatted_kadaluarsa"));
            listBarang.add(barang);
        }
    } catch (SQLException e) {
        System.out.println("Gagal menampilkan data: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
    return listBarang;
}

@Override
public int getTotalDataCount() {
    PreparedStatement st = null;
    ResultSet rs = null;
    String sql = "SELECT COUNT(*) as total FROM tabel_produkmasuk";
    int count = 0;

    try {
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();
        
        if (rs.next()) {
            count = rs.getInt("total");
        }
    } catch (SQLException e) {
        System.out.println("Gagal mendapatkan jumlah data: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    return count;
}
   
    
}
