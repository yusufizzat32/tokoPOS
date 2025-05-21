/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.dao;

import raven.config.connectionDB;
import raven.model.modelUser;
import raven.service.serviceUser;
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
public class userDAO implements serviceUser {
   private Connection conn;

    public userDAO() {
        conn = connectionDB.getConnection();
    }

    @Override
    public void tambahData(modelUser model) {
       PreparedStatement st = null;
        try {
            String sql = "INSERT INTO tabel_user(nama, username, password, alamat, no_telepon, role) VALUES (?,?,?,?,?,?)";

            st = conn.prepareStatement(sql);
            st.setString(1, model.getNama());
            st.setString(2, model.getUsername());
            st.setString(3, generateSHA256(model.getPassword()));
            st.setString(4, model.getAlamat());
            st.setString(5, model.getNo_telepon());
            st.setString(6, model.getRole());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(modelUser model) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE tabel_user SET nama=?, username=?, alamat=?, no_telepon=?, role=? WHERE id_user=?";

            st = conn.prepareStatement(sql);
            st.setString(1, model.getNama());
            st.setString(2, model.getUsername());
            st.setString(3, model.getAlamat());
            st.setString(4, model.getNo_telepon());
            st.setString(5, model.getRole());
            st.setInt(6, model.getIdUser());
    
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(modelUser model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM tabel_user WHERE id_user=?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, model.getIdUser());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<modelUser> showData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM tabel_user";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                modelUser model = new modelUser();
                model.setIdUser(rs.getInt("id_user"));
                model.setNama(rs.getString("nama"));
                model.setUsername(rs.getString("username"));
                model.setAlamat(rs.getString("alamat"));
                model.setNo_telepon(rs.getString("no_telepon"));
                model.setRole(rs.getString("role"));

                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<modelUser> pencarianData(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM tabel_user WHERE id_user LIKE '%" + id + "%' "
                + "OR nama LIKE '%" + id + "%' "
                + "OR username LIKE '%" + id + "%' "
                + "OR alamat LIKE '%" + id + "%' "
                + "OR role LIKE '%" + id + "%' ";
        try {

            st = conn.prepareStatement(sql);
            rs = st.executeQuery(); 
            while (rs.next()) {
                modelUser model = new modelUser();
                model.setIdUser(rs.getInt("id_user"));
                model.setNama(rs.getString("nama"));
                model.setUsername(rs.getString("username"));
                model.setAlamat(rs.getString("alamat"));
                model.setNo_telepon(rs.getString("no_telepon"));
                model.setRole(rs.getString("role"));

                list.add(model);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String generateSHA256(String password) {
       try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodehash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder(2 * encodehash.length);

            for (byte b : encodehash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public modelUser prosesLogin(modelUser model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        modelUser modelUs = null;
        String sql = "SELECT * FROM tabel_user WHERE username = ? AND password = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getUsername());
            st.setString(2, model.getPassword());
            rs = st.executeQuery();

            if (rs.next()) {
                modelUs = new modelUser();
                modelUs.setIdUser(rs.getInt("id_user"));
                modelUs.setNama(rs.getString("nama"));
                modelUs.setUsername(rs.getString("username"));
                modelUs.setRole(rs.getString("role"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return modelUs;
    }

    @Override
    public boolean validasiPasswordLama(String username, String passwordLama) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String enkripsiPasswordLama = generateSHA256(passwordLama);
        String sql = "SELECT * FROM tabel_user WHERE username = ? AND password = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, enkripsiPasswordLama);
            rs = st.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean pergantianPassword(String username, String passwordLama, String passwordBaru) {
        PreparedStatement st = null;
        PreparedStatement stUpdate = null;
        ResultSet rs = null;
        String enkripsiPasswordLama = generateSHA256(passwordLama);
        String enkripsiPasswordBaru = generateSHA256(passwordBaru);
        String sql = "SELECT * FROM tabel_user WHERE username = ? AND password = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, enkripsiPasswordLama);
            rs = st.executeQuery();

            if (rs.next()) {
                String sqlUpdate = "UPDATE tabel_user SET password = ? WHERE username = ?";
                stUpdate = conn.prepareStatement(sqlUpdate);
                stUpdate.setString(1, enkripsiPasswordBaru);
                stUpdate.setString(2, username);
                int result = stUpdate.executeUpdate();
                return result > 0;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
