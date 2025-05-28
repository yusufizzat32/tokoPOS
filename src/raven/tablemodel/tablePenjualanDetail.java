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
import raven.model.modelPenjualanDetail;
/**
 *
 * @author yusuf
 */
public class tablePenjualanDetail extends AbstractTableModel{
    private final List<modelPenjualanDetail> list = new ArrayList<>();
    private final String[] columnNames = {"NO", "KODE", "NAMA OBAT", "HARGA", "QTY", "SUBTOTAL"};
    
    public void insertData(modelPenjualanDetail model) {
        list.add(model);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    }
    
    public void updateData(int row, modelPenjualanDetail model){
        list.set(row, model);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }
    
    public modelPenjualanDetail getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<modelPenjualanDetail> list){
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
//    public Object getValueAt(int rowIndex, int columnIndex) {
////        ModelPenjualanDetail model = list.get(rowIndex);
////        DecimalFormat df1 = new DecimalFormat("#,##0");
////        String total = df1.format(model.getNilai());
////        String qty = df1.format(model.getQty());
////        String harga_obat = df1.format(model.getModelObat().getHargaObat());
////        if(columnIndex == 0){
////            return (rowIndex +1);
////        } else {
////            return switch (columnIndex) {
////                case 1 -> model.getModelObat().getIdObat();
////                case 2 -> model.getModelObat().getNamaObat();
////                case 3 -> harga_obat;
////                case 4 -> qty;
////                case 5 -> total;
////                default -> null;
////            };
////        }
//    }
    
//    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        modelPenjualanDetail model = list.get(rowIndex);
        DecimalFormat df1 = new DecimalFormat("#,##0");
        String total = df1.format(model.getNilai());
        String qty = df1.format(model.getQty());
        String harga_produk = df1.format(model.getModelBaraang().getHargaProduk());
        if(columnIndex == 0){
            return (rowIndex +1);
        } else {
            switch (columnIndex) {
            case 1:
                return model.getModelBaraang().getIdProduk();
            case 2:
                return model.getModelBaraang().getNamaProduk();
            case 3:
                return harga_produk;
            case 4:
                return qty;
            case 5:
                return total;
            default:
                return null;
        }
        }
    }
}
