/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.tablemodel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import raven.model.modelPenjualan;
/**
 *
 * @author yusuf
 */
public class tablePenjualan extends AbstractTableModel{

    private final List<modelPenjualan> list = new ArrayList<>();
    private final String[] columnNames = {"REF", "TANGGAL", "KASIR", "TOTAL HARGA", "BAYAR", "KEMBALIAN"};
    public void insertData(modelPenjualan model) {
        list.add(model);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    }
    
    public void updateData(int row, modelPenjualan model){
        list.set(row, model);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }
    
    public modelPenjualan getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<modelPenjualan> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        modelPenjualan model = list.get(rowIndex);
        DecimalFormat df1 = new DecimalFormat("#,##0");
        String total = df1.format(model.getTotal());
        String bayar = df1.format(model.getBayar());
        String kembalian = df1.format(model.getKembalian());
        switch (columnIndex) {
        case 0:
            return model.getRef();
        case 1:
            return model.getTanggal();
        case 2:
            return model.getKasir();
        case 3:
            return total;
        case 4:
            return bayar;
        case 5:
            return kembalian;
        default:
            return null;
    
    }
    }
    
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
}
