package raven.application.form.other;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import raven.dao.penjualanDAO;
import raven.dao.penjualanDetailDAO;
import raven.dao.rekapTransaksiDAO;
import raven.model.modelBarang;
import raven.model.modelPenjualan;
import raven.model.modelPenjualanDetail;
import raven.model.modelRekapTransaksi;
import raven.service.servicePenjualan;
import raven.service.servicePenjualanDetail;
import raven.service.serviceRekapTransaksi;
import raven.tablemodel.tableRekapTransaksi;
import java.util.List;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import raven.config.connectionDB;
import raven.model.session;


public class FormKasir extends javax.swing.JPanel {
        private final tableRekapTransaksi tblModel = new tableRekapTransaksi();
        private final serviceRekapTransaksi servisSmt = new rekapTransaksiDAO();
        private final servicePenjualanDetail servisDetail = new penjualanDetailDAO();
        private final servicePenjualan servis = new penjualanDAO();
        private final Connection conn;
//      private final lp = new LaporanDAO();
        
    
        public String kode_barang;
        public String nama_barang;
        public static int qty;
        public static int harga;
        public static double stok;
        public int total;
        
        public FormKasir() {
            conn = connectionDB.getConnection(); // Initialize connection
            initComponents();
            tbPengeluaran.setModel(tblModel);
            loadData();
            
            btnHapus.setEnabled(false);
            btnSimpan.setEnabled(false);
            tbPengeluaran.getTableHeader().setBackground(new Color(46,204,113));
            tbPengeluaran.getTableHeader().setForeground(new Color(39,174,96));
            btnUpdate.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnUpdateActionPerformed(evt);
                }
            });
            
            

            
            if(!txtBarcode.getText().isEmpty()) {
                btnCari.setEnabled(true);
                btnCancel.setEnabled(true);
                txtQty.setEditable(true);
            } else {
                btnNew.setEnabled(true);
                btnCari.setEnabled(false);
                btnSimpan.setEnabled(false);
                btnCancel.setEnabled(false);
                txtQty.setEditable(false);
            }
            SwingUtilities.invokeLater(() -> {
                txtBarcode.requestFocusInWindow();
            });
             txtBarcode.addActionListener(e -> {
                String barcode = txtBarcode.getText().trim();
                if (!barcode.isEmpty()) {
                    handleBarcodeInput(barcode);
                }
             });
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
//            txtBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyReleased(java.awt.event.KeyEvent evt) {
//                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//                        String barcode = txtBarcode.getText().trim();
//                    if (!barcode.isEmpty()) {
//                        handleBarcodeInput(barcode);
//                    }
//                }
//            }
//    });
        }
        
        private void loadData() {
            List<modelRekapTransaksi> list = servisSmt.tampilData();
            tblModel.setData(list);
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        btnHapus = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        txtNama = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNoFaktur = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        txtKembalian = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTotal2 = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtDiskon = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPengeluaran = new com.raven.swing.Table();
        txtSubTotal = new javax.swing.JTextField();

        jLabel2.setText("jLabel2");

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(46, 204, 113));
        jLabel1.setText("KASIR GARREN JAYA");

        jLabel4.setText("TOTAL");

        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcodeActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(231, 76, 60));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(46, 204, 113));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("PERBARUI");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel6.setText("BARCODE");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("No. Faktur");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Sub Total");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Total");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Bayar");

        txtBayar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBayarCaretUpdate(evt);
            }
        });

        txtKembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKembalianActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Kembalian");

        btnSave.setText("save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel13.setText("NAMA");

        jLabel14.setText("HARGA");

        jLabel15.setText("STOK");

        txtQty.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtQtyCaretUpdate(evt);
            }
        });
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQtyKeyTyped(evt);
            }
        });

        jLabel16.setText("QTY");

        txtTotal2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        txtTotal2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal2.setText("0");
        txtTotal2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnCari.setText("cari barang");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("%");

        btnReset.setText("reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(231, 76, 60));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("BATAL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtDiskon.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDiskonCaretUpdate(evt);
            }
        });

        btnSimpan.setBackground(new java.awt.Color(46, 204, 113));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(46, 204, 113));
        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("NEW");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        tbPengeluaran.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbPengeluaran);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBarcode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStok, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtQty, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(234, 234, 234)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSubTotal)
                                    .addComponent(txtNoFaktur, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 639, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus)
                    .addComponent(btnUpdate)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(btnCancel)
                    .addComponent(btnSimpan)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNoFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(120, 120, 120))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBayarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBayarCaretUpdate
         String bayarStr = txtBayar.getText().replace(",", "").trim();
    String totalStr = txtTotal.getText().replace(",", "").trim();

    if (!bayarStr.matches("\\d+")) {
        txtKembalian.setText("0");
        btnSave.setEnabled(false);
        btnReset.setEnabled(false);
        return;
    }

    try {
        int bayar = Integer.parseInt(bayarStr);
        int total = Integer.parseInt(totalStr);
        int kembali = bayar - total;

        // Hanya tampilkan kembalian jika tidak negatif
        if (kembali >= 0) {
            txtKembalian.setText(Integer.toString(kembali));
            btnSave.setEnabled(true);
        } else {
            txtKembalian.setText("0"); // Tampilkan 0 jika bayar < total
            btnSave.setEnabled(false);  // Nonaktifkan tombol simpan
        }
        btnReset.setEnabled(true);
    } catch (NumberFormatException ex) {
        txtKembalian.setText("0");
        btnSave.setEnabled(false);
        btnReset.setEnabled(false);
    }
    }//GEN-LAST:event_txtBayarCaretUpdate
private int parseNumberField(JTextField field, String fieldName) throws NumberFormatException {
    String text = field.getText().replaceAll("[^\\d]", "").trim();
    if (text.isEmpty()) {
        throw new NumberFormatException("Field " + fieldName + " tidak boleh kosong");
    }
    return Integer.parseInt(text);
}
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        btnSave.setEnabled(false);
    
    try {
        // Validate inputs
        int totalall = parseNumberField(txtTotal, "Total");
        int diskon = parseNumberField(txtDiskon, "Diskon");
        int bayar = parseNumberField(txtBayar, "Bayar");
        int kembali = parseNumberField(txtKembalian, "Kembalian");
       
        if (bayar < totalall) {
            JOptionPane.showMessageDialog(null, "PEMBAYARAN KURANG");
            btnSave.setEnabled(true);
            return;
        }

        // Start transaction
        conn.setAutoCommit(false);

        // Save main transaction
        String ref = txtNoFaktur.getText();
        modelPenjualan mp = new modelPenjualan();
        session sess = session.getInstance();
        int idUser = sess.getUserId();
        String nama = sess.getUsername();
    
        mp.setCreateAt(new Timestamp(System.currentTimeMillis()));
        mp.setRef(ref);
        mp.setKasir(nama);
        mp.setBayar(bayar);
        mp.setTotal(totalall);
        mp.setKembalian(kembali);
        mp.setTanggal(LocalDate.now().toString());
        mp.setIdUser(idUser);
        
        servis.tambahPenjualan(mp);

        // Save transaction details
        for (int n = 0; n < tbPengeluaran.getRowCount(); n++) {
            modelRekapTransaksi item = tblModel.getData(n);
            
            String kode_barangB = item.getModelBarang().getIdProduk();
            String nama_barangB = item.getModelBarang().getNamaProduk();
            int hargaB = item.getModelBarang().getHargaProduk();
            int qtyB = item.getModelPendet().getQty();
            int totallB = item.getModelPendet().getNilai();
            
            // Update stok
            servisSmt.updateStok(kode_barangB, qtyB);

            modelPenjualanDetail smt = new modelPenjualanDetail();
            smt.getModelPenjualan().setRef(ref);
            smt.getModelBaraang().setIdProduk(kode_barangB);
            smt.getModelBaraang().setNamaProduk(nama_barangB);
            smt.getModelBaraang().setHargaProduk(hargaB);
            smt.setQty(qtyB);
            smt.setNilai(totallB);
            
            servisDetail.tambah_detail_P(smt);
        }

        // Commit transaction
        conn.commit();
        
        printNota();

        btnNew.setEnabled(false);
        reset();
        
        JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan!");
        
    } catch (Exception e) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException ex) {
            System.out.println("Rollback failed: " + ex.getMessage());
        }
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            System.out.println("Failed to set auto-commit: " + ex.getMessage());
        }
        btnSave.setEnabled(true);
    }
    }//GEN-LAST:event_btnSaveActionPerformed
//    
    
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed

        int row = tbPengeluaran.getSelectedRow();
        tblModel.deleteData(row);
        btnHapus.setEnabled(false);
        btnCancel.setEnabled(false);
                btnNew.setEnabled(true);
        btnCari.setEnabled(false);
        //        tampilTTL.setText("0");
        //        txtotal2.setText("");
        //        txGrandTotal.setText("");
        int subTotal = 0;
        for(int n =0;n<tbPengeluaran.getRowCount();n++){
            int hargajual = (int) tbPengeluaran.getValueAt(n, 4);
            int  qty1 = (int) tbPengeluaran.getValueAt(n, 6);
            int  total1 = hargajual*qty;
            int total2 = (int) tbPengeluaran.getValueAt(n,7);
            subTotal += total2;
        }
        txtTotal2.setText(txtTotal.getText()); // Gunakan nilai dari txtTotal
        txtSubTotal.setText(Integer.toString(subTotal));
        txtNoFaktur.setText(servis.noTransaksi());
        txtDiskon.setText("0");
        
        hitungTotal();
        
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
         btnCancel.setEnabled(false);
    btnSimpan.setEnabled(false);
    btnNew.setEnabled(true);
    btnCari.setEnabled(false);
    btnHapus.setEnabled(false);
    
    // Reset textfield data barang
    txtBarcode.setText("");
    txtNama.setText("");
    txtHarga.setText("");
    txtStok.setText("");
    txtQty.setText("");
    txtQty.setEditable(false);
    
    // Kosongkan seleksi di tabel
    tbPengeluaran.clearSelection();
    
    // Kembalikan fokus ke textfield barcode
    txtBarcode.requestFocusInWindow();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    
        if(txtQty.getText().length() >= 5){
            evt.consume();
        }
    }//GEN-LAST:event_txtQtyKeyTyped

    private void txtQtyCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtQtyCaretUpdate
        btnSimpan.setEnabled(!txtQty.getText().trim().isEmpty());
    }//GEN-LAST:event_txtQtyCaretUpdate

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        cariBrg dialog = new cariBrg(null, true, "Transaksi");
        dialog.setVisible(true);

        modelBarang selectedBarang = dialog.getSelectedBarang();
        if (selectedBarang != null) {
            
            txtBarcode.setText(selectedBarang.getIdProduk());
            txtNama.setText(selectedBarang.getNamaProduk());
            txtHarga.setText(String.valueOf(selectedBarang.getHargaProduk()));
            txtStok.setText(String.valueOf(selectedBarang.getStokProduk()));
            
            txtQty.setEditable(true);
            txtQty.requestFocus();
            harga = selectedBarang.getHargaProduk();
            stok = selectedBarang.getStokProduk();
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void txtDiskonCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDiskonCaretUpdate
        hitungTotal(); // Panggil metode hitungTotal yang sudah dimodifikasi
    }//GEN-LAST:event_txtDiskonCaretUpdate

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
         String kodeBarang = txtBarcode.getText();
         int jumlahDibeli = Integer.parseInt(txtQty.getText());
         
         boolean stokCukup = servisSmt.cekStok(kodeBarang, jumlahDibeli);
        if(txtQty.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "ISI QTY TERLEBIH DAHULU");
        } else if(!stokCukup){
            JOptionPane.showMessageDialog(this, "Stok barang tidak mencukupi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        } else{
            kode_barang = txtBarcode.getText();
            nama_barang = txtNama.getText();
            qty = Integer.parseInt(txtQty.getText());
            total =(int) harga*qty;
            int total1 = 0;
            int subTotal = 0;
            int hargajual;
            int qty1;
            int total2 = 0;
            try {
               modelRekapTransaksi smt = new modelRekapTransaksi();
        
        smt.getModelBarang().setIdProduk(kode_barang);
        smt.getModelBarang().setNamaProduk(nama_barang);
        smt.getModelBarang().setHargaProduk(harga);
        smt.getModelBarang().setBarcode(txtBarcode.getText());
        
        // Get stock from the text field properly
        smt.getModelBarang().setStokProduk(stok);
        
        smt.getModelPendet().setQty(qty);
        smt.getModelPendet().setNilai(total);
                txtBarcode.setText("");
                txtNama.setText("");
                txtQty.setText("");
                System.out.println("out");
                
                tblModel.insertData(smt);
                for(int n =0;n<tbPengeluaran.getRowCount();n++){
                    hargajual = (int) tbPengeluaran.getValueAt(n, 3);
                    System.out.println(hargajual);
                    qty1 = (int) tbPengeluaran.getValueAt(n, 4);
                    System.out.println(qty1);
                    total1 = hargajual*qty;
                    total2 = (int) tbPengeluaran.getValueAt(n, 5);
                    subTotal += total2;
                }
                System.out.println("Stok yang disimpan: " + smt.getModelBarang().getStokProduk());
                txtTotal2.setText(Integer.toString(subTotal));
                txtSubTotal.setText(Integer.toString(subTotal));
                txtNoFaktur.setText(servis.noTransaksi());
                txtDiskon.setText("0");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

                btnNew.setEnabled(true);
        btnCari.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnHapus.setEnabled(true);
        btnCancel.setEnabled(false);
        txtDiskon.setEditable(true);
        txtBayar.setEditable(true);
        txtQty.setEditable(false);
        txtQty.setText("");
        txtBayar.requestFocus();
        hitungTotal();
        txtNoFaktur.setText(servis.noTransaksi());
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        btnCari.setEnabled(true);
        btnNew.setEnabled(false);
        btnCancel.setEnabled(true);
        txtBarcode.requestFocusInWindow();
    }//GEN-LAST:event_btnNewActionPerformed
private void hitungTotal() {
    // Hitung subtotal dari tabel
    int subTotal = 0;
    for (int i = 0; i < tblModel.getRowCount(); i++) {
        modelRekapTransaksi item = tblModel.getData(i);
        subTotal += item.getModelPendet().getNilai();
    }
    
    // Set nilai subtotal
    txtSubTotal.setText(String.valueOf(subTotal));
    
    // Hitung total setelah diskon (jika ada diskon)
    int diskon = 0;
    if (!txtDiskon.getText().isEmpty()) {
        try {
            diskon = Integer.parseInt(txtDiskon.getText());
        } catch (NumberFormatException e) {
            diskon = 0;
        }
    }
    
    int total = subTotal - (subTotal * diskon / 100);
    txtTotal.setText(String.valueOf(total));
    
    // Ambil nilai dari txtTotal untuk ditampilkan di txtTotal2
    txtTotal2.setText(txtTotal.getText());
}
    private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcodeActionPerformed
        
    }//GEN-LAST:event_txtBarcodeActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (tbPengeluaran.getSelectedRow() == -1) {
        JOptionPane.showMessageDialog(this, "Pilih barang yang akan diupdate terlebih dahulu");
        return;
        
    }
    
    if (txtQty.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "ISI QTY TERLEBIH DAHULU");
        return;
    }
    
    int selectedRow = tbPengeluaran.getSelectedRow();
    String kodeBarang = tbPengeluaran.getValueAt(selectedRow, 2).toString();
    int newQty = Integer.parseInt(txtQty.getText());
    
    
    if (newQty > stok) {
        JOptionPane.showMessageDialog(this, "Stok tidak mencukupi");
        return;
    }
    
    // Update data di tabel
    tbPengeluaran.setValueAt(newQty, selectedRow, 6);
    int newTotal = harga * newQty;
    tbPengeluaran.setValueAt(newTotal, selectedRow, 7);
    
    // Update total keseluruhan
    hitungTotal();
    
    // Reset form
    resetForm();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
            tblModel.clearData();
    
    // Kosongkan semua textfield
    txtBarcode.setText("");
    txtNama.setText("");
    txtHarga.setText("");
    txtStok.setText("");
    txtQty.setText("");
    txtNoFaktur.setText("");
    txtSubTotal.setText("");
    txtDiskon.setText("");
    txtTotal.setText("");
    txtBayar.setText("");
    txtKembalian.setText("");
    txtTotal2.setText("0");
    
    // Reset status tombol
    btnNew.setEnabled(true);
    btnCari.setEnabled(false);
    btnSimpan.setEnabled(false);
    btnUpdate.setEnabled(false);
    btnHapus.setEnabled(false);
    btnCancel.setEnabled(false);
    btnSave.setEnabled(false);

    
    // Set editable status
    txtQty.setEditable(false);
    
    // Fokus ke barcode
    txtBarcode.requestFocusInWindow();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtKembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKembalianActionPerformed
    private void handleBarcodeInput(String barcode) {
    if (barcode.isEmpty()) {
        return;
    }
    
    // Cari barang berdasarkan barcode
    modelBarang barang = servis.cariBarangByBarcode(barcode);

    if (barang != null) {
        // Isi field dengan data barang
        txtBarcode.setText(barang.getIdProduk());
        txtNama.setText(barang.getNamaProduk());
        txtHarga.setText(String.valueOf(barang.getHargaProduk()));
        txtStok.setText(String.valueOf(barang.getStokProduk()));
        harga = barang.getHargaProduk();
        stok = barang.getStokProduk();
        txtQty.setEditable(true);
        txtQty.requestFocus();
    } else {
        JOptionPane.showMessageDialog(this, "Barang dengan barcode " + barcode + " tidak ditemukan");
        txtBarcode.setText("");
        txtBarcode.requestFocus();
    }
    
}
    private void resetForm() {
    txtBarcode.setText("");
    txtNama.setText("");
    txtHarga.setText("");
    txtStok.setText("");
    txtQty.setText("");
    txtQty.setEditable(false);
    btnSimpan.setEnabled(false);
    btnUpdate.setEnabled(false);
    btnHapus.setEnabled(false);
    btnCancel.setEnabled(false);
    btnNew.setEnabled(true);
    txtBarcode.requestFocus();
}
   private void printNota() {
    try {
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat format = job.defaultPage();
        Paper paper = new Paper();
        double width = 226.77;
        double height = 1000; // Bisa diatur sesuai panjang nota

        paper.setSize(width, height);
        paper.setImageableArea(5, 5, width - 10, height - 10); // Margin 5 point di semua sisi
        format.setPaper(paper);

        // Set Printable
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                Font font = new Font("Monospaced", Font.PLAIN, 8);
                g2d.setFont(font);

                int y = 10;
                g2d.drawString("GARREN JAYA", 50, y); y += 12;
                g2d.drawString("Jl. Mastrip", 40, y); y += 12;
                g2d.drawString("Telp: (08) xxxxxxx", 30, y); y += 15;

                g2d.drawLine(0, y, (int) pageFormat.getImageableWidth(), y); y += 12;
                g2d.drawString("No. Faktur: " + txtNoFaktur.getText(), 10, y); y += 12;
                g2d.drawString("Tanggal: " + new Timestamp(System.currentTimeMillis()).toString(), 10, y); y += 15;

                g2d.drawString("----------------------------------------", 0, y); y += 12;
                g2d.drawString(String.format("%-16s %4s %8s", "Nama", "Qty", "Total"), 0, y); y += 12;
                g2d.drawString("----------------------------------------", 0, y); y += 12;

                for (int i = 0; i < tbPengeluaran.getRowCount(); i++) {
                    String nama = tbPengeluaran.getValueAt(i, 2).toString();
                    String qty = tbPengeluaran.getValueAt(i, 4).toString();
                    String total = tbPengeluaran.getValueAt(i, 5).toString();
                    if (nama.length() > 16) nama = nama.substring(0, 13) + "...";
                    g2d.drawString(String.format("%-16s %4s %8s", nama, qty, total), 0, y);
                    y += 12;
                }

                g2d.drawString("----------------------------------------", 0, y); y += 12;
                g2d.drawString("Sub Total : " + txtSubTotal.getText(), 10, y); y += 12;
                g2d.drawString("Diskon    : " + txtDiskon.getText() + "%", 10, y); y += 12;
                g2d.drawString("Total     : " + txtTotal.getText(), 10, y); y += 12;
                g2d.drawString("Bayar     : " + txtBayar.getText(), 10, y); y += 12;
                g2d.drawString("Kembali   : " + txtKembalian.getText(), 10, y); y += 20;

                g2d.drawString("Terima kasih atas kunjungan Anda", 10, y); y += 12;
                g2d.drawString("Barang yang sudah dibeli", 20, y); y += 12;
                g2d.drawString("tidak dapat ditukar/dikembalikan", 10, y);

                return PAGE_EXISTS;
            }
        }, format);

        if (job.printDialog()) {
            job.print();
        }

    } catch (PrinterException ex) {
        JOptionPane.showMessageDialog(this, "Gagal mencetak nota: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
private void reset() {
    // Use the clearData() method from your tableRekapTransaksi model
    tblModel.clearData();
    
    // Kosongkan semua input field
    txtNoFaktur.setText("");
    txtSubTotal.setText("");
    txtDiskon.setText("");
    txtTotal.setText("");
    txtBayar.setText("");
    txtKembalian.setText("");

    btnCari.setEnabled(true);
    btnCancel.setEnabled(true);
    txtQty.setEditable(true);
    btnSave.setEnabled(false);
    btnHapus.setEnabled(true);

    // Fokus ke input awal (jika ada)
    txtNoFaktur.requestFocus();
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private com.raven.swing.Table tbPengeluaran;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKembalian;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoFaktur;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotal2;
    // End of variables declaration//GEN-END:variables
}
