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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Raven
 */
public class LoginForm extends javax.swing.JPanel {
    
    private serviceUser servis = new userDAO();

    public LoginForm() {
        initComponents();
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
    
//    private void prosesLogin() {
//        // Validasi input sebelum melanjutkan
//        if (!validasiInput()) {
//            JOptionPane.showMessageDialog(
//                null, 
//                "Harap isi Username dan Password!", 
//                "Validasi Input", 
//                JOptionPane.WARNING_MESSAGE
//            );
//            return;
//        }
//
//        // Ambil data dari form
//        String user = txtUsername.getText();
//        String pass = new String(txtPassword.getPassword());
//
//        // Buat model user
//        modelUser model = new modelUser();
//        model.setUsername(user);
//        model.setPassword(pass);
//
//        // Proses login menggunakan service
//        modelUser hasilLogin = servis.prosesLogin(model);
//
//        if (hasilLogin != null) {
//            // Login berhasil
//            MainForm.login(hasilLogin);
//            JOptionPane.showMessageDialog(
//                null, 
//                "Login berhasil! Selamat datang, " + hasilLogin.getNama()+ ".", 
//                "Login Sukses", 
//                JOptionPane.INFORMATION_MESSAGE
//            );
//            resetForm();
//        } else {
//            // Login gagal
//            JOptionPane.showMessageDialog(
//                null, 
//                "Username atau Password salah. Silakan coba lagi.", 
//                "Login Gagal", 
//                JOptionPane.ERROR_MESSAGE
//            );
//        }
//    }

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

        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Login");
        panelLogin1.add(lbTitle);

        lbUser.setText("User Name");
        panelLogin1.add(lbUser);
        panelLogin1.add(txtUsername);

        lbPass.setText("Password");
        panelLogin1.add(lbPass);
        panelLogin1.add(txtPassword);

        cmdLogin.setText("Login");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });
        panelLogin1.add(cmdLogin);

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
        Application.login();
    }//GEN-LAST:event_cmdLoginActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdLogin;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private raven.application.form.PanelLogin panelLogin1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
