/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.model;

/**
 *
 * @author yusuf
 */
public class modelPenjualanDetail {
    private modelBarang modelBarang1;
    private modelPenjualan modelPenjualan1;
    private int qty;
    private int nilai;
    private String kdTrx;  // Add this field
    // ... existing fields ...
    
    // Add getter and setter
    public String getKdTrx() {
        return kdTrx;
    }
    
    public void setKdTrx(String kdTrx) {
        this.kdTrx = kdTrx;
    }
    
    public modelPenjualanDetail() {
        this.modelBarang1 = new modelBarang(); // Pastikan ini ada
        this.modelPenjualan1 = new modelPenjualan();
    }

    public modelBarang getModelBaraang() {
        return modelBarang1;
    }

    public void setModelBarang(modelBarang modelBarang1) {
        this.modelBarang1 = modelBarang1;
    }

    public modelPenjualan getModelPenjualan() {
        return modelPenjualan1;
    }

    public void setModelPenjualan(modelPenjualan modelPenjualan1) {
        this.modelPenjualan1 = modelPenjualan1;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

}
