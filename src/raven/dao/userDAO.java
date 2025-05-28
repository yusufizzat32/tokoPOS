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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
            String sql = "INSERT INTO tabel_user(nama, username, password, alamat, no_telepon, role, rfid) VALUES (?,?,?,?,?,?,?)";

            st = conn.prepareStatement(sql);
            st.setString(1, model.getNama());
            st.setString(2, model.getUsername());
            st.setString(3, generateSHA256(model.getPassword())); 
            st.setString(4, model.getAlamat());
            st.setString(5, model.getno_telepon());
            st.setString(6, model.getRole());
            st.setString(7, model.getRFID());

            
            st.executeUpdate();
            System.out.println("DATA USER DAO BERHASIL DISIMPAN");
            System.out.println("Nama: " + model.getNama());
            System.out.println("Username: " + model.getUsername());
            System.out.println("Password: " + model.getPassword());
            System.out.println("Alamat: " + model.getAlamat());
            System.out.println("No Telepon: " + model.getno_telepon());
            System.out.println("Role: " + model.getRole());
            System.out.println("ID: " + model.getIdUser());
            System.out.println("ID: " + model.getRFID());
            st.close();
        } catch (SQLException e) {
            System.out.println("GAGAL INSERT: " + e.getMessage());
            e.printStackTrace();
        }
    }

   @Override
public void perbaruiData(modelUser model) {
    PreparedStatement st = null;
    try {
        // Query yang diperbaiki dengan urutan parameter yang benar
        String sql = "UPDATE tabel_user SET nama=?, username=?, password=?, alamat=?, no_telepon=?, role=?, rfid=? WHERE id_user=?";
            
        st = conn.prepareStatement(sql);
        st.setString(1, model.getNama());
        st.setString(2, model.getUsername());
        
        // Handle password update
        if (model.getPassword() != null && !model.getPassword().isEmpty()) {
            st.setString(3, generateSHA256(model.getPassword()));
        } else {
            // If password not changed, get current password
            String currentPassword = getCurrentPassword(model.getIdUser());
            st.setString(3, currentPassword);
        }
        
        st.setString(4, model.getAlamat());
        st.setString(5, model.getno_telepon());
        
        // Ensure role matches exactly one of the enum values
        String role = model.getRole().trim();
        if (!role.equals("Admin") && !role.equals("Kasir") && !role.equals("Manajemen Stok")) {
            throw new IllegalArgumentException("Role must be one of: Admin, Kasir, Manajemen Stok");
        }
        st.setString(6, role);
        
        st.setString(7, model.getRFID());
        st.setInt(8, model.getIdUser());

        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal memperbarui data: " + e.getMessage());
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

// Method helper untuk mendapatkan password saat ini
private String getCurrentPassword(int userId) throws SQLException {
    String sql = "SELECT password FROM tabel_user WHERE id_user = ?";
    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, userId);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getString("password");
        }
    }
    return "";
}

    public boolean gantiPassword(String username, String passwordBaru) {
    try {
       
        String sql = "SELECT password FROM tabel_user WHERE Username = ?"; 
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String username1 = rs.getString("Username");

            if (username1.equals(username)) {
                // Password lama cocok, lanjut update
                String updateSql = "UPDATE tabel_user SET password = ? WHERE Username = ?";
                PreparedStatement psUpdate = conn.prepareStatement(updateSql);
                psUpdate.setString(1, passwordBaru);
                psUpdate.setString(2, username);
                int updated = psUpdate.executeUpdate();

                return updated > 0;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}



public void hapusData(int idUser) {
    // Validasi input
    if (idUser <= 0) {
        throw new IllegalArgumentException("ID User tidak valid");
    }

    PreparedStatement st = null;
    try {
        String sql = "DELETE FROM tabel_user WHERE id_user = ?";
        st = conn.prepareStatement(sql);
        st.setInt(1, idUser);
        
        int rowsAffected = st.executeUpdate();
        
        if (rowsAffected == 0) {
            throw new SQLException("Data dengan ID " + idUser + " tidak ditemukan atau sudah dihapus");
        }
        
        System.out.println("Berhasil menghapus user dengan ID: " + idUser);
        
    } catch (SQLException e) {
        // Log error dan lempar exception custom
        System.err.println("Gagal menghapus data: " + e.getMessage());
        throw new RuntimeException("Operasi penghapusan gagal: " + e.getMessage(), e);
    } finally {
        // Pastikan statement ditutup
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                System.err.println("Gagal menutup statement: " + ex.getMessage());
                
            }
        }
    }
}

    @Override
    public List<modelUser> tampilData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<modelUser> list = new ArrayList<>();
        String sql = "SELECT * FROM tabel_user";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
               modelUser u = new modelUser();
            u.setIdUser(rs.getInt("id_user"));
            u.setNama(rs.getString("nama"));
            u.setPassword(rs.getString("password"));
            u.setRole(rs.getString("role"));
            u.setno_telepon(rs.getString("no_telepon"));
            u.setAlamat(rs.getString("alamat"));
            u.setRFID(rs.getString("rfid"));
         
            list.add(u);
        }
        } catch (SQLException e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Gagal memuat data user.");
        }
        return list;
    }
    public int getLastUserID() {
       int lastId = 0;
    String sql = "SELECT MAX(id_user) AS max_id FROM tabel_user";
    
    try (PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        if (rs.next()) {
            lastId = rs.getInt("max_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lastId;        
        
    }
  
    public List<modelUser> pencarianData(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<modelUser> list = new ArrayList<>();
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
                model.setPassword(rs.getString("password"));
                model.setAlamat(rs.getString("alamat"));
                model.setno_telepon(rs.getString("no_telepon"));
                model.setRole(rs.getString("role"));
                model.setRFID(rs.getString("rfid"));

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
            // Hash password input user
         String hashedPassword = generateSHA256(model.getPassword());
         System.out.println("DEBUG | Username input: " + model.getUsername());
         System.out.println("DEBUG | Password hash input: " + hashedPassword);

            
            st = conn.prepareStatement(sql);
            st.setString(1, model.getUsername());
            st.setString(2, hashedPassword); 
            rs = st.executeQuery();

            if (rs.next()) {
                modelUs = new modelUser();
                modelUs.setIdUser(rs.getInt("id_user"));
                modelUs.setNama(rs.getString("nama"));
                modelUs.setUsername(rs.getString("username"));
                modelUs.setRole(rs.getString("role"));
                modelUs.setRFID(rs.getString("rfid"));
                
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

    @Override
    public List<modelUser> showData() {
       List<modelUser> list = new ArrayList<>();
    String sql = "SELECT * FROM tabel_user";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            modelUser user = new modelUser();
            user.setIdUser(rs.getInt("Id_user"));         
            user.setNama(rs.getString("Nama"));   
            user.setUsername(rs.getString("Username"));  
            user.setPassword(rs.getString("Password"));   
            user.setRole(rs.getString("Role"));           
            user.setno_telepon(rs.getString("No_Telepon"));      
            user.setAlamat(rs.getString("Alamat"));    
            user.setRFID(rs.getString("Rfid"));

            list.add(user); 
    }
          } catch (SQLException e) {
        System.out.println("Gagal menampilkan data user: " + e.getMessage());
    }

    return list;
}


public void updateData(modelUser u) {
   try (PreparedStatement ps = conn.prepareStatement(
            "UPDATE tabel_user SET  nama=?, username=?, password=?, role=?, no_telepon=?, alamat=?, rfid=? WHERE id_user=?")) {

        ps.setString(1, u.getNama());                    
        ps.setString(2, u.getUsername());                
        ps.setString(3, generateSHA256(u.getPassword())); 
        ps.setString(4, u.getRole());                    
        ps.setString(5, u.getno_telepon());              
        ps.setString(6, u.getAlamat());          
        ps.setString(7, u.getRFID());
        ps.setInt(8, u.getIdUser());                     
        

        ps.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal memperbarui data user: " + ex.getMessage());
    }
}

    @Override

public void insertData(modelUser u) {
    PreparedStatement st = null;
    try {
        String sql = "INSERT INTO tabel_user(nama, username, password, alamat, no_telepon, role, rfid) VALUES (?, ?, ?, ?, ?, ?, ?)";
        st = conn.prepareStatement(sql);

        // Set parameter
        st.setString(1, u.getNama());
        st.setString(2, u.getUsername());
        st.setString(3, generateSHA256(u.getPassword())); // Hash password
        st.setString(4, u.getAlamat());
        st.setString(5, u.getno_telepon());
        st.setString(6, u.getRole());
        st.setString(7, u.getRFID());
     
        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Gagal menyimpan data user: " + e.getMessage());
    } finally {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

    @Override
    public boolean isUsernameExists(String username) {
         String sql = "SELECT COUNT(*) FROM tabel_user WHERE username = ?";
    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setString(1, username);
        ResultSet rs = st.executeQuery();
        return rs.next() && rs.getInt(1) > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
     
    }

    @Override
    public void hapusData(modelUser model) {
       //
    }

    @Override
    public modelUser getUserByRFID(String rfid) {
        modelUser user = null;
        String sql = "SELECT * FROM user WHERE rfid=?"; 
   
       try {
           
           PreparedStatement pst = conn.prepareStatement(sql);
           pst.setString(1, rfid);
           try(ResultSet rs = pst.executeQuery()) {
               if (rs.next()) {
              
            user.setIdUser(rs.getInt("Id_user"));         
            user.setNama(rs.getString("Nama"));   
            user.setUsername(rs.getString("Username"));  
            user.setPassword(rs.getString("Password"));   
            user.setRole(rs.getString("Role"));           
            user.setno_telepon(rs.getString("No_Telepon"));      
            user.setAlamat(rs.getString("Alamat"));    
            user.setRFID(rs.getString("Rfid"));
               }
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       return user;
    }
    
   @Override
public modelUser loginByRFID(String rfid) {
    modelUser user = null;
    String sql = "SELECT * FROM tabel_user WHERE rfid = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, rfid);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            user = new modelUser();
            user.setIdUser(rs.getInt("id_user"));
            user.setNama(rs.getString("nama"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setRFID(rs.getString("rfid"));
        }

        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return user;
}
@Override
public modelUser prosesLoginByRFID(String rfid) {
    PreparedStatement st = null;
    ResultSet rs = null;
    modelUser modelUs = null;
    String sql = "SELECT * FROM tabel_user WHERE rfid = ?";

    try {
        st = conn.prepareStatement(sql);
        st.setString(1, rfid);
        rs = st.executeQuery();

        if (rs.next()) {
            modelUs = new modelUser();
            modelUs.setIdUser(rs.getInt("id_user"));
            modelUs.setNama(rs.getString("nama"));
            modelUs.setUsername(rs.getString("username"));
            modelUs.setRole(rs.getString("role"));
            modelUs.setRFID(rs.getString("rfid"));
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
    public boolean isRFIDRegistered(String rfid) {
      String sql = "SELECT COUNT (*) FROM user WHERE rfid=?"; 
     try {
           
           PreparedStatement pst = conn.prepareStatement(sql);
           pst.setString(1, rfid);
           try(ResultSet rs = pst.executeQuery()) {
               if (rs.next()) {
                   return rs.getInt(1) > 0;
               }
    }
   } catch (SQLException ex) {
    ex.printStackTrace();
         
     }
             return false;
    }}
