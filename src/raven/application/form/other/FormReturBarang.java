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
    new String[]{"Barcode", "Nama Produk", "Harga", "Jumlah", "Alasan"}
));
   
    }

private void setLayoutform(){
    idref.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "id penjualan" );
    txtscanbarcode.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Barcode");
    txtnamaproduk.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nama Produk");
    txtharga.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Harga");
    txtjumlah.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Jumlah");
    txtalasan.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Alasan");
    
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
                        .addComponent(jSeparator2)
                        .addGap(542, 542, 542))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(simpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }//GEN-LAST:event_simpan1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.ButtonGradient batal;
    private javax.swing.JToggleButton btnCari;
    private javax.swing.JTextField idref;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTextField txtalasan;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtnamaproduk;
    private javax.swing.JTextField txtscanbarcode;
    // End of variables declaration//GEN-END:variables
}
