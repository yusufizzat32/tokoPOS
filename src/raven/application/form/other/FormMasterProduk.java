/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import raven.dao.barangDAO;
import raven.model.modelBarang;
import raven.service.serviceBarang;
import raven.tablemodel.tableBarang;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author yusuf
 */
public class FormMasterProduk extends javax.swing.JPanel {
    private final tableBarang tblModel = new tableBarang();
    private final serviceBarang servis = new barangDAO();
    // Tambahkan variabel instance di awal class
private int currentPage = 1;
private int rowsPerPage = 10; // Jumlah baris per halaman
private int totalRows = 0;

    public FormMasterProduk() {
        initComponents();
        btnPrev.setText("Sebelumnya");
btnPrev.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        goToPage(currentPage - 1);
    }
});

btnNext.setText("Selanjutnya");
btnNext.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        goToPage(currentPage + 1);
    }
});

cbxRowsPerPage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "25", "50", "100" }));
cbxRowsPerPage.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        rowsPerPage = Integer.parseInt(cbxRowsPerPage.getSelectedItem().toString());
        currentPage = 1;
        loadData();
    }
});
        loadData();
        loadKategori();
        table1.setModel(tblModel);
        setLebarKolom();
    }
    private void setLebarKolom() {
    TableColumnModel kolom = table1.getColumnModel();
    kolom.getColumn(0).setPreferredWidth(50);
    kolom.getColumn(0).setMaxWidth(50);
    kolom.getColumn(0).setMinWidth(50);
    // Tambahkan pengaturan untuk kolom kategori jika perlu
    kolom.getColumn(7).setPreferredWidth(100);
}
    private void loadData() {
        // Hitung offset berdasarkan halaman saat ini
        int offset = (currentPage - 1) * rowsPerPage;
    
        // Dapatkan data dengan pagination
        List<modelBarang> list = servis.getDataWithPagination(offset, rowsPerPage); 
        totalRows = servis.getTotalRowCount(); // Total semua data
    
        tblModel.setData(list);
        updatePaginationInfo();
    }
    private void updatePaginationInfo() {
        int totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
        lblPageInfo.setText("Halaman " + currentPage + " dari " + totalPages);
        btnPrev.setEnabled(currentPage > 1);
        btnNext.setEnabled(currentPage < totalPages);
    }
    private void goToPage(int page) {
        if (page >= 1 && page <= Math.ceil((double) totalRows / rowsPerPage)) {
            currentPage = page;
            loadData();
        }
    }

    private void loadKategori() {
        List<String> listKategori = servis.getKategoriList(); // Anda perlu menambahkan method getKategoriList() di serviceBarang
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Semua Kategori"); // Opsi default
        for (String kategori : listKategori) {
            model.addElement(kategori);
        }
        cbxKategori.setModel(model);
    }
    private void searchData() {
    String keyword = txtSearch.getText();
    String kategori = cbxKategori.getSelectedItem().toString();
    
    int offset = (currentPage - 1) * rowsPerPage;
    
    List<modelBarang> list;
    if (kategori.equals("Semua Kategori")) {
        list = servis.searchDataWithPagination(keyword, offset, rowsPerPage);
        totalRows = servis.getSearchRowCount(keyword);
    } else {
        list = servis.searchDataByKategoriWithPagination(keyword, kategori, offset, rowsPerPage);
        totalRows = servis.getSearchByKategoriRowCount(keyword, kategori);
    }
    tblModel.setData(list);
    updatePaginationInfo();
}
    private void tambahData(){
        InputMasterProduk input = new InputMasterProduk(null,true,1,null,this);
        input.setVisible(true);
        loadData();
        
    }
    void refreshTable() {
        loadData();
    }
    
    private void editData() {
        int row = table1.getSelectedRow();
        if (row != -1){
            modelBarang obat = tblModel.getData(row);
            InputMasterProduk input = new InputMasterProduk(null,true, row, obat,this);
            input.setVisible(true);
            loadData();
        }
    }
    private void hapusData() {
        int row = table1.getSelectedRow();
        if (row != -1) {
            modelBarang barang = tblModel.getData(row);
            if(JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                servis.hapusData(barang);
                tblModel.deleteData(row);
                loadData();
            } 
        } else {
                JOptionPane.showMessageDialog(null, "Silahkan pilih data yang ingin di hapus!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tambah = new javax.swing.JToggleButton();
        hapus = new javax.swing.JToggleButton();
        edit = new javax.swing.JToggleButton();
        txtSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new com.raven.swing.Table();
        jLabel2 = new javax.swing.JLabel();
        cbxKategori = new javax.swing.JComboBox<>();
        lblPageInfo = new javax.swing.JLabel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        cbxRowsPerPage = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MASTER PRODUK");

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

        jLabel2.setText("Search");

        cbxKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKategoriActionPerformed(evt);
            }
        });

        lblPageInfo.setText("jLabel3");

        btnPrev.setText("Prev");

        btnNext.setText("Next");

        cbxRowsPerPage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                                .addComponent(cbxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPageInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxRowsPerPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(cbxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPageInfo)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(cbxRowsPerPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
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

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchData();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cbxKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKategoriActionPerformed
    searchData();        // TODO add your handling code here:
    }//GEN-LAST:event_cbxKategoriActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JComboBox<String> cbxKategori;
    private javax.swing.JComboBox<String> cbxRowsPerPage;
    private javax.swing.JToggleButton edit;
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
