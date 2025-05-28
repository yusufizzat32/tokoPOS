    
package raven.dao;

import raven.config.connectionDB;
import raven.model.modelBarang;
import raven.service.serviceBarang;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author yusuf
 */
 public class barangDAO implements serviceBarang{
    private Connection conn;

    public barangDAO() {
        conn = connectionDB.getConnection();
    }

    @Override
    public void tambahData(modelBarang model) {
         String sql = "INSERT INTO tabel_barang (Kd_Produk, Nama_Produk, Stok, Harga_Jual, Barcode) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, model.getIdProduk());
            st.setString(2, model.getNamaProduk());
            st.setDouble(3, model.getStokProduk());
            st.setDouble(4, model.getHargaProduk());
            st.setString(5, model.getBarcode());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
        }
    }

    @Override
    public void updateData(modelBarang model) {
        System.out.println("update");
        PreparedStatement st = null;
        try {
            String sql = "UPDATE tabel_barang SET Nama_Produk = ?, Harga_Jual = ?, Stok = ?, Barcode = ? WHERE Kd_Produk = ?";

            st = conn.prepareStatement(sql);
            st.setString(1, model.getNamaProduk());
            st.setDouble(2, model.getHargaProduk());
            st.setDouble(3, model.getStokProduk());
            st.setString(4, model.getIdProduk()); // Kondisi berdasarkan Kd_Obat
            st.setString(5, model.getBarcode());

            st.executeUpdate();
            st.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(modelBarang model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM tabel_barang WHERE Kd_Produk = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getIdProduk()); // Kondisi berdasarkan Kd_Obat

            
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
    public void updateStok(String id, double stok) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String selectSql = "SELECT Stok FROM tabel_barang WHERE Kd_Produk = ?";
        String updateSql = "UPDATE tabel_barang SET Stok = ? WHERE Kd_Produk = ?";

        try {

            st = conn.prepareStatement(selectSql);
            st.setString(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                double stokLama = rs.getDouble("Stok");
                double stokBaru = stokLama + stok;

                st = conn.prepareStatement(updateSql);
                st.setDouble(1, stokBaru);
                st.setString(2, id);
                st.executeUpdate();

                System.out.println("Stok berhasil diperbarui.");
            } else {
                System.out.println("Data produk dengan Kd_produk " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui stok: " + e.getMessage());
        }
    }

    @Override
    public List<modelBarang> showData() {
        List listBarang = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tabel_barang";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                modelBarang barang = new modelBarang();
                barang.setIdProduk(rs.getString("Kd_Produk"));
                barang.setNamaProduk(rs.getString("Nama_Produk"));
                barang.setHargaProduk(rs.getInt("Harga_Jual"));
                barang.setStokProduk(rs.getInt("Stok"));
                barang.setBarcode(rs.getString("barcode"));
         
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
    public List<modelBarang> searchData(String keyword) {
        List<modelBarang> listBarang = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM tabel_barang WHERE Nama_Produk LIKE ? OR Kd_Produk LIKE ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%");
            st.setString(3, "%" + keyword + "%");

            // Memeriksa apakah keyword bisa menjadi angka untuk stok atau harga
            try {
                int stokKeyword = Integer.parseInt(keyword);
                // Jika keyword berupa angka, tambahkan pencarian stok
                sql += " OR Stok = ?";
                st = conn.prepareStatement(sql);
                st.setString(1, "%" + keyword + "%");
                st.setString(2, "%" + keyword + "%");
                st.setString(3, "%" + keyword + "%");
                st.setInt(4, stokKeyword);
            } catch (NumberFormatException e) {
                // Jika bukan angka, lanjutkan tanpa stok
            }

            // Cek apakah keyword bisa menjadi harga (Long) untuk pencarian Harga_Jual
            try {
                long hargaKeyword = Long.parseLong(keyword);
                sql += " OR Harga_Jual = ?";
                st = conn.prepareStatement(sql);
                st.setString(1, "%" + keyword + "%");
                st.setString(2, "%" + keyword + "%");
                st.setString(3, "%" + keyword + "%");
                st.setLong(4, hargaKeyword);
            } catch (NumberFormatException e) {
                // Jika bukan angka, lanjutkan tanpa harga
            }

            rs = st.executeQuery();

            while (rs.next()) {
                modelBarang obat = new modelBarang();
                obat.setIdProduk(rs.getString("Kd_Produk"));
                obat.setNamaProduk(rs.getString("Nama_Produk"));
                obat.setHargaProduk(rs.getInt("Harga_Jual"));
                obat.setStokProduk(rs.getDouble("Stok"));
                obat.setBarcode(rs.getString("barcode"));
                listBarang.add(obat);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari data: " + e);
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (st != null) try { st.close(); } catch (SQLException e) {}
        }

        return listBarang;
    }

    @Override
    public List<modelBarang> ambilNamaBarang() {
        List listBarang = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT Kd_Produk, Nama_Produk FROM tabel_barang";
    

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                modelBarang obat = new modelBarang();
                obat.setIdProduk(rs.getString("Kd_Produk"));
                obat.setNamaProduk(rs.getString("Nama_Produk"));
                listBarang.add(obat);
            }
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data: " + e);
        } 
        return listBarang;
    }

    @Override
    public String ambilNamaBarangId(int id) {
         String namaObat = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT Nama_Produk FROM tabel_barang WHERE Kd_Produk=?";
        
        try{
            st = conn.prepareStatement(sql);
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()){
                namaObat = rs.getString("Nama_Produk");
                
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return namaObat; 
    }

    @Override
public modelBarang cariBarangByBarcode(String id) {
    PreparedStatement st = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM tabel_barang WHERE Barcode = ?";

    try {
        st = conn.prepareStatement(sql);
        st.setString(1, id);
        rs = st.executeQuery();

        if (rs.next()) {
            modelBarang obat = new modelBarang();
            obat.setIdProduk(rs.getString("Kd_Produk"));
            obat.setNamaProduk(rs.getString("Nama_Produk"));
            obat.setHargaProduk(rs.getInt("Harga_Jual"));
            obat.setStokProduk(rs.getDouble("Stok"));
            obat.setBarcode(rs.getString("Barcode"));
            return obat;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return null;
}

    @Override
    public void insertData(modelBarang model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}