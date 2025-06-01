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
import raven.model.modelReturBarang;
/**
 *
 * @author revi
 */
public class tableRetur extends AbstractTableModel {
    private List<modelReturBarang> list = new ArrayList<>();
    private final String[] columnNames = {"No", "Referensi", "Alasan Retur", "Total Pengembalian", "Status"};
    

    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    public modelReturBarang getData(int row) {
        return list.get(row);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
     public void setData(List<modelReturBarang> list) {
        this.list = list;
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
        modelReturBarang retur = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return retur.getIdRetur();
            case 1: return retur.getRef();
            case 2: return retur.getAlasanRetur();
            case 3: return retur.getTotalPengembalian();
            case 4: return retur.getStatusRetur();
            default: return null;
        }
    }
 
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
}
