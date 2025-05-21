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
import raven.model.session;
/**
 *
 * @author Raven
 */
public class FormDetailTransaksi extends javax.swing.JPanel {
    private tablePenjualan tblModelPen = new tablePenjualan();
    private tableRekapTransaksi tblModelSmt = new tableRekapTransaksi();
    private serviceRekapTransaksi servisSmt = new rekapTransaksiDAO();
    private servicePenjualanDetail servisDetail = new penjualanDetailDAO();
    private servicePenjualan servis = new penjualanDAO();
    private serviceBarang servisBarang = new barangDAO();
    private String idObat;
    private Integer idKaryawan;
    private int id = 1;
    private Timer timer;
    session sess = session.getInstance();
    
    private modelPenjualan mp = new modelPenjualan();
    public FormDetailTransaksi(String refTransaksi) {
        initComponents();
        this.idKaryawan = sess.getUserId();
        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Kode Produk", "Nama Produk", "Harga", "Qty", "Subtotal"}
        ));
        
        loadDetailData(refTransaksi);
        setLebarKolom();
    }
    private void setLebarKolom() {
    TableColumnModel kolom = tblData.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(100);  
        kolom.getColumn(1).setPreferredWidth(200);  
        kolom.getColumn(2).setPreferredWidth(80);   
        kolom.getColumn(3).setPreferredWidth(50);   
        kolom.getColumn(4).setPreferredWidth(100);
        
    }
    private void loadDetailData(String refTransaksi) {
    List<modelPenjualanDetail> details = servisDetail.tampil_detail_P(refTransaksi);
    
    DefaultTableModel model = (DefaultTableModel) tblData.getModel();
    model.setRowCount(0);
    

    for (modelPenjualanDetail detail : details) {
        model.addRow(new Object[]{
            detail.getModelBaraang().getIdProduk(),
            detail.getModelBaraang().getNamaProduk(),
            detail.getModelBaraang().getHargaProduk(),
            detail.getQty(),
            detail.getNilai()
        });
    }
    }

    private void loadData() {
        List<modelPenjualan> list = servis.tampilPenjualan(idKaryawan);
        tblModelPen.setData(list);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

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
        jScrollPane2.setViewportView(tblData);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Detail Transaksi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table1;
    private javax.swing.JToggleButton tambah;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}
