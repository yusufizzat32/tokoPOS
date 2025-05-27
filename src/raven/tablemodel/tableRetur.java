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
import raven.model.modelRekapTransaksi;
/**
 *
 * @author yusuf
 */
public class tableRetur extends AbstractTableModel {
    private final List<modelRekapTransaksi> list = new ArrayList<>();
    private final String[] columnNames = {"NO", "BARCODE", "NAMA PRODUK", "HARGA", "JUMLAH", "ALASAN"};
    
    public void insertData(modelRekapTransaksi model) {
        list.add(model);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }
    
    public void updateData(int row, modelRekapTransaksi model){
        list.set(row, model);
        fireTableDataChanged();
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    public modelRekapTransaksi getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<modelRekapTransaksi> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 4 || column == 3; // Mengizinkan edit pada kolom QTY (index 4) dan Harga (index 3)
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
        modelRekapTransaksi model = list.get(rowIndex);
        DecimalFormat df1 = new DecimalFormat("#,##0");
        String stok = df1.format(model.getModelBarang().getStokProduk());
        String qty = df1.format(model.getModelPendet().getQty());
        String harga = df1.format(model.getModelBarang().getHargaProduk());
        String nilai = df1.format(model.getModelPendet().getNilai());
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1: 
                return model.getModelBarang().getBarcode();
            case 2: 
                return model.getModelBarang().getIdProduk();
            case 3:
                return model.getModelBarang().getNamaProduk();
            case 4:
                return harga;
            case 5:
                return stok;
            case 6:
                return qty;
            case 7: 
                return nilai;
            default:
                return null;
        }  
    }
 
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
}
