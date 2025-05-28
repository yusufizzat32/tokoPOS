/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.model;

/**
 *
 * @author yusuf
 */
public class modelRekapTransaksi {
    private modelPenjualanDetail modelPendet;
    private modelBarang modelBarang;

    public modelRekapTransaksi() {
        this.modelBarang = new modelBarang(); // Pastikan ini ada
        this.modelPendet = new modelPenjualanDetail();
    }
     
    public modelPenjualanDetail getModelPendet() {
        return modelPendet;
    }

    public void setModelPendet(modelPenjualanDetail modelPendet) {
        this.modelPendet = modelPendet;
    }

    public modelBarang getModelBarang() {
        return modelBarang;
    }

    public void setModelBarang(modelBarang modelBarang) {
        this.modelBarang = modelBarang;
    }
}
