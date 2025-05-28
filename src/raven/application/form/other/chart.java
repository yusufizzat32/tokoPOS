/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package raven.application.form.other;

import java.awt.BorderLayout;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author yusuf
 */
public class chart extends javax.swing.JFrame {

    /**
     * Creates new form chart
     */
    public chart() {
        initComponents();
        showPieChart();
    }
    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      barDataset.setValue( "IPhone 5s" , new Double( 20 ) );  
      barDataset.setValue( "SamSung Grand" , new Double( 20 ) );   
      barDataset.setValue( "MotoG" , new Double( 40 ) );    
      barDataset.setValue( "Nokia Lumia" , new Double( 10 ) );  
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("mobile sales",barDataset, false,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelBarChart.removeAll();
        panelBarChart.add(barChartPanel, BorderLayout.CENTER);
        panelBarChart.validate();
    }

    /*=============================================================================*/
    
    public void showLineChart(){
        //create dataset for the graph
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "Amount", "january");
        dataset.setValue(150, "Amount", "february");
        dataset.setValue(18, "Amount", "march");
        dataset.setValue(100, "Amount", "april");
        dataset.setValue(80, "Amount", "may");
        dataset.setValue(250, "Amount", "june");
        
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("contribution","monthly","amount", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        
        //create plot object
         CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
       // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        
        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204,0,51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        
         //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        panelLineChart.removeAll();
        panelLineChart.add(lineChartPanel, BorderLayout.CENTER);
        panelLineChart.validate();
    }

    /*========================================================================================*/
    


    /*========================================================================================*/
    
    public void showBarChart(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "Amount", "january");
        dataset.setValue(150, "Amount", "february");
        dataset.setValue(18, "Amount", "march");
        dataset.setValue(100, "Amount", "april");
        dataset.setValue(80, "Amount", "may");
        dataset.setValue(250, "Amount", "june");
        
        JFreeChart chart = ChartFactory.createBarChart("contribution","monthly","amount", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
        showBarChart.removeAll();
        showBarChart.add(barpChartPanel, BorderLayout.CENTER);
        showBarChart.validate();
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelBarChart = new javax.swing.JPanel();
        panelLineChart = new javax.swing.JPanel();
        showBarChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        panelBarChart.setBackground(new java.awt.Color(52, 152, 219));

        javax.swing.GroupLayout panelBarChartLayout = new javax.swing.GroupLayout(panelBarChart);
        panelBarChart.setLayout(panelBarChartLayout);
        panelBarChartLayout.setHorizontalGroup(
            panelBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        panelBarChartLayout.setVerticalGroup(
            panelBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        getContentPane().add(panelBarChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 230));

        panelLineChart.setBackground(new java.awt.Color(52, 152, 219));

        javax.swing.GroupLayout panelLineChartLayout = new javax.swing.GroupLayout(panelLineChart);
        panelLineChart.setLayout(panelLineChartLayout);
        panelLineChartLayout.setHorizontalGroup(
            panelLineChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        panelLineChartLayout.setVerticalGroup(
            panelLineChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        getContentPane().add(panelLineChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 280, -1));

        showBarChart.setBackground(new java.awt.Color(52, 152, 219));

        javax.swing.GroupLayout showBarChartLayout = new javax.swing.GroupLayout(showBarChart);
        showBarChart.setLayout(showBarChartLayout);
        showBarChartLayout.setHorizontalGroup(
            showBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        showBarChartLayout.setVerticalGroup(
            showBarChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        getContentPane().add(showBarChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelBarChart;
    private javax.swing.JPanel panelLineChart;
    private javax.swing.JPanel showBarChart;
    // End of variables declaration//GEN-END:variables
}
