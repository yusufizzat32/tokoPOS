package raven.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.application.Application;
import raven.dao.userDAO;
import raven.model.modelUser;
import raven.service.serviceUser;
import raven.application.form.MainForm;
import raven.config.connectionDB;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import raven.application.Application;
import raven.model.session;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import raven.application.form.other.FormDashboard;


/**
 *
 * @author Raven
 */
public class LoginForm extends javax.swing.JPanel {
    
    private serviceUser servis = new userDAO();
      private JTextField txtRFID = new JTextField();
      
    public LoginForm() {
        initComponents();
        
        setupRFIDListener();
        txtRFID = new javax.swing.JTextField(); 
     
        
        init();
    }
    
    private void resetForm() {
        txtUsername.setText("");
        txtPassword.setText("");
    }
    
    private boolean validasiInput() {
        boolean valid = false;
        if (txtUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username tidak boleh kosong");
        } else if (txtPassword.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password tidak boleh kosong");
        } else {
            valid = true;
        }
        return valid;
    }
    
private void prosesLogin() {
    if (!validasiInput()) {
        JOptionPane.showMessageDialog(
            null, 
            "Harap isi Username dan Password!", 
            "Validasi Input", 
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    String user = txtUsername.getText();
    String pass = new String(txtPassword.getPassword());
    
    modelUser model = new modelUser();
    model.setUsername(user);
    model.setPassword(pass);

    modelUser hasilLogin = servis.prosesLogin(model);
    
    if (hasilLogin != null) {
        // Pastikan ID user valid
        if (hasilLogin.getIdUser() <= 0) {
            JOptionPane.showMessageDialog(
                null, 
                "ID User tidak valid!", 
                "Login Gagal", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        // Set session
        session sess = session.getInstance();
        sess.setUserSession(
            hasilLogin.getIdUser(), 
            hasilLogin.getUsername(), 
            hasilLogin.getRole()
        );
        
        // Normalisasi role
        String role = hasilLogin.getRole().trim().toLowerCase();
        if (role.equals("admin") || role.equals("kasir") || role.equals("manajemen stok")) {
            Application.login(hasilLogin);
            JOptionPane.showMessageDialog(
                null, 
                "Login berhasil! Selamat datang, " + hasilLogin.getNama()+ ".", 
                "Login Sukses", 
                JOptionPane.INFORMATION_MESSAGE
            );
            resetForm();
        } else {
            JOptionPane.showMessageDialog(
                null, 
                "Role tidak valid atau tidak diizinkan untuk login.", 
                "Login Gagal", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    } else {
        JOptionPane.showMessageDialog(
            null, 
            "Username atau Password salah. Silakan coba lagi.", 
            "Login Gagal", 
            JOptionPane.ERROR_MESSAGE
        );
    }
}

                                      
private void init() {
        setLayout(new MigLayout("al center center"));

        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "User Name");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogin1 = new raven.application.form.PanelLogin();
        lbTitle = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        cmdLogin = new javax.swing.JButton();
        register = new javax.swing.JButton();
        lbPass1 = new javax.swing.JLabel();
        RFID = new javax.swing.JTextField();

        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Login");
        panelLogin1.add(lbTitle);

        lbUser.setText("User Name");
        panelLogin1.add(lbUser);
        panelLogin1.add(txtUsername);

        lbPass.setText("Password");
        panelLogin1.add(lbPass);

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        panelLogin1.add(txtPassword);

        cmdLogin.setText("Login");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });
        panelLogin1.add(cmdLogin);

        register.setText("Belum Punya Akun?");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
        panelLogin1.add(register);

        lbPass1.setText("KODE RFID");
        panelLogin1.add(lbPass1);
        panelLogin1.add(RFID);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(panelLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(183, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed
        prosesLogin();
    }//GEN-LAST:event_cmdLoginActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    InputUser inputUserDialog = new InputUser(parentFrame, true, 0, null, null);
    inputUserDialog.setVisible(true);
    }//GEN-LAST:event_registerActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField RFID;
    private javax.swing.JButton cmdLogin;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbPass1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private raven.application.form.PanelLogin panelLogin1;
    private javax.swing.JButton register;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

private void setupRFIDListener() {
    RFID.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
                String rfid = RFID.getText().trim();
                if (!rfid.isEmpty()) {
                    processRFIDLogin(rfid);
                }
            }
        }
    });
}
private void processRFIDLogin(String rfid) {
    modelUser user = servis.loginByRFID(rfid);
    if (user != null && user.getIdUser() > 0) {
        String role = user.getRole() != null ? user.getRole().trim().toLowerCase() : "";
        
        if (role.equals("admin") || role.equals("kasir") || role.equals("manajemen stok")) {
            // Set session properly
            session sess = session.getInstance();
            sess.setUserSession(
                user.getIdUser(), 
                user.getUsername(), 
                user.getRole()
            );
            
            Application.login(user);
            JOptionPane.showMessageDialog(
                null, 
                "Login berhasil via RFID! Selamat datang, " + user.getNama() + ".", 
                "Login Sukses", 
                JOptionPane.INFORMATION_MESSAGE
            );
            resetForm();
            RFID.setText("");
        } else {
            JOptionPane.showMessageDialog(
                null, 
                "Role tidak valid atau tidak diizinkan untuk login.", 
                "Login Gagal", 
                JOptionPane.ERROR_MESSAGE
            );
            RFID.setText("");
        }
    } else {
        JOptionPane.showMessageDialog(
            null, 
            "RFID tidak terdaftar atau data user tidak lengkap!", 
            "Login Gagal", 
            JOptionPane.ERROR_MESSAGE
        );
        RFID.setText("");
    }
}
              private boolean autentikasiRFID(String uid) {
            
    boolean isValid = false;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Ganti dengan koneksi database milikmu
        conn = ps.getConnection(); 
        String sql = "SELECT * FROM user WHERE rfid_uid = ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, uid);
        rs = ps.executeQuery();

        if (rs.next()) {
            isValid = true; // UID ditemukan
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(LoginForm.this, "Terjadi kesalahan saat mengakses database.");
    } finally {
        // Tutup koneksi
        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
    return isValid;
}
   private void dispose() {
    }
}
