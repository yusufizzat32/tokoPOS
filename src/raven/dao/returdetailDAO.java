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
import raven.model.modelDetailRetur;
import raven.model.modelReturBarang;
import raven.service.serviceRetur;
import raven.service.serviceReturDetail;
/**
 *
 * @author revi
 */
 public class returdetailDAO implements serviceReturDetail{
    private Connection conn;

    public returdetailDAO() {
        conn = connectionDB.getConnection();
    }

    @Override
    public void insertReturDetail(modelDetailRetur detail) {
        String sql = "INSERT INTO tabel_detailretur (id_retur, Kd_Produk, Quantity, Harga_Satuan, alasan, Subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detail.getIdRetur());
            stmt.setString(2, detail.getKdProduk());
            stmt.setInt(3, detail.getQuantity());
            stmt.setInt(4, detail.getHargaSatuan());
            stmt.setString(5, detail.getAlasan());
            stmt.setInt(6, detail.getSubtotal());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(returdetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void updateReturDetail(modelDetailRetur detail) {
        String sql = "UPDATE tabel_detailretur SET id_retur=?, Kd_Produk=?, Quantity=?, Harga_Satuan=?, alasan=?, Subtotal=? WHERE id_detailretur=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detail.getIdRetur());
            stmt.setString(2, detail.getKdProduk());
            stmt.setInt(3, detail.getQuantity());
            stmt.setInt(4, detail.getHargaSatuan());
            stmt.setString(5, detail.getAlasan());
            stmt.setInt(6, detail.getSubtotal());
            stmt.setInt(7, detail.getIdDetailRetur());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(returdetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteReturDetail(int idDetailRetur) {
        String sql = "DELETE FROM tabel_detailretur WHERE id_detailretur=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDetailRetur);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(returdetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<modelDetailRetur> getAllReturDetail() throws SQLException {
        List<modelDetailRetur> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_detailretur";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                modelDetailRetur detail = new modelDetailRetur(
                    rs.getInt("id_detailretur"),
                    rs.getInt("id_retur"),
                    rs.getString("Kd_Produk"),
                    rs.getInt("Quantity"),
                    rs.getInt("Harga_Satuan"),
                    rs.getString("alasan"),
                    rs.getInt("Subtotal")
                );
                list.add(detail);
            }
        }
        return list;
    }
    }