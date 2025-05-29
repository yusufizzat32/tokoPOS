/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import raven.dao.barangDAO;
import raven.model.modelBarang;
import raven.service.serviceBarang;
import raven.tablemodel.tableBarang;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import raven.config.connectionDB;
import raven.dao.returDAO;
import raven.dao.returdetailDAO;
import raven.model.modelDetailRetur;
import raven.model.modelReturBarang;
import raven.service.serviceRetur;
import raven.service.serviceReturDetail;
import raven.tablemodel.tableRetur;
/**
 *
 * @author yusuf
 */
public class FormReturs extends javax.swing.JPanel {
 private final tableRetur tblModel = new tableRetur();
    private final serviceRetur servis = new returDAO();
    private final serviceReturDetail detailService = new returdetailDAO(); // Tambahkan ini
    // Tambahkan variabel instance di awal class


    public FormReturs() {
        initComponents();
         tableRetur.setModel(tblModel);
        loadData(); // Memuat data saat form pertama kali dibuka
        setLebarKolom();


    }
 
    

    private void loadData() {
        try {
            List<modelReturBarang> list = servis.getAllRetur();
            tblModel.setData(list);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data retur: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     private void showDetailRetur() {
    int selectedRow = tableRetur.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih retur terlebih dahulu", 
                "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    modelReturBarang selectedRetur = tblModel.getData(selectedRow);
    try {
        List<modelDetailRetur> details = detailService.getAllReturDetail()
            .stream()
            .filter(d -> d.getIdRetur() == selectedRetur.getIdRetur())
            .collect(Collectors.toList());
        
        // Buat JFrame baru untuk menampung panel detail
        JFrame detailFrame = new JFrame("Detail Retur");
        detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Tutup hanya window ini
        detailFrame.setSize(800, 600); // Sesuaikan ukuran
        
        // Tambahkan FormDetailRetur ke JFrame
        FormDetailRetur detailPanel = new FormDetailRetur(selectedRetur, details);
        detailFrame.add(detailPanel);
        
        // Tampilkan di tengah layar relatif terhadap FormReturs
        detailFrame.setLocationRelativeTo(this);
        detailFrame.setVisible(true);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal memuat detail retur: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void searchData() {
        String searchText = txtSearch.getText().trim();
        try {
            List<modelReturBarang> allData = servis.getAllRetur();
            List<modelReturBarang> filteredData = new ArrayList<>();
            
            if (searchText.isEmpty()) {
                tblModel.setData(allData);
                return;
            }
            
            for (modelReturBarang retur : allData) {
                if (retur.getRef().toLowerCase().contains(searchText.toLowerCase()) ||
                    retur.getAlasanRetur().toLowerCase().contains(searchText.toLowerCase()) ||
                    retur.getStatusRetur().toLowerCase().contains(searchText.toLowerCase()) ||
                    String.valueOf(retur.getIdRetur()).contains(searchText)) {
                    filteredData.add(retur);
                }
            }
            
            tblModel.setData(filteredData);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mencari data: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void refreshTable() {
        loadData();
    }
        private void setLebarKolom() {
        TableColumnModel kolom = tableRetur.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(50);
        kolom.getColumn(0).setMaxWidth(50);
        kolom.getColumn(0).setMinWidth(50);
    }

    private void hapusData() {
        int row = tableRetur.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus", 
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        modelReturBarang retur = tblModel.getData(row);
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Apakah Anda yakin ingin menghapus retur dengan ID: " + retur.getIdRetur() + "?", 
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                servis.deleteRetur(retur.getIdRetur());
                tblModel.deleteData(row);
                JOptionPane.showMessageDialog(this, "Data retur berhasil dihapus", 
                        "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        hapus = new javax.swing.JToggleButton();
        txtSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableRetur = new com.raven.swing.Table();
        jLabel2 = new javax.swing.JLabel();
        btnDetail = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("RIWAYAT RETUR");

        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        tableRetur.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableRetur);

        jLabel2.setText("Search");

        btnDetail.setText("DETAIL");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDetail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hapus)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnDetail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
 
    }//GEN-LAST:event_hapusActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchData();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        showDetailRetur();
    }//GEN-LAST:event_btnDetailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetail;
    private javax.swing.JToggleButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private com.raven.swing.Table tableRetur;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
