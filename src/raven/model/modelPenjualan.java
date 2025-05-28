/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.model;
import java.sql.Timestamp;

/**
 *
 * @author yusuf
 */
public class modelPenjualan {
    private String Ref;
    private String Tanggal;
    private String Kasir;
    private double Total;
    private double Bayar;
    private double Kembalian;
    private double Diskon;
    private int IdUser;
    private Timestamp createAt;
    private modelUser modelUser;

    public modelPenjualan() {
        this.modelUser = new modelUser(); // Pastikan ini ada
    }

    public modelUser getModelUser() {
        return modelUser;
    }

    public void setModelUser(modelUser modelUser) {
        this.modelUser = modelUser;
    }

    public String getRef() {
        return Ref;
    }

    public void setRef(String Ref) {
        this.Ref = Ref;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String Tanggal) {
        this.Tanggal = Tanggal;
    }
    
    public String getKasir() {
       return Kasir;
    }

    public void setKasir(String Kasir) {
       this.Kasir = Kasir;
    }
    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public double getBayar() {
        return Bayar;
    }

    public void setBayar(double Bayar) {
        this.Bayar = Bayar;
    }

    public double getDiskon() {
        return Diskon;
    }

    public void setDiskon(double Diskon) {
        this.Diskon = Diskon;
    }

    public double getKembalian() {
        return Kembalian;
    }

    public void setKembalian(double Kembalian) {
        this.Kembalian = Kembalian;
    }
    
     public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }
    public Timestamp getCreateAt() {
    return createAt;
}

public void setCreateAt(Timestamp createAt) {
    this.createAt = createAt;
}

}
