package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import raven.dao.barangDAO;
import raven.dao.penjualanDAO;
import raven.dao.penjualanDetailDAO;
import raven.dao.rekapTransaksiDAO;
import raven.model.modelBarang;
import raven.model.modelPenjualan;
import raven.model.modelPenjualanDetail;
import raven.model.modelRekapTransaksi;
import raven.model.modelUser;
import raven.service.serviceBarang;
import raven.service.servicePenjualan;
import raven.service.servicePenjualanDetail;
import raven.service.serviceRekapTransaksi;
import raven.tablemodel.tablePenjualan;
import raven.tablemodel.tableRekapTransaksi;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import raven.model.modelDetailRetur;
import raven.model.modelReturBarang;
import raven.model.session;
/**
 *
 * @author Raven
 */
public class FormDetailRetur extends javax.swing.JPanel {

    
    private DefaultTableModel tableModel;
    private modelPenjualan mp = new modelPenjualan();
    public FormDetailRetur(modelReturBarang retur, List<modelDetailRetur> details) {
        initComponents();
        initTableModel();
        loadDetailData(retur, details);
        setLebarKolom();
    }
    private void initTableModel() {
        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID Detail", "Kode Produk", "Nama Produk", "Quantity", "Harga Satuan", "Alasan", "Subtotal"}
        );
        tblData.setModel(tableModel);
    }
    private void loadDetailData(modelReturBarang retur, List<modelDetailRetur> details) {
        jLabel2.setText("Detail Retur #" + retur.getIdRetur() + " - Ref: " + retur.getRef());
        
        // Kosongkan tabel terlebih dahulu
        tableModel.setRowCount(0);
        
        // Isi tabel dengan data detail
        for (modelDetailRetur detail : details) {
            // Anda mungkin perlu menambahkan method untuk mendapatkan nama produk berdasarkan kode
            String productName = getProductName(detail.getKdProduk());
            
            tableModel.addRow(new Object[]{
                detail.getIdDetailRetur(),
                detail.getKdProduk(),
                productName,
                detail.getQuantity(),
                detail.getHargaSatuan(),
                detail.getAlasan(),
                detail.getSubtotal()
            });
        }
    }
    
    private String getProductName(String kodeProduk) {
        // Implementasi untuk mendapatkan nama produk dari kode produk
        // Contoh sederhana:
        try {
            modelBarang barang = new barangDAO().getByKode(kodeProduk);
            return barang != null ? barang.getNamaProduk(): "Unknown Product";
        } catch (Exception e) {
            return "Unknown Product";
        }
    }

    private void setLebarKolom() {
        TableColumnModel kolom = tblData.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(60);  // ID Detail
        kolom.getColumn(1).setPreferredWidth(100); // Kode Produk
        kolom.getColumn(2).setPreferredWidth(200); // Nama Produk
        kolom.getColumn(3).setPreferredWidth(60);  // Quantity
        kolom.getColumn(4).setPreferredWidth(100); // Harga Satuan
        kolom.getColumn(5).setPreferredWidth(150); // Alasan
        kolom.getColumn(6).setPreferredWidth(100); // Subtotal
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        tambah = new javax.swing.JToggleButton();
        edit = new javax.swing.JToggleButton();
        hapus = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblData = new com.raven.swing.Table();

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table1);

        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MASTER PRODUK");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Detail Transaksi");

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed

    }//GEN-LAST:event_tambahActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed

    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed

    }//GEN-LAST:event_hapusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton edit;
    private javax.swing.JToggleButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable table1;
    private javax.swing.JToggleButton tambah;
    private com.raven.swing.Table tblData;
    // End of variables declaration//GEN-END:variables
}
