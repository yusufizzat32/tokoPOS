/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form;

import java.awt.Dimension;
import raven.application.form.other.*;
import java.awt.Frame;
import raven.model.modelUser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import raven.dao.userDAO;
import raven.model.modelUser;
import raven.service.serviceUser;
import raven.tablemodel.tableUser;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
/**
 *
 * @author Olla
 */
public class RegisterForm extends javax.swing.JDialog {
    public  int idUserEdit = -1;
    private final tableUser tblUser = new tableUser();
    private final serviceUser servis = new userDAO();
    private modelUser user ;
    private String idUser;
    private int mode;
    private FormUser formUser;
   private LoginForm loginForm;
 
    
   
    
    /**
     * Creates new form InputManajemenUser
     * @param parent
     * @param modal
     * @param mode
     * @param user
     * @param form
     */
      // Komponen GUI
   
    
     public RegisterForm(java.awt.Frame parent, boolean modal, int mode, modelUser user, FormUser form) {
       super(parent, modal);
           initComponents();
             setupRFIDListener();
        txtRFID = new javax.swing.JTextField(); 
            setLocationRelativeTo(null);
          
        setLocationRelativeTo(parent);
             setDefaultCloseOperation(DISPOSE_ON_CLOSE);
           pack();
           
         
 
    
  this.loginForm = loginForm;
      
       this.user = user;
       this.mode = mode;
        this.formUser = form;
  
       
      
    SwingUtilities.invokeLater(() -> {
    if (txtRole != null) {
    txtRole.removeAllItems();            
    txtRole.addItem("Kasir");
    txtRole.addItem("Manajemen Stok");
   
    
        if (mode == 1 && user != null) {
        // Mode edit
        simpan.setText("UPDATE"); // Tambahkan ini
        txtIDUser.setEnabled(false); 
        txtNama.setText(user.getNama());
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        txtRole.setSelectedItem(user.getRole());
        txtNotelp.setText(user.getno_telepon());
        txtAlamat.setText(user.getAlamat());
        txtRFID.setText(user.getRFID());
        
    } else if (mode == 0) {
        // Mode insert
        userDAO dao = new userDAO();
        txtIDUser.setEnabled(false);
    }}
    });
            }
    public RegisterForm() {
    
    setLocationRelativeTo(null);
    }

    RegisterForm(JFrame jFrame, boolean b) {
        
    }
      
      private void loadData() {
        List<modelUser> list = servis.showData();
        tblUser.setData(list);
    }
      
      private boolean validasiInput() {
       
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong.");
            txtNama.requestFocus();
            return false;
        }
        if (txtUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username tidak boleh kosong.");
            txtUsername.requestFocus();
            return false;
        }
        if (txtPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Password tidak boleh kosong.");
            txtPassword.requestFocus();
            return false;
        }
        if (txtAlamat.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Alamat tidak boleh kosong.");
        txtAlamat.requestFocus();
        return false;
    }
        if (txtNotelp.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "No Telepon tidak boleh kosong.");
        txtNotelp.requestFocus();
        return false;
    }
        if (!txtNotelp.getText().matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "No Telepon harus berupa angka.");
        txtNotelp.requestFocus();
        return false;
    }
        String role = (String) txtRole.getSelectedItem();
    if (role == null || role.equals("Pilih Role")) {
        JOptionPane.showMessageDialog(this, "Silakan pilih Role terlebih dahulu.");
        return false;
    }
    if (txtRFID.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Kode RFID tidak boleh kosong.");
        txtRFID.requestFocus();
        return false;
    }
    
    if (!txtRFID.getText().matches("[0-9A-Fa-f]+")) {
        JOptionPane.showMessageDialog(this, "Format RFID tidak valid. Gunakan angka 0-9 dan huruf A-F.");
        txtRFID.requestFocus();
        return false;
    }
    
    if (txtRFID.getText().length() != 8) { // Adjust length as needed
        JOptionPane.showMessageDialog(this, "Kode RFID harus 8 karakter.");
        txtRFID.requestFocus();
        return false;
    }
    
        return true;
    }
private void simpanData() {
    // Validasi input terlebih dahulu
    if (!validasiInput()) {
        return;
    }

    try {
        // Buat objek modelUser baru
         modelUser u = new modelUser();
        u.setNama(txtNama.getText());
        u.setUsername(txtUsername.getText());
        u.setPassword(new String(txtPassword.getPassword()));
        u.setRole(txtRole.getSelectedItem().toString());
        u.setno_telepon(txtNotelp.getText());
        u.setAlamat(txtAlamat.getText());
        u.setRFID(txtRFID.getText().trim()); // Add RFID
        

        // Panggil service untuk insert data
        servis.insertData(u);
        
        // Tampilkan pesan sukses
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        
        // Refresh tabel di FormUser
       
        
        // Tutup dialog
        dispose();
    } catch (Exception e) {
        // Tampilkan pesan error jika terjadi masalah
        JOptionPane.showMessageDialog(this, 
            "Gagal menyimpan data: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

private void perbaruiData() {
    if (!validasiInput()) return;

    modelUser model = new modelUser();
    model.setIdUser(Integer.parseInt(txtIDUser.getText()));
    model.setNama(txtNama.getText());
    model.setUsername(txtUsername.getText());
    model.setAlamat(txtAlamat.getText());
    model.setno_telepon(txtNotelp.getText());
    model.setRole(txtRole.getSelectedItem().toString());
    model.setRFID(txtRFID.getText());

    // Hanya set password jika diubah (field tidak kosong)
    String newPassword = new String(txtPassword.getPassword()).trim();
    if (!newPassword.isEmpty()) {
        model.setPassword(newPassword);
    } // else: password tidak di-set (akan dipertahankan yang lama)
    
    servis.perbaruiData(model);
    JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
    
    dispose();
    
}


    private RegisterForm(Frame parent, boolean modal) {
      
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLNama = new javax.swing.JLabel();
        jLUsername = new javax.swing.JLabel();
        jLPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JTextField();
        jLRole = new javax.swing.JLabel();
        jLNotelp = new javax.swing.JLabel();
        jLAlamat = new javax.swing.JLabel();
        txtRole = new javax.swing.JComboBox<>();
        txtNotelp = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        simpan = new com.raven.swing.ButtonGradient();
        batal = new com.raven.swing.ButtonGradient();
        jLNama1 = new javax.swing.JLabel();
        txtIDUser = new javax.swing.JTextField();
        jLAlamat1 = new javax.swing.JLabel();
        txtRFID = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(46, 203, 112));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("REGISTER");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jLNama.setText("Nama");

        jLUsername.setText("Username");

        jLPassword.setText("Password");

        jLRole.setText("Role");

        jLNotelp.setText("No. Telepon");

        jLAlamat.setText("Alamat");

        txtRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRoleActionPerformed(evt);
            }
        });

        simpan.setText("SIMPAN");
        simpan.setColor1(new java.awt.Color(46, 204, 113));
        simpan.setColor2(new java.awt.Color(46, 204, 113));
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        batal.setText("BATAL");
        batal.setColor1(new java.awt.Color(46, 204, 113));
        batal.setColor2(new java.awt.Color(46, 204, 113));
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        jLNama1.setText("ID User");

        jLAlamat1.setText("Kode RFID");

        txtRFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRFIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLNotelp)
                        .addGap(27, 27, 27)
                        .addComponent(txtNotelp))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLNama1)
                        .addGap(54, 54, 54)
                        .addComponent(txtIDUser)
                        .addGap(69, 69, 69))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLNama)
                            .addComponent(jLUsername)
                            .addComponent(jLPassword)
                            .addComponent(jLRole))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword)
                                    .addComponent(txtUsername))
                                .addGap(69, 69, 69))
                            .addComponent(txtNama)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLAlamat)
                            .addComponent(jLAlamat1))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAlamat)
                            .addComponent(txtRFID))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNama1)
                    .addComponent(txtIDUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLNama)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLUsername)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLRole)
                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLNotelp)
                            .addComponent(txtNotelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLAlamat)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLAlamat1)
                            .addComponent(txtRFID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 151, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        btnBack.setText("back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnBack)
                .addContainerGap(323, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(397, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(22, 22, 22))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 16, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        if(simpan.getText().equals("SIMPAN")){
            resetForm();
        } else if(simpan.getText().equals("UPDATE")){
            dispose();
        }
    }//GEN-LAST:event_batalActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        if (!validasiInput()) return;
    
    if (simpan.getText().equals("SIMPAN")) {
        simpanData();
    } else if (simpan.getText().equals("UPDATE")) {
        perbaruiData();
    }
    }//GEN-LAST:event_simpanActionPerformed

       private void updateData() {
    if (!validasiInput()) return;

    modelUser model = new modelUser();
        model.setIdUser(Integer.parseInt(txtIDUser.getText()));
        model.setNama(txtNama.getText());
        model.setUsername(txtUsername.getText());
        model.setAlamat(txtAlamat.getText());
        model.setno_telepon(txtNotelp.getText());
        model.setRole(txtRole.getSelectedItem().toString());
        model.setIdUser(idUserEdit);
        model.setRFID(txtRFID.getText());

         String password = new String(txtPassword.getPassword()).trim();
        // Cek apakah password diisi atau tidak
        if (!password.isEmpty()) {
            model.setPassword(password); // Nanti di DAO akan di-hash
        } else {
            model.setPassword(null); // Tandanya tidak ubah password
        }
    
     servis.updateData(model);
    JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
   
    
    dispose();
}
    private void txtRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRoleActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        
        loginForm.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtRFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRFIDActionPerformed

    }//GEN-LAST:event_txtRFIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.ButtonGradient batal;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLAlamat;
    private javax.swing.JLabel jLAlamat1;
    private javax.swing.JLabel jLNama;
    private javax.swing.JLabel jLNama1;
    private javax.swing.JLabel jLNotelp;
    private javax.swing.JLabel jLPassword;
    private javax.swing.JLabel jLRole;
    private javax.swing.JLabel jLUsername;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private com.raven.swing.ButtonGradient simpan;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtIDUser;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNotelp;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtRFID;
    private javax.swing.JComboBox<String> txtRole;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

 public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormUser formUser = new FormUser();
                RegisterForm dialog = new RegisterForm(new javax.swing.JFrame(), true, 0, null, formUser);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                         JFrame frame = new JFrame("Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);  // Atur ukuran sesuai kebutuhan
            frame.setLocationRelativeTo(null); // Tengah layar
            frame.setContentPane(new RegisterForm()); // Tambahkan panel login
            frame.setVisible(true);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void resetForm() {
        txtIDUser.setText("");
        txtNama.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtRole.setSelectedIndex(0);
        txtNotelp.setText("");
        txtAlamat.setText("");
        txtRFID.setText("");
    }

    private void tambahData() {
    simpanData();       
    }

    private void setupRFIDListener() {
    txtRFID.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent e) {
                String uid = txtRFID.getText().trim();
                
                // Jika panjang UID mencukupi (contoh: 10 karakter)
                if (uid.length() >= 10) {
                    // Cek apakah UID terdaftar di database
                    if (autentikasiRFID(uid)) {
                        JOptionPane.showMessageDialog(RegisterForm.this, "Login berhasil via RFID!");
                        dispose(); // Tutup LoginForm
                        new FormDashboard().setVisible(true); // Buka dashboard
                    } else {
                        JOptionPane.showMessageDialog(RegisterForm.this, "Kartu RFID tidak terdaftar!");
                    }
                    txtRFID.setText(""); // Reset field
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
        JOptionPane.showMessageDialog(RegisterForm.this, "Terjadi kesalahan saat mengakses database.");
    } finally {
        // Tutup koneksi
        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
    return isValid;
}
          });
    }

 

}