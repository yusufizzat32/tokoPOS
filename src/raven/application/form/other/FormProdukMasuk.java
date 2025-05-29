/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import java.sql.Connection;
import java.util.HashMap;
import raven.dao.barangMasukDAO;
import raven.model.modelBarangMasuk;
import raven.service.serviceBarangMasuk;
import raven.tablemodel.tableBarangMasuk;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import raven.config.connectionDB;
/**
 *
 * @author yusuf
 */
public class FormProdukMasuk extends javax.swing.JPanel {

    private final tableBarangMasuk tblModel = new tableBarangMasuk();
    private final serviceBarangMasuk servis = new barangMasukDAO();
    private int currentPage = 1;
    private int rowsPerPage = 10; // Jumlah baris per halaman
    private int totalRows = 0;

    public FormProdukMasuk() {
        initComponents();
        btnPrev.setText("Sebelumnya");
    btnPrev.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (currentPage > 1) {
                currentPage--;
                loadData();
            }
        }
    });
    filterComboBox.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        String selectedPeriod = (String) filterComboBox.getSelectedItem();
        if ("Semua".equals(selectedPeriod)) {
            currentPage = 1; // Reset ke halaman pertama
            loadData(); // Gunakan pagination normal
        } else {
            loadDataByPeriod(selectedPeriod); // Tampilkan semua tanpa pagination
        }
    }
});
    txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
    public void keyReleased(java.awt.event.KeyEvent evt) {
        if (txtSearch.getText().isEmpty()) {
            currentPage = 1; // Reset ke halaman pertama
            loadData(); // Kembali ke pagination normal
        } else {
            searchData(); // Tampilkan semua hasil pencarian
        }
    }
});

    btnNext.setText("Selanjutnya");
    btnNext.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            int totalPages = (int) Math.ceil((double) servis.getTotalDataCount() / rowsPerPage);
            if (currentPage < totalPages) {
                currentPage++;
                loadData();
            }
        }
    });
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            printLaporanMasterProduk();
        }   
        });
        loadData();
        table1.setModel(tblModel);
        setLebarKolom();
        filterComboBox.addItem("Semua");
        filterComboBox.addItem("Hari Ini");
        filterComboBox.addItem("Minggu Ini");
        filterComboBox.addItem("Bulan Ini");
    }
    private void setLebarKolom() {
        TableColumnModel kolom = table1.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(50);
        kolom.getColumn(0).setMaxWidth(50);
        kolom.getColumn(0).setMinWidth(50);
    }
    private void updatePagination() {
    totalRows = servis.getTotalDataCount();
    int totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
    
    lblPageInfo.setText("Halaman " + currentPage + " dari " + totalPages);
    
    // Enable/disable tombol berdasarkan halaman
    btnPrev.setEnabled(currentPage > 1);
    btnNext.setEnabled(currentPage < totalPages);
}

// Modifikasi method loadData
private void loadData() {
    List<modelBarangMasuk> list = servis.showDataWithPagination(currentPage, rowsPerPage);
    tblModel.setData(list);
    updatePagination();
}
    private void printLaporanMasterProduk() {
    try {
        // Path ke file laporan .jasper
        String reportPath = "src/raven/reports/LaporanProdukmasuk.jasper"; // sesuaikan path-nya

        // Load file laporan
        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

        // Buat parameter kosong (jika tidak ada parameter)
        Map<String, Object> params = new HashMap<>();

        // Hubungkan ke database
        Connection conn = connectionDB.getConnection(); // asumsi kamu punya class Koneksi

        // Isi laporan dengan data dari database
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

        // Tampilkan laporan
        JasperViewer.viewReport(jasperPrint, false);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal mencetak laporan: " + e.getMessage());
        e.printStackTrace();
    }
}
     private void loadDataByPeriod(String period) {
        List<modelBarangMasuk> list = servis.showDataByPeriod(period);
        tblModel.setData(list);
        lblPageInfo.setText("Menampilkan semua hasil filter");
    btnPrev.setEnabled(false);
    btnNext.setEnabled(false);
    }
    private void tambahData(){
            InputProdukMasuk input = new InputProdukMasuk(null,true,1,null);
        input.setVisible(true);
        loadData();
    }
    void refreshTable() {
        loadData();
    }
    private void editData() {
        int row = table1.getSelectedRow();
        if (row != -1){
            modelBarangMasuk obat = tblModel.getData(row);
            InputProdukMasuk input = new InputProdukMasuk(null,true, row, obat);
            input.setVisible(true);
            loadData();
        }
    }
    private void hapusData() {
        int row = table1.getSelectedRow();
        if (row != -1) {
            modelBarangMasuk barang = tblModel.getData(row);
            if(JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                servis.hapusData(barang);
                tblModel.deleteData(row);
                loadData();
            } 
        } else {
                JOptionPane.showMessageDialog(null, "Silahkan pilih data yang ingin di hapus!");
        }
    }
 private void searchData(){
        List<modelBarangMasuk> list = servis.searchData(txtSearch.getText());
        tblModel.setData(list);
        lblPageInfo.setText("Menampilkan semua hasil pencarian");
    btnPrev.setEnabled(false);
    btnNext.setEnabled(false);
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
        tambah = new javax.swing.JToggleButton();
        hapus = new javax.swing.JToggleButton();
        edit = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new com.raven.swing.Table();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        filterComboBox = new javax.swing.JComboBox<>();
        lblPageInfo = new javax.swing.JLabel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("PRODUK MASUK");

        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

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
        jScrollPane2.setViewportView(table1);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel2.setText("Search");

        lblPageInfo.setText("jLabel3");

        btnPrev.setText("Prev");

        btnNext.setText("Next");

        btnPrint.setText("PRINT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPrint)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblPageInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)))
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
                    .addComponent(tambah)
                    .addComponent(edit)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPageInfo)
                    .addComponent(btnPrev)
                    .addComponent(btnNext))
                .addContainerGap(11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        tambahData();
    }//GEN-LAST:event_tambahActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        editData();
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        hapusData();
    }//GEN-LAST:event_hapusActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchData();
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnPrint;
    private javax.swing.JToggleButton edit;
    private javax.swing.JComboBox<String> filterComboBox;
    private javax.swing.JToggleButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPageInfo;
    private com.raven.swing.Table table1;
    private javax.swing.JToggleButton tambah;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
