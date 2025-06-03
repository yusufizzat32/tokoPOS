/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package raven.application.form.other;

import java.util.Date;
import java.util.HashMap;
import raven.dao.barangMasukDAO;
import raven.dao.barangDAO;
import raven.model.modelBarangMasuk;
import raven.model.modelBarang;
import raven.service.serviceBarangMasuk;
import raven.service.serviceBarang;
import raven.tablemodel.tableBarangMasuk;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author yusuf
 */
public class InputProdukMasuk extends javax.swing.JDialog {

    /**
     * Creates new form InputMasterProduk
     */
    private final tableBarangMasuk tblModel = new tableBarangMasuk();
    private final serviceBarangMasuk servis = new barangMasukDAO();
    private final modelBarangMasuk barang;
    private serviceBarang servis_Barang = new barangDAO();
    private String idProduk;
    private int row;
    private FormMasterProduk formMasterProduk;
    private final Map<String, String> barangMap;
    int hargaSatuan = 0;
    double totalHarga = 0;
    
    public InputProdukMasuk(java.awt.Frame parent, boolean modal, int row, modelBarangMasuk barang) {
        super(parent, modal);
        this.barang = barang;
        this.row = row;
        barangMap = new HashMap<>();
        this.formMasterProduk = formMasterProduk;
        initComponents();
        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        txtBarcodeActionPerformed(evt);
    }
});
      
        ambilNamaObat();
        setLocationRelativeTo(null);
        if(barang != null){
            dataTable();
        }
        loadData();
        
    }
        private void loadData(){
        List<modelBarangMasuk> list = servis.showData();
        tblModel.setData(list);
    }
        private boolean validasiInput() {
    boolean valid = false;
    if (cbxNamaBarang.getSelectedItem().equals("pilih barang")) {
        JOptionPane.showMessageDialog(null, "Nama barang tidak boleh kosong");
    } else if (txtJumlahMasuk.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Jumlah Masuk tidak boleh kosong");
    } else if (tglKadaluarsa.getDate() == null) {
        JOptionPane.showMessageDialog(null, "Tanggal kadaluarsa tidak boleh kosong");
    } else {
        valid = true;
    }
    return valid;
}
//        private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {                                       
//    String barcode = txtBarcode.getText().trim();
//    if (!barcode.isEmpty()) {
//        cariBarangDariBarcode(barcode);
//    }
//}  

// Tambahkan method untuk mencari barang berdasarkan barcode
private void cariBarangDariBarcode(String barcode) {
    modelBarang barang = servis_Barang.cariBarangByBarcode(barcode);
    if (barang != null) {
        // Set nilai ke komponen form
        cbxNamaBarang.setSelectedItem(barang.getNamaProduk());
        txtIdBarang.setText(barang.getIdProduk());
        // Jika ada field lain yang perlu diisi, tambahkan di sini
    } else {
        JOptionPane.showMessageDialog(this, "Barang dengan barcode " + barcode + " tidak ditemukan", "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}   
        private void tambahData() {
    if (validasiInput() == true) {
        String namaObat = cbxNamaBarang.getSelectedItem().toString();
        String idObat1 = txtIdBarang.getText();
        double jumlah = Double.parseDouble(txtJumlahMasuk.getText());
        double jumlahMasuk = Double.parseDouble(txtJumlahMasuk.getText());
        Date tanggalKadaluarsa = tglKadaluarsa.getDate(); // Ambil tanggal dari JDateChooser

        modelBarangMasuk obat = new modelBarangMasuk();
        obat.setNamaProduk(namaObat);
        obat.setIdProduk(idObat1);
        obat.setStokProduk(jumlah);
        obat.setJumlahMasuk(jumlahMasuk);
        obat.setHargaProduk(hargaSatuan);
        obat.setNilaiProduk(totalHarga);
        obat.setTanggalKadaluarsa(tanggalKadaluarsa); // Set tanggal kadaluarsa

        servis.tambahData(obat);
        tblModel.insertData(obat);
        resetForm();
        dispose();
    }
}  
        private void dataTable() {
           simpan.setText("UPDATE");
            idProduk = barang.getIdProduk();
            cbxNamaBarang.setSelectedItem(barang.getNamaProduk());
            txtIdBarang.setText(barang.getIdProduk());
            txtJumlahMasuk.setText(String.valueOf(barang.getJumlahMasuk()));
            tglKadaluarsa.setDate(barang.getTanggalKadaluarsa()); // Set tanggal kadaluarsa ke JDateChooser
    }
        
        private void resetForm() {
        cbxNamaBarang.setSelectedIndex(0);
        txtIdBarang.setText("");
        txtJumlahMasuk.setText("");
        tglKadaluarsa.setDate(null); // Reset tanggal kadaluarsa
    }
        private void ambilNamaObat() {
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    model.addElement("pilih barang"); // Tambahkan item default
    List<modelBarang> list = servis_Barang.ambilNamaBarang(); // Ambil data nama obat dari database/service

    for (modelBarang barang : list) {
        model.addElement(barang.getNamaProduk()); // Tambahkan nama obat ke dalam model ComboBox
        barangMap.put(barang.getNamaProduk(), barang.getIdProduk()); // Simpan mapping namaObat -> idObat ke dalam Map
    }

    cbxNamaBarang.setModel(model); // Atur model ke ComboBox

    // Tambahkan ActionListener untuk menangani perubahan pilihan
    cbxNamaBarang.addActionListener(e -> {
        String namaProduk = cbxNamaBarang.getSelectedItem().toString(); // Ambil pilihan dari ComboBox
        if (!"pilih barang".equals(namaProduk)) {
            idProduk = barangMap.get(namaProduk); // Dapatkan ID Obat berdasarkan namaObat dari Map
            txtIdBarang.setText(idProduk); // Tampilkan ID Obat di text field
            txtIdBarang.setEditable(false); // Set text field tidak bisa diedit
        } else {
            txtIdBarang.setText(""); // Kosongkan text field jika tidak ada pilihan valid
            txtIdBarang.setEditable(true); // Biarkan bisa diedit jika tidak ada pilihan
        }
    });
}
         private void updateData(){
//            if (!validasiInput()) {
//                return;
//            }
//            // Log untuk debug
//            System.out.println("Mulai menyimpan data...");
//
//            String namaBarang = txtNamaBarang.getText().trim();
////          String satuan = cbxSatuan.getSelectedItem().toString();
//            int hargaJual = Integer.parseInt(txtHargaJual.getText().trim());
//            double stok = ((Number) txtStok.getValue()).doubleValue(); // Correctly handle Double
//            modelBarang brg = new modelBarang();
//            brg.setIdProduk(idProduk);
//            brg.setNamaProduk(namaBarang);
//            brg.setHargaProduk(hargaJual);
////        brg.setSatuanObat(satuan);
//            brg.setStokProduk(stok);
//            brg.setBarcode(idProduk);
//        
//            servis.updateData(brg);
//            tblModel.updateData(row, brg);
//            resetForm();
//            dispose();
        }
        

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdBarang = new javax.swing.JTextField();
        batal = new com.raven.swing.ButtonGradient();
        simpan = new com.raven.swing.ButtonGradient();
        jSeparator1 = new javax.swing.JSeparator();
        cbxNamaBarang = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtJumlahMasuk = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tglKadaluarsa = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(46, 203, 112));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INPUT BARANG");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Manajemen stok >Produk Masuk > Tambah ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(177, Short.MAX_VALUE))
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

        cbxNamaBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("PILIH BARANG");

        jLabel5.setText("JUMLAH");

        jLabel6.setText("TGL KADALUARSA");

        jLabel9.setText("BARCODE");

        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxNamaBarang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdBarang)
                            .addComponent(txtJumlahMasuk)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tglKadaluarsa, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtBarcode, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlahMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tglKadaluarsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
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
            .addGap(0, 389, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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

    private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcodeActionPerformed
         String barcode = txtBarcode.getText().trim();
        if (barcode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Barcode tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        cariBarangDariBarcode(barcode);
    }//GEN-LAST:event_txtBarcodeActionPerformed

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
            java.util.logging.Logger.getLogger(InputProdukMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputProdukMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputProdukMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputProdukMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputProdukMasuk dialog = new InputProdukMasuk(new javax.swing.JFrame(), true, 1, null);
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
    private javax.swing.JComboBox<String> cbxNamaBarang;
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
    private com.toedter.calendar.JDateChooser tglKadaluarsa;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtIdBarang;
    private javax.swing.JTextField txtJumlahMasuk;
    // End of variables declaration//GEN-END:variables
}
