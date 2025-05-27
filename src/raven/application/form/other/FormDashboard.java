package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import raven.dao.penjualanDAO;
import raven.model.modelPenjualan;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public final class FormDashboard extends javax.swing.JPanel {
    private penjualanDAO penjualanDao;
    public FormDashboard() {
        initComponents();
        penjualanDao = new penjualanDAO();
        // Inisialisasi combobox
        String[] rentangWaktu = {"7 Hari Terakhir", "1 Bulan Terakhir", "1 Tahun Terakhir"};
        cbxRentangWaktu.setModel(new javax.swing.DefaultComboBoxModel<>(rentangWaktu));
    
        // Tambahkan action listener
        cbxRentangWaktu.addActionListener(e -> updateChart());
        showPenjualanPerHariChart();
        updatePendapatanHariIni();
        updateTotalJumlahTransaksi();
        updateTotalPendapatan();
    }

    
    
    private void updateChart() {
        String selectedPeriod = (String) cbxRentangWaktu.getSelectedItem();
        showPenjualanChart(selectedPeriod);
    }
    public void showPenjualanChart(String period) {
    List<modelPenjualan> dataPenjualan = penjualanDao.getPenjualanByPeriod(period);

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (modelPenjualan penjualan : dataPenjualan) {
        dataset.addValue(penjualan.getTotal(), "Penjualan", penjualan.getTanggal());
    }

    String xAxisLabel = "Tanggal";
    if ("1 Tahun Terakhir".equals(period)) {
        xAxisLabel = "Bulan-Tahun";
    }

    JFreeChart chart = ChartFactory.createLineChart(
        "Penjualan " + period, 
        xAxisLabel,           
        "Total Penjualan",     
        dataset,              
        PlotOrientation.VERTICAL,
        true,                
        true,                 
        false                
    );  

    CategoryPlot plot = chart.getCategoryPlot();
    plot.setBackgroundPaint(Color.WHITE);
    LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
    renderer.setSeriesPaint(0, new Color(204, 0, 51));

    ChartPanel chartPanel = new ChartPanel(chart);
    panelLineChart.removeAll();
    panelLineChart.add(chartPanel, BorderLayout.CENTER);
    panelLineChart.validate();
}
    /*=============================================================================*/
    private void updatePendapatanHariIni() {
        double pendapatan = penjualanDao.getPendapatanHariIni();
        labelPendapatanHariIni.setText(String.format("Rp%,.2f", pendapatan));
    }
    private void updateTotalJumlahTransaksi() {
        int totalTransaksi = penjualanDao.getTotalJumlahTransaksi();
        labelJumlahTransaksi.setText(String.valueOf(totalTransaksi));
    }
    private void updateTotalPendapatan() {
        double totalPendapatan = penjualanDao.getTotalPendapatan();
        labelTotalPendapatan.setText(String.format("Rp%,.2f", totalPendapatan));
    }
    
    public void showPenjualanPerHariChart() {
        List<modelPenjualan> dataPenjualan = penjualanDao.getPenjualanPerHari();


        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (modelPenjualan penjualan : dataPenjualan) {
            dataset.addValue(penjualan.getTotal(), "Penjualan", penjualan.getTanggal());
        }

        JFreeChart chart = ChartFactory.createLineChart(
            "Penjualan Per Hari", 
            "Tanggal",           
            "Total Penjualan",     
            dataset,              
            PlotOrientation.VERTICAL,
            true,                
            true,                 
            false                
        );  


        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(204, 0, 51));


        ChartPanel chartPanel = new ChartPanel(chart);
        panelLineChart.removeAll();
        panelLineChart.add(chartPanel, BorderLayout.CENTER);
        panelLineChart.validate();
    }
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLineChart = new javax.swing.JPanel();
        panelTotalTransaksi = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelJumlahTransaksi = new javax.swing.JLabel();
        panelTotalTransaksi1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelPendapatanHariIni = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        labelTotalPendapatan = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cetakLaporan = new javax.swing.JButton();
        cetakLaporanStokMasuk = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cbxRentangWaktu = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setMaximumSize(null);
        setMinimumSize(null);
        setName(""); // NOI18N

        panelLineChart.setBackground(new java.awt.Color(204, 204, 204));
        panelLineChart.setLayout(new java.awt.BorderLayout());

        panelTotalTransaksi.setBackground(new java.awt.Color(46, 204, 113));

        jLabel1.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("JUMLAH TRANSAKSI");

        labelJumlahTransaksi.setFont(new java.awt.Font("Helvetica", 0, 36)); // NOI18N
        labelJumlahTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        labelJumlahTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelJumlahTransaksi.setText("label");

        javax.swing.GroupLayout panelTotalTransaksiLayout = new javax.swing.GroupLayout(panelTotalTransaksi);
        panelTotalTransaksi.setLayout(panelTotalTransaksiLayout);
        panelTotalTransaksiLayout.setHorizontalGroup(
            panelTotalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTotalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTotalTransaksiLayout.createSequentialGroup()
                        .addComponent(labelJumlahTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTotalTransaksiLayout.setVerticalGroup(
            panelTotalTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelJumlahTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTotalTransaksi1.setBackground(new java.awt.Color(46, 204, 113));

        jLabel2.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PENDAPATAN HARI INI");

        labelPendapatanHariIni.setFont(new java.awt.Font("Helvetica", 0, 36)); // NOI18N
        labelPendapatanHariIni.setForeground(new java.awt.Color(255, 255, 255));
        labelPendapatanHariIni.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPendapatanHariIni.setText("label");

        javax.swing.GroupLayout panelTotalTransaksi1Layout = new javax.swing.GroupLayout(panelTotalTransaksi1);
        panelTotalTransaksi1.setLayout(panelTotalTransaksi1Layout);
        panelTotalTransaksi1Layout.setHorizontalGroup(
            panelTotalTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalTransaksi1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTotalTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPendapatanHariIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTotalTransaksi1Layout.setVerticalGroup(
            panelTotalTransaksi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalTransaksi1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPendapatanHariIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel.setBackground(new java.awt.Color(46, 204, 113));

        labelTotalPendapatan.setFont(new java.awt.Font("Helvetica", 0, 36)); // NOI18N
        labelTotalPendapatan.setForeground(new java.awt.Color(255, 255, 255));
        labelTotalPendapatan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotalPendapatan.setText("label");

        jLabel3.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TOTAL PENDAPATAN");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(labelTotalPendapatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTotalPendapatan, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Helvetica", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("GENERAL REPORT - TOKO GARREN JAYA 2");

        cetakLaporan.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        cetakLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/cashier 1.png"))); // NOI18N
        cetakLaporan.setText("CETAK LAPORAN TRANSAKSI");
        cetakLaporan.setIconTextGap(10);

        cetakLaporanStokMasuk.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        cetakLaporanStokMasuk.setText("CETAK LAPORAN STOK MASUK");

        jButton1.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        jButton1.setText("CETAK MASTER PRODUK");

        cbxRentangWaktu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxRentangWaktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRentangWaktuActionPerformed(evt);
            }
        });

        jLabel5.setText("Rentang Waktu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelTotalTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cetakLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelTotalTransaksi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cetakLaporanStokMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxRentangWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 1583, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTotalTransaksi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTotalTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cetakLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(cetakLaporanStokMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxRentangWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxRentangWaktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRentangWaktuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxRentangWaktuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxRentangWaktu;
    private javax.swing.JButton cetakLaporan;
    private javax.swing.JButton cetakLaporanStokMasuk;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel labelJumlahTransaksi;
    private javax.swing.JLabel labelPendapatanHariIni;
    private javax.swing.JLabel labelTotalPendapatan;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelLineChart;
    private javax.swing.JPanel panelTotalTransaksi;
    private javax.swing.JPanel panelTotalTransaksi1;
    // End of variables declaration//GEN-END:variables
}