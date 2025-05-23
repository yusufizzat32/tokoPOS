/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.tablemodel;
import raven.model.modelUser;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import raven.model.modelBarang;

/**
 *
 * @author Olla
 */
public class tableUser extends AbstractTableModel {


     private final List<modelUser> list = new ArrayList<>();
    private final String[] columnNames = {"No", "Id User","Nama", "Username", "Password", "Role", "No Telepon","Alamat"};
    
    public void insertData(modelUser user) {
        list.add(user);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    }
     public void updateData(int row, modelUser user){
        list.set(row, user);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
    }
 public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }
  public modelUser getData(int index){
        return list.get(index);
    }
  public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<modelUser> list){
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
       modelUser user = list.get(rowIndex);
switch (columnIndex) {
          case 0: return rowIndex + 1; // Nomor urut
            case 1: return user.getIdUser();
            case 2: return user.getNama();
            case 3: return user.getUsername();
            case 4: return user.getPassword();
            case 5: return user.getRole();
            case 6: return user.getNo_telepon();
            case 7: return user.getAlamat();
            default: return null;
    }
}

    @Override
    
    public String getColumnName(int column) {
        return columnNames[column];
    }
}

    
   
  

