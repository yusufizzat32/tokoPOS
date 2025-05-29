/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.sun.org.apache.xerces.internal.impl.dv.xs.IDREFDV;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import raven.dao.barangDAO;
import raven.model.modelBarang;
import raven.service.serviceBarang;
import raven.tablemodel.tableBarang;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import raven.config.connectionDB;
import raven.dao.penjualanDAO;
import raven.dao.penjualanDetailDAO;
import raven.dao.returDAO;
import raven.model.modelReturBarang;
/**
 *
 * @author yusuf
 */
public class FormReturBarang extends javax.swing.JPanel {

    private final Connection conn;
    private penjualanDetailDAO penjualanDetailService = new penjualanDetailDAO();
    
    public FormReturBarang() {
        initComponents();
        setLayoutform();
        conn = connectionDB.getConnection();
        // Inisialisasi tabel model jika belum
        penjualanDetailService = new penjualanDetailDAO();
    
    // Inisialisasi tabel model
    tabelretur.setModel(new DefaultTableModel(
    new Object[][]{},
    new String[]{"Kd Produk", "Nama Produk", "Harga", "Jumlah", "Alasan","Sub Total"}
));
    checkTableAndButtonStatus();
    
    // Tambahkan listener untuk perubahan tabel
    tabelretur.getModel().addTableModelListener(e -> checkTableAndButtonStatus());

    }
    private void checkTableAndButtonStatus() {
    boolean isTableEmpty = tabelretur.getRowCount() == 0;
    simpan1.setEnabled(!isTableEmpty);
}
private void updateTotal() {
    DefaultTableModel model = (DefaultTableModel) tabelretur.getModel();
    int total = 0;

    for (int i = 0; i < model.getRowCount(); i++) {
        // Pastikan kolom subtotal adalah kolom ke-5 (indeks 5)
        Object subtotalObj = model.getValueAt(i, 5);
        if (subtotalObj instanceof Integer) {
            total += (Integer) subtotalObj;
        } else if (subtotalObj instanceof String) {
            try {
                total += Integer.parseInt((String) subtotalObj);
            } catch (NumberFormatException e) {
                // Handle error jika subtotal bukan angka
            }
        }
    }

    txtTotal1.setText(String.valueOf(total));
}
private void setLayoutform(){
    idref.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "id penjualan" );
    txtscanbarcode.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Barcode");
    txtnamaproduk.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nama Produk");
    txtharga.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Harga");
    txtjumlah.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Jumlah");
    txtalasan.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Alasan");
    
    }
private void clearAllFields() {
    idref.setText("");
    txtscanbarcode.setText("");
    txtnamaproduk.setText("");
    txtharga.setText("");
    txtjumlah.setText("");
    txtalasan.setText("");
    txtTotal1.setText("0");
    txtBayar.setText("");
    txtKembalian.setText("");
    
    // Kosongkan tabel
    DefaultTableModel model = (DefaultTableModel) tabelretur.getModel();
    model.setRowCount(0);
    
    // Periksa status tombol setelah membersihkan
    checkTableAndButtonStatus();
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        save1 = new com.raven.swing.ButtonGradient();
        save = new com.raven.swing.ButtonGradient();
        batal = new com.raven.swing.ButtonGradient();
        simpan = new com.raven.swing.ButtonGradient();
        jSeparator1 = new javax.swing.JSeparator();
        txtscanbarcode = new javax.swing.JTextField();
        idref = new javax.swing.JTextField();
        txtnamaproduk = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        txtalasan = new javax.swing.JTextField();
        btnCari = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        save2 = new com.raven.swing.ButtonGradient();
        save3 = new com.raven.swing.ButtonGradient();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelretur = new javax.swing.JTable();
        simpan1 = new com.raven.swing.ButtonGradient();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTotal1 = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        txtKembalian = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnReset = new com.raven.swing.ButtonGradient();

        save1.setText("EDIT");
        save1.setColor1(new java.awt.Color(46, 204, 113));
        save1.setColor2(new java.awt.Color(46, 204, 113));
        save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save1ActionPerformed(evt);
            }
        });

        save.setText("SAVE");
        save.setColor1(new java.awt.Color(46, 204, 113));
        save.setColor2(new java.awt.Color(46, 204, 113));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
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

        simpan.setText("SIMPAN");
        simpan.setColor1(new java.awt.Color(46, 204, 113));
        simpan.setColor2(new java.awt.Color(46, 204, 113));
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        txtscanbarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtscanbarcodeActionPerformed(evt);
            }
        });

        idref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idrefActionPerformed(evt);
            }
        });

        txtnamaproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaprodukActionPerformed(evt);
            }
        });

        txtharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargaActionPerformed(evt);
            }
        });

        txtjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahActionPerformed(evt);
            }
        });

        txtalasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtalasanActionPerformed(evt);
            }
        });

        btnCari.setText("...");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jLabel4.setText("ID Ref");

        save2.setText("EDIT");
        save2.setColor1(new java.awt.Color(46, 204, 113));
        save2.setColor2(new java.awt.Color(46, 204, 113));
        save2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save2ActionPerformed(evt);
            }
        });

        save3.setText("SAVE");
        save3.setColor1(new java.awt.Color(46, 204, 113));
        save3.setColor2(new java.awt.Color(46, 204, 113));
        save3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save3ActionPerformed(evt);
            }
        });

        tabelretur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane3.setViewportView(tabelretur);

        simpan1.setText("SIMPAN");
        simpan1.setColor1(new java.awt.Color(46, 204, 113));
        simpan1.setColor2(new java.awt.Color(46, 204, 113));
        simpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("RETUR BARANG");

        jLabel10.setText("Barcode");

        jLabel11.setText("Nama Produk");

        jLabel12.setText("Harga");

        jLabel13.setText("Jumlah");

        jLabel14.setText("Alasan");

        txtTotal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotal1ActionPerformed(evt);
            }
        });

        txtBayar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBayarCaretUpdate(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Kembalian");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Bayar");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Total");

        btnReset.setText("RESET");
        btnReset.setColor1(new java.awt.Color(46, 204, 113));
        btnReset.setColor2(new java.awt.Color(46, 204, 113));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idref, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtscanbarcode, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnamaproduk, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtharga, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtjumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtalasan, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 82, Short.MAX_VALUE)
                                .addComponent(save2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(save3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator2)
                                .addGap(542, 542, 542))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(simpan1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(save3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(save2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idref)
                        .addComponent(txtscanbarcode)
                        .addComponent(txtnamaproduk)
                        .addComponent(txtharga)
                        .addComponent(txtjumlah)
                        .addComponent(txtalasan)
                        .addComponent(btnCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))))
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtscanbarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtscanbarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtscanbarcodeActionPerformed

    private void idrefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idrefActionPerformed

    }//GEN-LAST:event_idrefActionPerformed

    private void txtnamaprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamaprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaprodukActionPerformed

    private void txthargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargaActionPerformed

    private void txtjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjumlahActionPerformed

    private void txtalasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtalasanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtalasanActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        String ref = idref.getText().trim();
        if (ref.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan ID Penjualan terlebih dahulu!");
            return;
        }

        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        cariBrgRetur dialog = new cariBrgRetur(parentFrame, true, ref);
        dialog.setVisible(true);

        // Ambil data setelah dialog ditutup
        modelBarang selected = dialog.getSelectedBarang();
        if (selected != null) {
            txtscanbarcode.setText(selected.getIdProduk());
            txtnamaproduk.setText(selected.getNamaProduk());
            txtharga.setText(String.valueOf(selected.getHargaProduk()));
            // Jika perlu, set jumlah maksimal yang bisa diretur
            // txtjumlah.setText(String.valueOf(selected.getStokProduk()));
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_save1ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if(txtscanbarcode.getText().isEmpty() ||
            txtnamaproduk.getText().isEmpty() ||
            txtharga.getText().isEmpty() ||
            txtjumlah.getText().isEmpty() ||
            txtalasan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Harap isi semua field!");
            return;
        }

        try {
            String barcode = txtscanbarcode.getText();
            String nama = txtnamaproduk.getText();
            int harga = Integer.parseInt(txtharga.getText());
            int jumlah = Integer.parseInt(txtjumlah.getText());
            String alasan = txtalasan.getText();

            DefaultTableModel model = (DefaultTableModel) tabelretur.getModel();
            // Ganti addRow menjadi insertRow dengan indeks 0
            model.insertRow(0, new Object[]{
                barcode,
                nama,
                harga,
                jumlah,
                alasan
            });

            txtjumlah.setText("");
            txtalasan.setText("");

        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Format angka tidak valid!");
        }
    }//GEN-LAST:event_saveActionPerformed

    private void save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_save2ActionPerformed

    private void save3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save3ActionPerformed
        if (txtscanbarcode.getText().isEmpty() ||
        txtnamaproduk.getText().isEmpty() ||
        txtharga.getText().isEmpty() ||
        txtjumlah.getText().isEmpty() ||
        txtalasan.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Harap isi semua field!");
        return;
    }

    try {
        String barcode = txtscanbarcode.getText();
        String nama = txtnamaproduk.getText();
        int harga = Integer.parseInt(txtharga.getText());
        int jumlah = Integer.parseInt(txtjumlah.getText());
        String alasan = txtalasan.getText();
        int subtotal = harga * jumlah; // Hitung subtotal

        DefaultTableModel model = (DefaultTableModel) tabelretur.getModel();
        model.insertRow(0, new Object[]{
            barcode,
            nama,
            harga,
            jumlah,
            alasan,
            subtotal // Tambahkan subtotal ke tabel
        });

        // Update total
        updateTotal();

        txtjumlah.setText("");
        txtalasan.setText("");

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Format angka tidak valid!");
    }
    }//GEN-LAST:event_save3ActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed

  

    }//GEN-LAST:event_batalActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed

        // Validasi apakah tabel kosong
        if (tabelretur.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data retur yang akan disimpan!");
            return;
        }

        // Validasi ID Ref penjualan
        String refPenjualan = idref.getText().trim();
        if (refPenjualan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID Ref Penjualan harus diisi!");
            return;
        }

        try {
            // Inisialisasi service
            returDAO returService = new returDAO();
            penjualanDAO penjualanService = new penjualanDAO();

            // Hitung total pengembalian
            int totalPengembalian = 0;
            DefaultTableModel model = (DefaultTableModel) tabelretur.getModel();

            // Buat objek retur
            modelReturBarang retur = new modelReturBarang();
            retur.setRef(refPenjualan);
            for (int i = 0; i < model.getRowCount(); i++) {
                String alasan = model.getValueAt(i, 4).toString(); // kolom ke-4 (indeks 4 = kolom ke-5)

                retur.setAlasanRetur(alasan);
            }
            retur.setStatusRetur("selesai");
            retur.setTotalPengembalian(0);

            // Simpan header retur dan dapatkan ID retur
            int idRetur = returService.insertReturAndGetId(retur);
            if (idRetur == -1) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data retur!");
                return;
            }

            // Simpan detail retur
            for (int i = 0; i < model.getRowCount(); i++) {
                try {
                    // Ambil data dengan indeks yang benar
                    String barcode = String.valueOf(model.getValueAt(i, 0)); // Kolom 0: Barcode
                    String namaProduk = String.valueOf(model.getValueAt(i, 1)); // Kolom 1: Nama Produk
                    String hargaStr = String.valueOf(model.getValueAt(i, 2)); // Kolom 2: Harga
                    String jumlahStr = String.valueOf(model.getValueAt(i, 3)); // Kolom 3: Jumlah
                    String alasan = String.valueOf(model.getValueAt(i, 4)); // Kolom 4: Alasan

                    // Validasi data kosong/null
                    if (hargaStr.isEmpty() || hargaStr.equals("null") ||
                        jumlahStr.isEmpty() || jumlahStr.equals("null")) {
                        JOptionPane.showMessageDialog(this, "Data Harga/Jumlah pada baris " + (i+1) + " tidak valid!");
                        continue; // Lewati baris ini
                    }

                    int harga = Integer.parseInt(hargaStr);
                    int jumlah = Integer.parseInt(jumlahStr);

                    // Cari Kd_Produk berdasarkan barcode
                    modelBarang barang = penjualanService.cariBarangByBarcode(barcode);

                    if (barang != null && barang.getIdProduk() != null) {
                        returService.insertDetailRetur(
                            idRetur,
                            barcode,
                            jumlah,
                            harga,
                            alasan
                        );

                        totalPengembalian += (harga * jumlah);

                        // Update stok barang
                        penjualanDetailService.updateStok(barang.getIdProduk(), -jumlah);
                    } else {
                        JOptionPane.showMessageDialog(this, "Barang dengan barcode " + barcode + " tidak ditemukan!");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Format angka tidak valid pada baris " + (i+1) + "!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error pada baris " + (i+1) + ": " + e.getMessage());
                }

            }

            // Update total pengembalian di header retur
            retur.setIdRetur(idRetur);
            retur.setTotalPengembalian(totalPengembalian);
            returService.updateRetur(retur);

            JOptionPane.showMessageDialog(this, "Data retur berhasil disimpan!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_simpanActionPerformed

    private void simpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan1ActionPerformed

        // Validasi apakah tabel kosong
    if (tabelretur.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "Tidak ada data retur yang akan disimpan!");
        return;
    }

    // Validasi ID Ref penjualan
    String refPenjualan = idref.getText().trim();
    if (refPenjualan.isEmpty()) {
        JOptionPane.showMessageDialog(this, "ID Ref Penjualan harus diisi!");
        return;
    }

    try {
        // Inisialisasi service
        returDAO returService = new returDAO();
        penjualanDAO penjualanService = new penjualanDAO();

        // Hitung total pengembalian
        int totalPengembalian = 0;
        DefaultTableModel model = (DefaultTableModel) tabelretur.getModel();

        // Buat objek retur
        modelReturBarang retur = new modelReturBarang();
        retur.setRef(refPenjualan);
        
        // Simpan alasan retur (ambil dari baris pertama)
        if (model.getRowCount() > 0) {
            retur.setAlasanRetur(model.getValueAt(0, 4).toString());
        }
        
        retur.setStatusRetur("selesai");
        retur.setTotalPengembalian(0);

        // Simpan header retur dan dapatkan ID retur
        int idRetur = returService.insertReturAndGetId(retur);
        if (idRetur == -1) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data retur!");
            return;
        }

        // Simpan detail retur dan kumpulkan data untuk update penjualan
        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                String barcode = String.valueOf(model.getValueAt(i, 0));
                String namaProduk = String.valueOf(model.getValueAt(i, 1));
                String hargaStr = String.valueOf(model.getValueAt(i, 2));
                String jumlahStr = String.valueOf(model.getValueAt(i, 3));
                String alasan = String.valueOf(model.getValueAt(i, 4));

                // Validasi data
                if (hargaStr.isEmpty() || hargaStr.equals("null") ||
                    jumlahStr.isEmpty() || jumlahStr.equals("null")) {
                    JOptionPane.showMessageDialog(this, "Data Harga/Jumlah pada baris " + (i+1) + " tidak valid!");
                    continue;
                }

                int harga = Integer.parseInt(hargaStr);
                int jumlah = Integer.parseInt(jumlahStr);
                int subtotal = harga * jumlah;

                // Cari Kd_Produk berdasarkan barcode
                modelBarang barang = penjualanService.cariBarangByBarcode(barcode);

                if (barang != null && barang.getIdProduk() != null) {
                    // Simpan detail retur
                    returService.insertDetailRetur(
                        idRetur,
                        barcode,
                        jumlah,
                        harga,
                        alasan
                    );

                    totalPengembalian += subtotal;

                    // Update stok barang
                    penjualanDetailService.updateStok(barang.getIdProduk(), -jumlah);
                    
                    // Update detail penjualan
                    updatePenjualanDetail(refPenjualan, barcode, jumlah, subtotal);
                } else {
                    JOptionPane.showMessageDialog(this, "Barang dengan barcode " + barcode + " tidak ditemukan!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Format angka tidak valid pada baris " + (i+1) + "!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error pada baris " + (i+1) + ": " + e.getMessage());
            }
        }

        // Update total pengembalian di header retur
        retur.setIdRetur(idRetur);
        retur.setTotalPengembalian(totalPengembalian);
        returService.updateRetur(retur);
        
        // Update header penjualan
        returService.updatePenjualanAfterRetur(refPenjualan, totalPengembalian);

        JOptionPane.showMessageDialog(this, "Data retur berhasil disimpan!");
        clearAllFields();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_simpan1ActionPerformed
private void updatePenjualanDetail(String refPenjualan, String kdProduk, int jumlahRetur, int subtotalRetur) {
    String sql = "UPDATE tabel_transaksidetail SET Quantity = Quantity - ?, Subtotal = Subtotal - ? WHERE Ref = ? AND Kd_Produk = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, jumlahRetur);
        stmt.setInt(2, subtotalRetur);
        stmt.setString(3, refPenjualan);
        stmt.setString(4, kdProduk);
        
        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            System.out.println("Tidak ada baris yang diupdate untuk Ref: " + refPenjualan + " dan Kd_Produk: " + kdProduk);
        }
    } catch (SQLException ex) {
        System.out.println("Gagal mengupdate detail penjualan: " + ex.getMessage());
        ex.printStackTrace();
    }
}
    private void txtTotal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotal1ActionPerformed

    private void txtBayarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBayarCaretUpdate
         String bayarStr = txtBayar.getText().replace(",", "").trim();
    String totalStr = txtTotal1.getText().replace(",", "").trim();

    if (!bayarStr.matches("\\d+")) {
        txtKembalian.setText("0");
        simpan1.setEnabled(false);
//        btnReset.setEnabled(false);
        return;
    }

    try {
        int bayar = Integer.parseInt(bayarStr);
        int total = Integer.parseInt(totalStr);
        int kembali = bayar - total;

        // Hanya tampilkan kembalian jika tidak negatif
        if (kembali >= 0) {
            txtKembalian.setText(Integer.toString(kembali));
            simpan1.setEnabled(true);
        } else {
            txtKembalian.setText("0"); // Tampilkan 0 jika bayar < total
            simpan1.setEnabled(false);  // Nonaktifkan tombol simpan
        }
//        btnReset.setEnabled(true);
    } catch (NumberFormatException ex) {
        txtKembalian.setText("0");
        simpan1.setEnabled(false);
//        btnReset.setEnabled(false);
    }
    }//GEN-LAST:event_txtBayarCaretUpdate

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearAllFields(); // Bersihkan semua field dan tabel
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.ButtonGradient batal;
    private javax.swing.JToggleButton btnCari;
    private com.raven.swing.ButtonGradient btnReset;
    private javax.swing.JTextField idref;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.raven.swing.ButtonGradient save;
    private com.raven.swing.ButtonGradient save1;
    private com.raven.swing.ButtonGradient save2;
    private com.raven.swing.ButtonGradient save3;
    private com.raven.swing.ButtonGradient simpan;
    private com.raven.swing.ButtonGradient simpan1;
    private javax.swing.JTable tabelretur;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtKembalian;
    private javax.swing.JTextField txtTotal1;
    private javax.swing.JTextField txtalasan;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtnamaproduk;
    private javax.swing.JTextField txtscanbarcode;
    // End of variables declaration//GEN-END:variables
}
