/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.dao;
import raven.config.connectionDB;
import raven.model.modelPenjualan;
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
import raven.service.servicePenjualan;
/**
 *
 * @author yusuf
 */
public class penjualanDAO implements servicePenjualan{
    
    private final Connection conn;

    public penjualanDAO() {
        conn = connectionDB.getConnection();
    }
    
    @Override
    public void tambahPenjualan(modelPenjualan model) {
        String sql= "INSERT INTO tabel_transaksipenjualan (Ref, Kasir, Tanggal, Total, Bayar, Kembalian, Diskon, id_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setString(1, model.getRef());
            st.setString(2, model.getKasir());
            st.setString(3, model.getTanggal());
            st.setDouble(4, model.getTotal());
            st.setDouble(5, model.getBayar());
            st.setDouble(6, model.getKembalian());
            st.setDouble(7, model.getDiskon());
            st.setDouble(8, model.getIdUser());
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println("Gagal menambahkan data: " + e.getMessage());
        }
        
    }
    @Override
    public void hapusPenjualan(modelPenjualan model) {
        
    }
    @Override
    public List<modelPenjualan> tampilPenjualan(int id) {
        List<modelPenjualan> listPenjualan = new ArrayList<>();
        
        String sql = "SELECT Ref, Kasir, DATE_FORMAT(Tanggal,'%d-%m-%Y') AS Tanggal, Total, Bayar, Kembalian, Diskon " +
                     "FROM tabel_transaksipenjualan WHERE id_user = ? ORDER BY create_at DESC";
        
        try (PreparedStatement st = conn.prepareStatement(sql) ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                modelPenjualan model = new modelPenjualan();
                model.setRef(rs.getString("Ref"));
                model.setKasir(rs.getString("Kasir"));
                model.setTanggal(rs.getString("Tanggal"));
                model.setTotal(rs.getDouble("Total"));
                model.setBayar(rs.getDouble("Bayar"));
                model.setKembalian(rs.getDouble("Kembalian"));
                model.setDiskon(rs.getDouble("Diskon"));

                listPenjualan.add(model);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data: " + e.getMessage());
        }

        return listPenjualan;
    }
    
    // Tambahkan di penjualanDAO.java
@Override
public List<modelPenjualan> tampilPenjualanByPeriod(int idUser, String period) {
    List<modelPenjualan> listPenjualan = new ArrayList<>();
    String sql = "";
    
    switch(period) {
        case "Hari Ini":
            sql = "SELECT Ref, Kasir, DATE_FORMAT(Tanggal,'%d-%m-%Y') AS Tanggal, Total, Bayar, Kembalian, Diskon " +
                  "FROM tabel_transaksipenjualan WHERE id_user = ? AND Tanggal = CURDATE() ORDER BY create_at DESC";
            break;
        case "Minggu Ini":
            sql = "SELECT Ref, Kasir, DATE_FORMAT(Tanggal,'%d-%m-%Y') AS Tanggal, Total, Bayar, Kembalian, Diskon " +
                  "FROM tabel_transaksipenjualan WHERE id_user = ? AND YEARWEEK(Tanggal, 1) = YEARWEEK(CURDATE(), 1) ORDER BY create_at DESC";
            break;
        case "Bulan Ini":
            sql = "SELECT Ref, Kasir, DATE_FORMAT(Tanggal,'%d-%m-%Y') AS Tanggal, Total, Bayar, Kembalian, Diskon " +
                  "FROM tabel_transaksipenjualan WHERE id_user = ? AND MONTH(Tanggal) = MONTH(CURDATE()) AND YEAR(Tanggal) = YEAR(CURDATE()) ORDER BY create_at DESC";
            break;
        default: // Semua
            sql = "SELECT Ref, Kasir, DATE_FORMAT(Tanggal,'%d-%m-%Y') AS Tanggal, Total, Bayar, Kembalian, Diskon " +
                  "FROM tabel_transaksipenjualan WHERE id_user = ? ORDER BY create_at DESC";
    }
    
    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, idUser);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            modelPenjualan model = new modelPenjualan();
            model.setRef(rs.getString("Ref"));
            model.setKasir(rs.getString("Kasir"));
            model.setTanggal(rs.getString("Tanggal"));
            model.setTotal(rs.getDouble("Total"));
            model.setBayar(rs.getDouble("Bayar"));
            model.setKembalian(rs.getDouble("Kembalian"));
            model.setDiskon(rs.getDouble("Diskon"));

            listPenjualan.add(model);
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data: " + e.getMessage());
    }

    return listPenjualan;
}
    
    @Override
    public List<modelPenjualan> cariData(String keyword) {
        List<modelPenjualan> listObat = new ArrayList<>();
        String sql =
                "SELECT Ref, DATE_FORMAT(Tanggal,'%d-%m-%Y') AS Tanggal, Total, Bayar, Kasir, Kembalian, Diskon FROM tabel_transaksipenjualan WHERE Ref LIKE ? OR Tanggal LIKE ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            // Mengisi parameter dengan keyword untuk pencarian
            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%");

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    modelPenjualan model = new modelPenjualan();
                    model.setRef(rs.getString("Ref"));
                    model.setKasir(rs.getString("Kasir"));
                    model.setTanggal(rs.getString("Tanggal"));
                    model.setTotal(rs.getDouble("Total"));
                    model.setBayar(rs.getDouble("Bayar"));
                    model.setKembalian(rs.getDouble("Kembalian"));
                    model.setDiskon(rs.getDouble("Diskon"));

                    listObat.add(model);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari data: " + e.getMessage());
        }

        return listObat;
    }

    @Override
    public String noTransaksi() {
//        String prefix = "REF" + new SimpleDateFormat("yyyyMMdd").format(new Date());
        String randomDigits = String.format("%04d", (int) (Math.random() * 10000));

        return randomDigits;
    }

    @Override
    public void printNota(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public modelBarang cariBarangByBarcode(String barcode) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            conn = connectionDB.getConnection();
            String sql = "SELECT * FROM tabel_barang WHERE Kd_Produk = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, barcode);
            rs = st.executeQuery();
            
            if (rs.next()) {
                modelBarang barang = new modelBarang();
                barang.setIdProduk(rs.getString("Kd_Produk"));
                barang.setNamaProduk(rs.getString("Nama_Produk"));
                barang.setHargaProduk(rs.getInt("Harga_Jual"));
                barang.setStokProduk(rs.getDouble("Stok"));
                barang.setBarcode(rs.getString("Barcode"));
                return barang;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public List<modelPenjualan> getPenjualanPerHari() {
    List<modelPenjualan> listPenjualan = new ArrayList<>();
    String sql = "SELECT DATE_FORMAT(Tanggal, '%Y-%m-%d') AS Tanggal, SUM(Total) AS TotalHarian " +
                 "FROM tabel_transaksipenjualan " +
                 "GROUP BY DATE_FORMAT(Tanggal, '%Y-%m-%d') " +
                 "ORDER BY Tanggal ASC";

    try (PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        while (rs.next()) {
            modelPenjualan model = new modelPenjualan();
            model.setTanggal(rs.getString("Tanggal"));
            model.setTotal(rs.getDouble("TotalHarian"));
            listPenjualan.add(model);
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data penjualan harian: " + e.getMessage());
    }
    return listPenjualan;
}
    
    public double getPendapatanHariIni() {
    String sql = "SELECT SUM(Total) AS TotalHariIni FROM tabel_transaksipenjualan WHERE Tanggal = CURDATE()";
    try (PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        if (rs.next()) {
            return rs.getDouble("TotalHariIni");
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil pendapatan hari ini: " + e.getMessage());
    }
    return 0.0;
}
    public int getTotalJumlahTransaksi() {
    String sql = "SELECT COUNT(*) AS TotalTransaksi FROM tabel_transaksipenjualan";
    try (PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        if (rs.next()) {
            return rs.getInt("TotalTransaksi");
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil total transaksi: " + e.getMessage());
    }
    return 0;
}
    public double getTotalPendapatan() {
    String sql = "SELECT SUM(Total) AS TotalPendapatan FROM tabel_transaksipenjualan";
    try (PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        if (rs.next()) {
            return rs.getDouble("TotalPendapatan");
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil total pendapatan: " + e.getMessage());
    }
    return 0.0;
}
    public static String generateKodeTransaksi(Connection conn) throws SQLException {
    String prefix = "TRX";
    String tanggal = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
    String sql = "SELECT COUNT(*) FROM penjualan WHERE kd_trx LIKE ?";
    
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, prefix + tanggal + "%");
    ResultSet rs = ps.executeQuery();
    
    int count = 0;
    if (rs.next()) {
        count = rs.getInt(1) + 1;
    }

    String nomorUrut = String.format("%04d", count);
    return prefix + tanggal + nomorUrut;
}
    public List<modelPenjualan> getPenjualanByPeriod(String period) {
    List<modelPenjualan> listPenjualan = new ArrayList<>();
    String sql = "";
    
    switch(period) {
        case "7 Hari Terakhir":
            sql = "SELECT DATE_FORMAT(Tanggal, '%Y-%m-%d') AS Tanggal, SUM(Total) AS TotalHarian " +
                  "FROM tabel_transaksipenjualan " +
                  "WHERE Tanggal >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
                  "GROUP BY DATE_FORMAT(Tanggal, '%Y-%m-%d') " +
                  "ORDER BY Tanggal ASC";
            break;
        case "1 Bulan Terakhir":
            sql = "SELECT DATE_FORMAT(Tanggal, '%Y-%m-%d') AS Tanggal, SUM(Total) AS TotalHarian " +
                  "FROM tabel_transaksipenjualan " +
                  "WHERE Tanggal >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) " +
                  "GROUP BY DATE_FORMAT(Tanggal, '%Y-%m-%d') " +
                  "ORDER BY Tanggal ASC";
            break;
        case "1 Tahun Terakhir":
            sql = "SELECT DATE_FORMAT(Tanggal, '%Y-%m') AS Tanggal, SUM(Total) AS TotalHarian " +
                  "FROM tabel_transaksipenjualan " +
                  "WHERE Tanggal >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) " +
                  "GROUP BY DATE_FORMAT(Tanggal, '%Y-%m') " +
                  "ORDER BY Tanggal ASC";
            break;
        default: // Default 7 Hari Terakhir
            sql = "SELECT DATE_FORMAT(Tanggal, '%Y-%m-%d') AS Tanggal, SUM(Total) AS TotalHarian " +
                  "FROM tabel_transaksipenjualan " +
                  "WHERE Tanggal >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
                  "GROUP BY DATE_FORMAT(Tanggal, '%Y-%m-%d') " +
                  "ORDER BY Tanggal ASC";
    }
    
    try (PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        while (rs.next()) {
            modelPenjualan model = new modelPenjualan();
            model.setTanggal(rs.getString("Tanggal"));
            model.setTotal(rs.getDouble("TotalHarian"));
            listPenjualan.add(model);
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data penjualan: " + e.getMessage());
    }
    return listPenjualan;
}
    @Override
    public List<modelBarang> getTransaksiDetailByRef(String ref) {
         List<modelBarang> list = new ArrayList<>();
    String sql = "SELECT td.Kd_Produk, td.Nama_Produk, td.Harga_Satuan, td.Quantity " +
                 "FROM tabel_transaksidetail td " +
                 "WHERE td.Ref = ?";
    
    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setString(1, ref);
        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {
            modelBarang barang = new modelBarang();
            barang.setIdProduk(rs.getString("Kd_Produk"));
            barang.setNamaProduk(rs.getString("Nama_Produk"));
            double harga = rs.getDouble("Harga_Satuan");
            barang.setHargaProduk(rs.getInt("Harga_Satuan"));
            barang.setStokProduk(rs.getInt("Quantity")); // Quantity dari transaksi
            list.add(barang);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
    }
    @Override
public List<modelPenjualan> tampilPenjualanWithPagination(int idUser, int page, int rowsPerPage) {
    List<modelPenjualan> listPenjualan = new ArrayList<>();
    
    String sql = "SELECT Ref, Kasir, DATE_FORMAT(Tanggal,'%d-%m-%Y') AS Tanggal, Total, Bayar, Kembalian, Diskon " +
                 "FROM tabel_transaksipenjualan WHERE id_user = ? ORDER BY create_at DESC LIMIT ? OFFSET ?";
    
    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, idUser);
        st.setInt(2, rowsPerPage);
        st.setInt(3, (page - 1) * rowsPerPage);
        
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            modelPenjualan model = new modelPenjualan();
            model.setRef(rs.getString("Ref"));
            model.setKasir(rs.getString("Kasir"));
            model.setTanggal(rs.getString("Tanggal"));
            model.setTotal(rs.getDouble("Total"));
            model.setBayar(rs.getDouble("Bayar"));
            model.setKembalian(rs.getDouble("Kembalian"));
            model.setDiskon(rs.getDouble("Diskon"));

            listPenjualan.add(model);
        }
    } catch (SQLException e) {
        System.out.println("Gagal mengambil data: " + e.getMessage());
    }

    return listPenjualan;
}

@Override
public int getTotalPenjualanCount(int idUser) {
    String sql = "SELECT COUNT(*) as total FROM tabel_transaksipenjualan WHERE id_user = ?";
    int count = 0;

    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, idUser);
        ResultSet rs = st.executeQuery();
        
        if (rs.next()) {
            count = rs.getInt("total");
        }
    } catch (SQLException e) {
        System.out.println("Gagal mendapatkan jumlah data: " + e.getMessage());
    }
    return count;
}
}
