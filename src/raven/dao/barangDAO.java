    
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
    String sql = "INSERT INTO tabel_barang (Kd_Produk, Nama_Produk, Stok, Harga_Jual, Harga_Beli, Barcode, id_kategori) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setString(1, model.getIdProduk());
        st.setString(2, model.getNamaProduk());
        st.setDouble(3, model.getStokProduk());
        st.setDouble(4, model.getHargaProduk());
        st.setDouble(5, model.getHargaBeli());
        st.setString(6, model.getBarcode());
        st.setInt(7, model.getIdKategori());
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
        String sql = "UPDATE tabel_barang SET Nama_Produk = ?, Harga_Jual = ?, Harga_Beli = ?, Stok = ?, Barcode = ?, id_kategori = ? WHERE Kd_Produk = ?";

        st = conn.prepareStatement(sql);
        st.setString(1, model.getNamaProduk());
        st.setDouble(2, model.getHargaProduk());
        st.setDouble(3, model.getHargaBeli());
        st.setDouble(4, model.getStokProduk());
        st.setString(5, model.getBarcode());
        st.setInt(6, model.getIdKategori());
        st.setString(7, model.getIdProduk());

        st.executeUpdate();
        st.close();
    } catch (SQLException e){
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
    String sql = "SELECT b.*, k.nama_kategori FROM tabel_barang b LEFT JOIN kategori k ON b.id_kategori = k.id_kategori";

    try {
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();

        while (rs.next()) {
            modelBarang barang = new modelBarang();
            barang.setIdProduk(rs.getString("Kd_Produk"));
            barang.setNamaProduk(rs.getString("Nama_Produk"));
            barang.setHargaProduk(rs.getInt("Harga_Jual"));
            barang.setHargaBeli(rs.getInt("Harga_Beli"));
            barang.setStokProduk(rs.getInt("Stok"));
            barang.setBarcode(rs.getString("barcode"));
            barang.setIdKategori(rs.getInt("id_kategori"));
            barang.setNamaKategori(rs.getString("nama_kategori"));
            
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
        String sql = "SELECT b.*, k.nama_kategori FROM tabel_barang b " +
                     "LEFT JOIN kategori k ON b.id_kategori = k.id_kategori " +
                     "WHERE b.Nama_Produk LIKE ? OR b.Kd_Produk LIKE ? OR b.Barcode LIKE ?";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%");
            st.setString(3, "%" + keyword + "%");
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    modelBarang barang = new modelBarang();
                    barang.setIdProduk(rs.getString("Kd_Produk"));
                    barang.setNamaProduk(rs.getString("Nama_Produk"));
                    barang.setHargaProduk(rs.getInt("Harga_Jual"));
                    barang.setHargaBeli(rs.getInt("Harga_Beli"));
                    barang.setStokProduk(rs.getDouble("Stok"));
                    barang.setBarcode(rs.getString("barcode"));
                    barang.setIdKategori(rs.getInt("id_kategori"));
                    barang.setNamaKategori(rs.getString("nama_kategori"));
                    listBarang.add(barang);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari data: " + e.getMessage());
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
public modelBarang cariBarangByBarcode(String barcode) {
    if (barcode == null || barcode.trim().isEmpty()) {
        return null;
    }

    PreparedStatement st = null;
    ResultSet rs = null;
    // Gunakan LIKE dengan wildcard dan trim() untuk menghindari masalah whitespace
    String sql = "SELECT b.*, k.nama_kategori FROM tabel_barang b " +
                 "LEFT JOIN kategori k ON b.id_kategori = k.id_kategori " +
                 "WHERE TRIM(b.Barcode) = ?";
    
    try {
        st = conn.prepareStatement(sql);
        st.setString(1, barcode.trim()); // Gunakan trim() untuk input
        
        System.out.println("Executing query: " + st.toString()); // Debug query
        
        rs = st.executeQuery();

        if (rs.next()) {
            modelBarang barang = new modelBarang();
            barang.setIdProduk(rs.getString("Kd_Produk"));
            barang.setNamaProduk(rs.getString("Nama_Produk"));
            barang.setHargaProduk(rs.getInt("Harga_Jual"));
            barang.setHargaBeli(rs.getInt("Harga_Beli")); // Tambahkan ini
            barang.setStokProduk(rs.getDouble("Stok"));
            barang.setBarcode(rs.getString("Barcode"));
            barang.setIdKategori(rs.getInt("id_kategori")); // Tambahkan ini
            barang.setNamaKategori(rs.getString("nama_kategori")); // Tambahkan ini
            
            System.out.println("Barang ditemukan: " + barang.getNamaProduk()); // Debug
            return barang;
        } else {
            System.out.println("Barang dengan barcode '" + barcode + "' tidak ditemukan"); // Debug
        }
    } catch (SQLException e) {
        System.err.println("Error saat mencari barcode: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            System.err.println("Error saat menutup resources: " + e.getMessage());
        }
    }
    return null;
}

    @Override
    public List<String> getKategoriList() {
        List<String> listKategori = new ArrayList<>();
        String sql = "SELECT nama_kategori FROM kategori";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                listKategori.add(rs.getString("nama_kategori"));
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data kategori: " + e.getMessage());
        }
        return listKategori;
    }

    @Override
    public List<modelBarang> searchDataByKategori(String keyword, String kategori) {
        List<modelBarang> listBarang = new ArrayList<>();
        String sql = "SELECT b.*, k.nama_kategori FROM tabel_barang b " +
                     "LEFT JOIN kategori k ON b.id_kategori = k.id_kategori " +
                     "WHERE (b.Nama_Produk LIKE ? OR b.Kd_Produk LIKE ?) " +
                     "AND k.nama_kategori = ?";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%");
            st.setString(3, kategori);
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    modelBarang barang = new modelBarang();
                    barang.setIdProduk(rs.getString("Kd_Produk"));
                    barang.setNamaProduk(rs.getString("Nama_Produk"));
                    barang.setHargaProduk(rs.getInt("Harga_Jual"));
                    barang.setHargaBeli(rs.getInt("Harga_Beli"));
                    barang.setStokProduk(rs.getDouble("Stok"));
                    barang.setBarcode(rs.getString("barcode"));
                    barang.setIdKategori(rs.getInt("id_kategori"));
                    barang.setNamaKategori(rs.getString("nama_kategori"));
                    listBarang.add(barang);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari data: " + e.getMessage());
        }
        return listBarang;
    }
    // Contoh implementasi di barangDAO

    @Override
public List<modelBarang> getDataWithPagination(int offset, int limit) {
    List<modelBarang> list = new ArrayList<>();
    String sql = "SELECT b.*, k.nama_kategori FROM tabel_barang b " +
                 "LEFT JOIN kategori k ON b.id_kategori = k.id_kategori " +
                 "LIMIT ? OFFSET ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, limit);
        ps.setInt(2, offset);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            modelBarang barang = new modelBarang();
            barang.setIdProduk(rs.getString("Kd_Produk"));
            barang.setNamaProduk(rs.getString("Nama_Produk"));
            barang.setHargaProduk(rs.getInt("Harga_Jual"));
            barang.setHargaBeli(rs.getInt("Harga_Beli"));
            barang.setStokProduk(rs.getDouble("Stok"));
            barang.setBarcode(rs.getString("Barcode"));
            barang.setNamaKategori(rs.getString("nama_kategori"));
            list.add(barang);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

@Override
public int getTotalRowCount() {
    String sql = "SELECT COUNT(*) FROM tabel_barang";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

@Override
public List<modelBarang> searchDataWithPagination(String keyword, int offset, int limit) {
    List<modelBarang> list = new ArrayList<>();
    String sql = "SELECT b.*, k.nama_kategori FROM tabel_barang b " +
                 "LEFT JOIN kategori k ON b.id_kategori = k.id_kategori " +
                 "WHERE b.Nama_Produk LIKE ? OR b.Kd_Produk LIKE ? OR b.Barcode LIKE ? " +
                 "LIMIT ? OFFSET ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        String searchPattern = "%" + keyword + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ps.setString(3, searchPattern);
        ps.setInt(4, limit);
        ps.setInt(5, offset);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            modelBarang barang = new modelBarang();
            barang.setIdProduk(rs.getString("Kd_Produk"));
            barang.setNamaProduk(rs.getString("Nama_Produk"));
            barang.setHargaProduk(rs.getInt("Harga_Jual"));
            barang.setHargaBeli(rs.getInt("Harga_Beli"));
            barang.setStokProduk(rs.getDouble("Stok"));
            barang.setBarcode(rs.getString("Barcode"));
            barang.setNamaKategori(rs.getString("nama_kategori"));
            list.add(barang);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

@Override
public int getSearchRowCount(String keyword) {
    String sql = "SELECT COUNT(*) FROM tabel_barang " +
                 "WHERE Nama_Produk LIKE ? OR Kd_Produk LIKE ? OR Barcode LIKE ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        String searchPattern = "%" + keyword + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ps.setString(3, searchPattern);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

@Override
public List<modelBarang> searchDataByKategoriWithPagination(String keyword, String kategori, int offset, int limit) {
    List<modelBarang> list = new ArrayList<>();
    String sql = "SELECT b.*, k.nama_kategori FROM tabel_barang b " +
                 "JOIN kategori k ON b.id_kategori = k.id_kategori " +
                 "WHERE (b.Nama_Produk LIKE ? OR b.Kd_Produk LIKE ? OR b.Barcode LIKE ?) " +
                 "AND k.nama_kategori = ? " +
                 "LIMIT ? OFFSET ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        String searchPattern = "%" + keyword + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ps.setString(3, searchPattern);
        ps.setString(4, kategori);
        ps.setInt(5, limit);
        ps.setInt(6, offset);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            modelBarang barang = new modelBarang();
            barang.setIdProduk(rs.getString("Kd_Produk"));
            barang.setNamaProduk(rs.getString("Nama_Produk"));
            barang.setHargaProduk(rs.getInt("Harga_Jual"));
            barang.setHargaBeli(rs.getInt("Harga_Beli"));
            barang.setStokProduk(rs.getDouble("Stok"));
            barang.setBarcode(rs.getString("Barcode"));
            barang.setNamaKategori(rs.getString("nama_kategori"));
            list.add(barang);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

@Override
public int getSearchByKategoriRowCount(String keyword, String kategori) {
    String sql = "SELECT COUNT(*) FROM tabel_barang b " +
                 "JOIN kategori k ON b.id_kategori = k.id_kategori " +
                 "WHERE (b.Nama_Produk LIKE ? OR b.Kd_Produk LIKE ? OR b.Barcode LIKE ?) " +
                 "AND k.nama_kategori = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        String searchPattern = "%" + keyword + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ps.setString(3, searchPattern);
        ps.setString(4, kategori);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}
@Override
public String generateProductId() {
    String sql = "SELECT MAX(Kd_Produk) FROM tabel_barang WHERE Kd_Produk LIKE 'PRD%'";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            String lastId = rs.getString(1);
            if (lastId != null) {
                // Ambil angka terakhir dan increment
                int num = Integer.parseInt(lastId.substring(3)) + 1;
                return String.format("PRD%03d", num);
            }
        }
        // Jika belum ada data, mulai dari PRD001
        return "PRD001";
    } catch (SQLException e) {
        e.printStackTrace();
        return "PRD001"; // Fallback
    } catch (NumberFormatException e) {
        e.printStackTrace();
        return "PRD001"; // Fallback jika format tidak sesuai
    }
}
@Override
public modelBarang getByKode(String kodeProduk) {
    PreparedStatement st = null;
    ResultSet rs = null;
    String sql = "SELECT b.*, k.nama_kategori FROM tabel_barang b " +
                 "LEFT JOIN kategori k ON b.id_kategori = k.id_kategori " +
                 "WHERE b.Kd_Produk = ?";
    
    try {
        st = conn.prepareStatement(sql);
        st.setString(1, kodeProduk);
        rs = st.executeQuery();

        if (rs.next()) {
            modelBarang barang = new modelBarang();
            barang.setIdProduk(rs.getString("Kd_Produk"));
            barang.setNamaProduk(rs.getString("Nama_Produk"));
            barang.setHargaProduk(rs.getInt("Harga_Jual"));
            barang.setHargaBeli(rs.getInt("Harga_Beli"));
            barang.setStokProduk(rs.getDouble("Stok"));
            barang.setBarcode(rs.getString("Barcode"));
            barang.setIdKategori(rs.getInt("id_kategori"));
            barang.setNamaKategori(rs.getString("nama_kategori"));
            return barang;
        }
    } catch (SQLException e) {
        System.out.println("Gagal mendapatkan data barang: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    return null;
}




}
