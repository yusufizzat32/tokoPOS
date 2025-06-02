/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package raven.application.form.other;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import raven.dao.barangDAO;
import raven.model.modelBarang;
import raven.service.serviceBarang;
import raven.tablemodel.tableBarang;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import raven.config.connectionDB;
/**
 *
 * @author yusuf
 */
public class InputMasterProduk extends javax.swing.JDialog {

    /**
     * Creates new form InputMasterProduk
     */
    private final tableBarang tblModel = new tableBarang();
    private final serviceBarang servis = new barangDAO();
    private modelBarang barang ;
    private String idProduk;
    private int row;
    private FormMasterProduk formMasterProduk;
    
    
    public InputMasterProduk(java.awt.Frame parent, boolean modal,int row, modelBarang barang,FormMasterProduk formMasterProduk) {
        super(parent, modal);
        this.barang = barang;
        this.row = row;
        this.formMasterProduk = formMasterProduk;
        
        initComponents();  // This must be called first to initialize components
        String generatedId = servis.generateProductId();
        txtIdProduk.setText(generatedId);
        txtIdProduk.setEditable(false); // Nonaktifkan edit manual
        // Now we can safely access UI components
        txtBarcode.requestFocusInWindow();
        setupBarcodeScannerHandling();
    
        setLocationRelativeTo(null);        
        loadKategori();
        if (barang != null) {
        dataTable();
        }
        loadData();
        
    }
        private void loadData(){
            List<modelBarang> list = servis.showData();
            tblModel.setData(list);
        }
        private boolean validasiInput() {
            if (txtIdProduk.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID Obat tidak boleh kosong.");
            txtIdProduk.requestFocus();
            return false;
        }
            if (txtNamaBarang.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Obat tidak boleh kosong.");
            txtNamaBarang.requestFocus();
            return false;
        }
            if (txtHargaJual.getText().trim().isEmpty() || !isNumeric(txtHargaJual.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka dan tidak boleh kosong.");
            txtHargaJual.requestFocus();
            return false;
        }
            if (cbxKategori.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Harap pilih kategori produk");
            cbxKategori.requestFocus();
            return false;
        }
    
    // Handle the case where txtStok.getValue() is a Double
            Object stokValue = txtStok.getValue();
            if (stokValue == null) {
            JOptionPane.showMessageDialog(this, "Stok tidak boleh kosong.");
            txtStok.requestFocus();
            return false;
        }
    
    double stok;
    if (stokValue instanceof Double) {
        stok = (Double) stokValue;
    } else if (stokValue instanceof Integer) {
        stok = ((Integer) stokValue).doubleValue();
    } else {
        JOptionPane.showMessageDialog(this, "Stok harus berupa angka.");
        txtStok.requestFocus();
        return false;
    }
    
    if (stok < 0) {
        JOptionPane.showMessageDialog(this, "Stok harus berupa angka positif.");
        txtStok.requestFocus();
        return false;
    }
    
    return true;
    }
         private boolean isNumeric(String str) {
        try {
            Long.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
        private void tambahData() {
    if (!validasiInput()) {
        return;
    }
    
    // Validasi kategori dipilih
    if (cbxKategori.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this, "Harap pilih kategori", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String namaBarang = txtNamaBarang.getText().trim();
    int harga = Integer.parseInt(txtHargaJual.getText().trim());
    int hargaBeli = Integer.parseInt(txtHargaBeli.getText().trim());
    double stok = ((Number) txtStok.getValue()).doubleValue();
    String idBarang = txtIdProduk.getText().trim();
    String barcode = txtBarcode.getText().trim();
    
    // Get selected kategori id
    int idKategori = getSelectedKategoriId();

    modelBarang brg = new modelBarang();
    brg.setIdProduk(idBarang);
    brg.setNamaProduk(namaBarang);
    brg.setHargaProduk(harga);
    brg.setHargaBeli(hargaBeli);
    brg.setStokProduk(stok);
    brg.setBarcode(barcode);
    brg.setIdKategori(idKategori);
    
    servis.tambahData(brg);
    tblModel.insertData(brg);
    formMasterProduk.refreshTable();
    resetForm();
}    
        private void dataTable() {
    simpan.setText("UPDATE");
    idProduk = barang.getIdProduk();
    txtIdProduk.setText(barang.getIdProduk());
    txtNamaBarang.setText(barang.getNamaProduk());
    txtHargaJual.setText(String.valueOf(barang.getHargaProduk()));
    txtHargaBeli.setText(String.valueOf(barang.getHargaBeli()));
    txtStok.setValue(barang.getStokProduk());
    
    // Set selected kategori
    if (barang.getIdKategori() > 0) {
        try {
            Connection conn = connectionDB.getConnection();
            String sql = "SELECT nama_kategori FROM kategori WHERE id_kategori = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, barang.getIdKategori());
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                String namaKategori = rs.getString("nama_kategori");
                cbxKategori.setSelectedItem(namaKategori);
            }
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
        
        private void resetForm() {
        txtIdProduk.setText("");
        txtBarcode.setText("");
        txtHargaJual.setText(""); 
        txtHargaBeli.setText(""); 
        txtStok.setValue(0); 
        txtNamaBarang.setText("");
        txtIdProduk.requestFocus();
        this.dispose();
    }
         private void updateData() {
    if (!validasiInput()) {
        return;
    }
    
    // Validasi kategori dipilih
    if (cbxKategori.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this, "Harap pilih kategori", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String namaBarang = txtNamaBarang.getText().trim();
    int hargaJual = Integer.parseInt(txtHargaJual.getText().trim());
    int hargaBeli = Integer.parseInt(txtHargaBeli.getText().trim());
    double stok = ((Number) txtStok.getValue()).doubleValue();
    
    // Get selected kategori id
    int idKategori = getSelectedKategoriId();

    modelBarang brg = new modelBarang();
    brg.setIdProduk(idProduk);
    brg.setNamaProduk(namaBarang);
    brg.setHargaProduk(hargaJual);
    brg.setHargaBeli(hargaBeli);
    brg.setStokProduk(stok);
    brg.setBarcode(idProduk);
    brg.setIdKategori(idKategori);
    
    servis.updateData(brg);
    tblModel.updateData(row, brg);
    resetForm();
    dispose();
}
         private int getSelectedKategoriId() {
    String selectedKategori = (String) cbxKategori.getSelectedItem();
    if (selectedKategori == null || selectedKategori.equals("-- Pilih Kategori --")) {
        return 0;
    }
    
    try {
        Connection conn = connectionDB.getConnection();
        String sql = "SELECT id_kategori FROM kategori WHERE nama_kategori = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, selectedKategori);
        ResultSet rs = st.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("id_kategori");
        }
        
        rs.close();
        st.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return 0;
}
         private void loadKategori() {
    try {
        Connection conn = connectionDB.getConnection();
        String sql = "SELECT id_kategori, nama_kategori FROM kategori";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        
        // Clear existing items
        cbxKategori.removeAllItems();
        
        // Add default empty item
        cbxKategori.addItem("-- Pilih Kategori --");
        
        while(rs.next()) {
            // Add kategori to combobox
            cbxKategori.addItem(rs.getString("nama_kategori"));
        }
        
        rs.close();
        st.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat kategori: " + e.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
        

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdProduk = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNamaBarang = new javax.swing.JTextField();
        txtHargaJual = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        batal = new com.raven.swing.ButtonGradient();
        simpan = new com.raven.swing.ButtonGradient();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        txtHargaBeli = new javax.swing.JTextField();
        cbxKategori = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtStok = new javax.swing.JSpinner();
        txtBarcode = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(46, 203, 112));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INPUT BARANG");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Manajemen stok >Master Produk > Tambah ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jLabel4.setText("KODE BARANG");

        jLabel5.setText("NAMA BARANG");

        jLabel6.setText("HARGA JUAL");

        batal.setText("BATAL");
        batal.setColor1(new java.awt.Color(46, 204, 113));
        batal.setColor2(new java.awt.Color(46, 204, 113));
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
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

        jLabel8.setText("HARGA BELI");

        cbxKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("KATEGORI");

        jLabel11.setText("BARCODE");

        jLabel12.setText("STOK");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHargaJual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(cbxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdProduk)
                            .addComponent(txtBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIdProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
         if(simpan.getText().equals("SIMPAN")){
            tambahData();
        } else if(simpan.getText().equals("UPDATE")){
            updateData();
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        if(simpan.getText().equals("SIMPAN")){
            resetForm();
        } else if(simpan.getText().equals("UPDATE")){
            dispose();
        }
    }//GEN-LAST:event_batalActionPerformed
    private void handleBarcodeInput(String barcode) {
        if (barcode.isEmpty()) {
            return;
        }
    
    modelBarang existingItem = servis.cariBarangByBarcode(barcode);
    
    if (existingItem != null) {
        JOptionPane.showMessageDialog(this, 
            "Produk dengan barcode ini sudah ada:\n" +
            "Nama: " + existingItem.getNamaProduk() + "\n" +
            "Harga: " + existingItem.getHargaProduk(),
            "Info Produk",
            JOptionPane.INFORMATION_MESSAGE);
        
        txtBarcode.requestFocusInWindow();
        txtBarcode.selectAll();
    } else {
        txtNamaBarang.requestFocusInWindow();
        
        if (txtNamaBarang.getText().isEmpty()) {
            txtNamaBarang.setText("Produk " + barcode);
        }
    }
}
    private void setupBarcodeScannerHandling() {
    // 1. Fokus otomatis ke field ID Barang saat form dibuka
    txtBarcode.requestFocusInWindow();
    
    // 2. Seleksi semua teks saat field ID Barang mendapat fokus
    txtBarcode.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            SwingUtilities.invokeLater(() -> {
                txtBarcode.selectAll();
            });
        }
    });
    
    // 3. Handle ketika barcode di-scan (Enter ditekan)
    txtBarcode.addActionListener(e -> {
        handleBarcodeInput(txtBarcode.getText().trim());
    });
    
    // 4. Validasi input real-time untuk mencegah karakter tidak valid
    txtBarcode.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            // Hanya izinkan karakter alfanumerik dan beberapa simbol khusus
            if (!Character.isLetterOrDigit(e.getKeyChar()) 
                && e.getKeyChar() != '-' 
                && e.getKeyChar() != '_'
                && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                e.consume();
            }
        }
    });
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputMasterProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputMasterProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputMasterProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputMasterProduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormMasterProduk formMasterProduk = new FormMasterProduk();
                InputMasterProduk dialog = new InputMasterProduk(new javax.swing.JFrame(), true, 1, null, formMasterProduk);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.ButtonGradient batal;
    private javax.swing.JComboBox<String> cbxKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private com.raven.swing.ButtonGradient simpan;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtHargaBeli;
    private javax.swing.JTextField txtHargaJual;
    private javax.swing.JTextField txtIdProduk;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JSpinner txtStok;
    // End of variables declaration//GEN-END:variables
}
