/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.service;

import raven.model.modelUser;
import java.util.List;

/**
 *
 * @author yusuf
 */
public interface serviceUser {
    void tambahData (modelUser model);
    void perbaruiData (modelUser model);
    void hapusData (modelUser model);
    
    List<modelUser> tampilData();
    List<modelUser> pencarianData(String id);
    
    String generateSHA256(String password);
    modelUser prosesLogin(modelUser model);
    modelUser getUserByRFID(String rfid);
    modelUser loginByRFID(String rfid);
    modelUser prosesLoginByRFID(String rfid);
    boolean isRFIDRegistered(String rfid);
    
    boolean validasiPasswordLama(String username, String passwordLama);
    boolean pergantianPassword(String username, String passwordLama, String passwordBaru);

    public List<modelUser> showData();

    public void insertData(modelUser u);

    public void updateData(modelUser u);

    public boolean isUsernameExists(String username);
}
