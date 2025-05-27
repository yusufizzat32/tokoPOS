/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.tablemodel;
import raven.model.modelBarangMasuk;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author yusuf
 */
public class tableBarangMasuk extends AbstractTableModel{
    private List<modelBarangMasuk> list = new ArrayList<>();
    
    public modelBarangMasuk getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<modelBarangMasuk> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData(modelBarangMasuk obat){
        list.add(obat);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah");
    }
    
    public void updateData(int row,modelBarangMasuk obat){
        list.set(row, obat);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }

    private final String[] columnNames = {"NO", "KODE BARANG", "NAMA BARANG", "JUMLAH MASUK", "TANGGAL MASUK","TANGGAL KADALUARSA"};

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
        modelBarangMasuk obat = list.get(rowIndex);   
        DecimalFormat df1 = new DecimalFormat("#,##0");
        String jumlah = df1.format(obat.getJumlahMasuk());
        switch(columnIndex){
            case 0:
                return rowIndex + 1;
            case 1:
                return obat.getIdProduk();
            case 2:
                return obat.getNamaProduk();
            case 3:
                return jumlah;
            case 4:
                return obat.getTanggal();
                
            case 5:
                  return obat.getTanggalKadaluarsaFormatted();
            default:
                return null;    
        }
    }
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
    
}
