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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import raven.model.modelReturBarang;
import raven.service.serviceRetur;
/**
 *
 * @author yusuf
 */
 public class returDAO implements serviceRetur{
    private Connection conn;

    public returDAO() {
        conn = connectionDB.getConnection();
    }

public int insertReturAndGetId(modelReturBarang retur) {
    String sql = "INSERT INTO tabel_retur (Ref, alasan_retur, total_pengembalian, status_retur) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, retur.getRef());
        stmt.setString(2, retur.getAlasanRetur());
        stmt.setInt(3, retur.getTotalPengembalian());
        stmt.setString(4, retur.getStatusRetur());
        stmt.executeUpdate();
        
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(returDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return -1;
}

public void insertDetailRetur(int idRetur, String kdProduk, int quantity, int hargaSatuan, String alasan) {
    String sql = "INSERT INTO tabel_detailretur (id_retur, Kd_Produk, Quantity, Harga_Satuan, alasan, Subtotal) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idRetur);
        stmt.setString(2, kdProduk);
        stmt.setInt(3, quantity);
        stmt.setInt(4, hargaSatuan);
        stmt.setString(5, alasan);
        stmt.setInt(6, quantity * hargaSatuan);
        stmt.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(returDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    @Override
    public void insertRetur(modelReturBarang retur) {
        String sql = "INSERT INTO tabel_retur (Ref,  alasan_retur, total_pengembalian, status_retur) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, retur.getRef());
            stmt.setString(2, retur.getAlasanRetur());
            stmt.setInt(3, retur.getTotalPengembalian());
            stmt.setString(4, retur.getStatusRetur());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(returDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateRetur(modelReturBarang retur) {
        String sql = "UPDATE tabel_retur SET Ref=?, alasan_retur=?, total_pengembalian=?, status_retur=? WHERE id_retur=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, retur.getRef());
            stmt.setString(2, retur.getAlasanRetur());
            stmt.setInt(3, retur.getTotalPengembalian());
            stmt.setString(4, retur.getStatusRetur());
            stmt.setInt(5, retur.getIdRetur());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(returDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteRetur(int idRetur) {
         String sql = "DELETE FROM tabel_retur WHERE id_retur=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRetur);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(returDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateQty(String idProduk, int Qty) {
        
    }

    @Override
    public List<modelReturBarang> getAllRetur() throws SQLException{
        List<modelReturBarang> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_retur";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                modelReturBarang retur = new modelReturBarang(
                    rs.getInt("id_retur"),
                    rs.getString("Ref"),
                    rs.getString("alasan_retur"),
                    rs.getInt("total_pengembalian"),
                    rs.getString("status_retur")
                );
                list.add(retur);
            }
        }
        return list;
    }
   
}