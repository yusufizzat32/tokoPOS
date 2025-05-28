package raven.application.form.other;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import raven.model.session;


/**
 *
 * @author Raven
 */
public class FormKasir extends javax.swing.JPanel {
        private final tableRekapTransaksi tblModel = new tableRekapTransaksi();
        private final serviceRekapTransaksi servisSmt = new rekapTransaksiDAO();
        private final servicePenjualanDetail servisDetail = new penjualanDetailDAO();
        private final servicePenjualan servis = new penjualanDAO();
//    private final lp = new LaporanDAO();
    
        public String kode_barang;
        public String nama_barang;
        public static int qty;
        public static int harga;
        public static double stok;
        public int total;
        
        public FormKasir() {
            initComponents();
            tbPengeluaran.setModel(tblModel);
            loadData();
            btnHapus.setEnabled(false);
            btnSimpan.setEnabled(false);
            tbPengeluaran.getTableHeader().setBackground(new Color(46,204,113));
            tbPengeluaran.getTableHeader().setForeground(new Color(39,174,96));
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
        txtSubTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        txtKembalian = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
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
        btnHitungTotal = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPengeluaran = new com.raven.swing.Table();

        jLabel2.setText("jLabel2");

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(46, 204, 113));
        jLabel1.setText("KASIR GARREN JAYA");

        jLabel4.setText("TOTAL");

        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcodeActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnUpdate.setText("PERBARUI");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel6.setText("BARCODE");

        txtNoFaktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoFakturActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("No. Faktur");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Sub Total");

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Total");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Bayar");

        txtBayar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBayarCaretUpdate(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Kembalian");

        btnSave.setText("save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCetak.setText("cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel13.setText("NAMA");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel14.setText("HARGA");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
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

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnNew.setText("new");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnHitungTotal.setText("hitung");
        btnHitungTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungTotalActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addGap(18, 18, 18)
                                .addComponent(btnNew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus)
                                .addGap(3, 3, 3)
                                .addComponent(btnCancel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTotal2, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
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
                                        .addComponent(txtNoFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 645, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCetak, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHitungTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNoFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnHitungTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCetak)))
                .addGap(94, 94, 94))
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
        int totall = Integer.parseInt(totalStr);
        int kembali = bayar - totall;

        txtKembalian.setText(Integer.toString(kembali));
        btnSave.setEnabled(true);
        btnReset.setEnabled(true);
    } catch (NumberFormatException ex) {
        // Optional: tampilkan pesan error/log
        txtKembalian.setText("0");
        btnSave.setEnabled(false);
        btnReset.setEnabled(false);
    }
    }//GEN-LAST:event_txtBayarCaretUpdate

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
          try {
        int totalall = Integer.parseInt(txtTotal.getText().replace(",", "").trim());
        int diskon = Integer.parseInt(txtDiskon.getText().replace(",", "").trim());
        int bayar = Integer.parseInt(txtBayar.getText().replace(",", "").trim());
        int kembali = Integer.parseInt(txtKembalian.getText().replace(",", "").trim());
       
        LocalDate currentDate = LocalDate.now();

        if (bayar < totalall) {
            JOptionPane.showMessageDialog(null, "PEMBAYARAN KURANG");
            return;
        }

        String ref = txtNoFaktur.getText();
        modelPenjualan mp = new modelPenjualan();
        session sess = session.getInstance();
        int idUser = sess.getUserId();
        mp.setCreateAt(new Timestamp(System.currentTimeMillis()));
        mp.setRef(ref);
        mp.setBayar(bayar);
        mp.setTotal(totalall);
        mp.setKembalian(kembali);
        mp.setTanggal(currentDate.toString());
        mp.setIdUser(idUser);
        servis.tambahPenjualan(mp);

        for (int n = 0; n < tbPengeluaran.getRowCount(); n++) {
            String kode_barangB = tbPengeluaran.getValueAt(n, 2).toString(); // ID PRODUK
            String nama_barangB = tbPengeluaran.getValueAt(n, 3).toString(); // NAMA PRODUK

            // Ambil dan bersihkan format angka
            String hargaStr = tbPengeluaran.getValueAt(n, 4).toString().replace(",", "").trim();
            String qtyStr = tbPengeluaran.getValueAt(n, 6).toString().replace(",", "").trim();
            String totalStr = tbPengeluaran.getValueAt(n, 7).toString().replace(",", "").trim();

            int hargaB = Integer.parseInt(hargaStr);
            int qtyB = Integer.parseInt(qtyStr);
            int totallB = Integer.parseInt(totalStr);

            modelPenjualanDetail smt = new modelPenjualanDetail();
            smt.getModelPenjualan().setRef(ref);
            smt.getModelBaraang().setIdProduk(kode_barangB);
            smt.getModelBaraang().setNamaProduk(nama_barangB);
            smt.getModelBaraang().setHargaProduk(hargaB);
            smt.setQty(qtyB);
            smt.setNilai(totallB);
            servisDetail.tambah_detail_P(smt);
        }

        btnSave.setEnabled(false);
        btnCetak.setEnabled(true);
        btnNew.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Format angka tidak valid: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
        e.printStackTrace(); // debug log
    }

    btnNew.setEnabled(false);
       
    }//GEN-LAST:event_btnSaveActionPerformed
//    
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
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
        txtTotal2.setText(Integer.toString(subTotal));
        txtSubTotal.setText(Integer.toString(subTotal));
        txtDiskon.setText("0");
        
        hitungTotal();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        btnCancel.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnNew.setEnabled(true);
        btnCari.setEnabled(false);
        btnHapus.setEnabled(false);
        txtQty.setEditable(false);
        txtQty.setText("");
        tbPengeluaran.clearSelection();
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
        // Set nilai ke textfield
        txtBarcode.setText(selectedBarang.getIdProduk());
        txtNama.setText(selectedBarang.getNamaProduk());
        txtHarga.setText(String.valueOf(selectedBarang.getHargaProduk()));
        txtStok.setText(String.valueOf(selectedBarang.getStokProduk()));
        
        // Aktifkan input qty
        txtQty.setEditable(true);
        txtQty.requestFocus();
        
        // Set harga dan stok untuk digunakan nanti
        harga = selectedBarang.getHargaProduk();
         stok = selectedBarang.getStokProduk();
        
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void txtDiskonCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDiskonCaretUpdate
        if(txtDiskon.getText().length()>0){
            int subtotal = Integer.parseInt(txtSubTotal.getText());
            int diskon = Integer.parseInt(txtDiskon.getText());
            int ndiskon = subtotal*diskon/100;
            int totalq= subtotal - ndiskon;
            txtTotal.setText(Integer.toString(totalq));
        }
    }//GEN-LAST:event_txtDiskonCaretUpdate

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if(txtQty.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "ISI QTY TERLEBIH DAHULU");
        } else {
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
                smt.getModelPendet().setQty(qty);
                smt.getModelPendet().setNilai(total);
                txtBarcode.setText("");
                txtNama.setText("");
                txtQty.setText("");
                System.out.println("out");
                //                servisSmt.tambahData(smt);
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
    }//GEN-LAST:event_btnNewActionPerformed
    private void hitungTotal() {
    tableRekapTransaksi model = (tableRekapTransaksi) tbPengeluaran.getModel();
    int grandTotal = 0;
    
    try {
        for (int i = 0; i < model.getRowCount(); i++) {
            // Ambil nilai SUBTOTAL dari kolom 7 (indeks 7)
            Object value = model.getValueAt(i, 7); 
            if (value != null) {
                // Hilangkan tanda koma dan karakter non-digit
                String totalStr = value.toString().replaceAll("[^\\d]", "");
                if (!totalStr.isEmpty()) {
                    int totalBarang = Integer.parseInt(totalStr);
                    grandTotal += totalBarang;
                }
            }
        }
        
        // Update total
        txtSubTotal.setText(String.valueOf(grandTotal));
        txtTotal.setText(String.valueOf(grandTotal));
        txtTotal2.setText(String.valueOf(grandTotal));
        
        // Reset field pembayaran
        txtDiskon.setText("");
        txtBayar.setText("");
        txtKembalian.setText("");
        
        
        txtBayar.requestFocus();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Format angka tidak valid: " + e.getMessage());
    }
}
    private void btnHitungTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungTotalActionPerformed
        tableRekapTransaksi model = (tableRekapTransaksi) tbPengeluaran.getModel();
    int grandTotal = 0;

    try {
        for (int i = 0; i < model.getRowCount(); i++) {
            // Ambil nilai SUBTOTAL dari kolom 5 (indeks 5)
            Object value = model.getValueAt(i, 5); 
            if (value != null) {
                String totalStr = value.toString().replaceAll("[^\\d]", "");
                if (!totalStr.isEmpty()) {
                    int totalBarang = Integer.parseInt(totalStr);
                    grandTotal += totalBarang;
                }
            }
        }

        // Update total
        txtSubTotal.setText(String.valueOf(grandTotal));
        txtTotal.setText(String.valueOf(grandTotal));
        txtTotal2.setText(String.valueOf(grandTotal));

        // Reset field pembayaran jika diskon 0
        if (txtDiskon.getText().isEmpty() || txtDiskon.getText().equals("0")) {
            txtTotal.setText(String.valueOf(grandTotal));
        } else {
            // Jika ada diskon, hitung ulang dengan diskon
            int diskon = Integer.parseInt(txtDiskon.getText());
            int totalDiskon = grandTotal - (grandTotal * diskon / 100);
            txtTotal.setText(String.valueOf(totalDiskon));
        }

        txtBayar.requestFocus();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Format angka tidak valid: " + e.getMessage());
        hitungTotal();
    }//GEN-LAST:event_btnHitungTotalActionPerformed
    }
    private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcodeActionPerformed
        
    }//GEN-LAST:event_txtBarcodeActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCetakActionPerformed

    private void txtNoFakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoFakturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoFakturActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

    // Reset field input
    txtNoFaktur.setText("");
    txtSubTotal.setText("0");
    txtTotal.setText("");
    txtDiskon.setText("0");
    txtBayar.setText("");
    txtKembalian.setText("");
    
    // Reset field barang
    txtBarcode.setText("");
    txtNama.setText("");
    txtHarga.setText("");
    txtStok.setText("");
    txtQty.setText("");
    
    // Reset tabel
    tblModel.clear();
    
    // Generate nomor faktur baru
    txtNoFaktur.setText(servis.noTransaksi());
    
    // Reset status tombol
    btnSimpan.setEnabled(false);
    btnHapus.setEnabled(false);
    btnNew.setEnabled(true);
    txtQty.setEditable(false);
    btnReset.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        resetForm();
    }

        private void resetForm() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    });
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed
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

        // Set harga dan stok untuk digunakan nanti
        harga = barang.getHargaProduk();
        stok = barang.getStokProduk();

        // Fokuskan ke field qty
        txtQty.setEditable(true);
        txtQty.requestFocus();
    } else {
        JOptionPane.showMessageDialog(this, "Barang dengan barcode " + barcode + " tidak ditemukan");
        txtBarcode.setText("");
        txtBarcode.requestFocus();
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHitungTotal;
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
