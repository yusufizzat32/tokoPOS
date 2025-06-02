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
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import raven.config.connectionDB;
import raven.model.session;
/**
 *
 * @author Raven
 */
public class FormTransaksi extends javax.swing.JPanel {
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
    private int currentPage = 1;
    private int rowsPerPage = 10; // Jumlah baris per halaman
    private int totalRows = 0;  
    session sess = session.getInstance();
    
    
    private modelPenjualan mp = new modelPenjualan();
    public FormTransaksi(modelUser modeluser) {
        initComponents();
        btnPrev.setText("Prev");
    btnPrev.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (currentPage > 1) {
                currentPage--;
                loadData();
            }
        }
    });

    btnNext.setText("Next");
    btnNext.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            int totalPages = (int) Math.ceil((double) servis.getTotalPenjualanCount(idKaryawan) / rowsPerPage);
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
        this.idKaryawan = sess.getUserId();
        
        tblData.setModel(tblModelPen);
        loadData();
        setLebarKolom();    
        filterComboBox.addItem("Semua");
        filterComboBox.addItem("Hari Ini");
        filterComboBox.addItem("Minggu Ini");
        filterComboBox.addItem("Bulan Ini");
    }
    private void updatePagination() {
    totalRows = servis.getTotalPenjualanCount(idKaryawan);
    int totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
    
    lblPageInfo.setText("Halaman " + currentPage + " dari " + totalPages);
    
    // Enable/disable tombol berdasarkan halaman
    btnPrev.setEnabled(currentPage > 1);
    btnNext.setEnabled(currentPage < totalPages);
}
    private void loadData() {
        List<modelPenjualan> list = servis.tampilPenjualan(idKaryawan);
        tblModelPen.setData(list);
        updatePagination();
    }
    private void searchData() {
    List<modelPenjualan> list = servis.cariData(jTextField1.getText());
    tblModelPen.setData(list);
    // Untuk search, kita tampilkan semua hasil tanpa pagination
    lblPageInfo.setText("Menampilkan semua hasil pencarian");
    btnPrev.setEnabled(false);
    btnNext.setEnabled(false);
}
    private void printLaporanMasterProduk() {
    try {
        // Path ke file laporan .jasper
        String reportPath = "src/raven/reports/RiwayatTransaksi.jasper"; // sesuaikan path-nya

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
    List<modelPenjualan> list = servis.tampilPenjualanByPeriod(idKaryawan, period);
    tblModelPen.setData(list);
    // Untuk filter period, kita tampilkan semua hasil tanpa pagination
    lblPageInfo.setText("Menampilkan semua hasil filter");
    btnPrev.setEnabled(false);
    btnNext.setEnabled(false);
}
    
    private void setLebarKolom() {
        TableColumnModel kolom = table1.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(50);
        kolom.getColumn(0).setMaxWidth(50);
        kolom.getColumn(0).setMinWidth(50);
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
        jLabel3 = new javax.swing.JLabel();
        detail = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblData = new com.raven.swing.Table();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        filterComboBox = new javax.swing.JComboBox<>();
        btnPrint = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        lblPageInfo = new javax.swing.JLabel();

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

        jLabel3.setText("Search");

        detail.setText("DETAIL");
        detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("RIWAYAT TRANSAKSI");

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

        jLabel4.setText("Search");

        filterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterComboBoxActionPerformed(evt);
            }
        });

        btnPrint.setText("print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnNext.setText("Next");

        btnPrev.setText("Prev");

        lblPageInfo.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(detail, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPageInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPrint)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPageInfo)
                    .addComponent(btnPrev)
                    .addComponent(btnNext))
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed

    }//GEN-LAST:event_tambahActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed

    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed

    }//GEN-LAST:event_hapusActionPerformed

    private void detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailActionPerformed
  
    int selectedRow = tblData.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih transaksi terlebih dahulu!");
        return;
    }
    
    String ref = tblModelPen.getValueAt(selectedRow, 0).toString(); 
    FormDetailTransaksi detailForm = new FormDetailTransaksi(ref);
    javax.swing.JFrame frame = new javax.swing.JFrame();
    frame.setContentPane(detailForm);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    }//GEN-LAST:event_detailActionPerformed

    private void filterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterComboBoxActionPerformed
         String selectedPeriod = (String) filterComboBox.getSelectedItem();
        loadDataByPeriod(selectedPeriod);
    }//GEN-LAST:event_filterComboBoxActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnPrint;
    private javax.swing.JToggleButton detail;
    private javax.swing.JToggleButton edit;
    private javax.swing.JComboBox<String> filterComboBox;
    private javax.swing.JToggleButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblPageInfo;
    private javax.swing.JTable table1;
    private javax.swing.JToggleButton tambah;
    private com.raven.swing.Table tblData;
    // End of variables declaration//GEN-END:variables
}
