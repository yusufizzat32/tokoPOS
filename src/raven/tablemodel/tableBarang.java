/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.tablemodel;

import raven.model.modelBarang;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author yusuf
 */
public class tableBarang extends AbstractTableModel {

    private final List<modelBarang> list = new ArrayList<>();
    private final String[] columnNames = {"NO","KODE", "NAMA OBAT", "HARGA", "STOK", "BARCODE",};
    
    public void insertData(modelBarang barang) {
        list.add(barang);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    }
    
    public void updateData(int row, modelBarang barang){
        list.set(row, barang);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }
    
     public modelBarang getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<modelBarang> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
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
        modelBarang barang = list.get(rowIndex);
        DecimalFormat df1 = new DecimalFormat("#,##0");
        String stok = df1.format(barang.getStokProduk());
        String harga = df1.format(barang.getHargaProduk());
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return barang.getIdProduk();
            case 2:
                return barang.getNamaProduk();
            case 3:
                return harga;
            case 4:
                return stok;
            case 5:
                return barang.getBarcode();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
    
    
}
