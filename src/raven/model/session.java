/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.model;

/**
 *
 * @author yusuf
 */
public class session {
    private static session instance;
    private String username;
    private int idUser;
    private String role;
    
    public static session getInstance() {
        if (instance == null) {
            instance = new session();
        }
        return instance;
    }
    
    public void setUserSession(int id, String uname, String userRole) {
        idUser = id;
        username = uname;
        role = userRole;
    }
    
    public int getUserId() {
        return idUser;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getRole() {
        return role;
    }
    
    public void clearSession() {
        idUser = 0;
        username = null;
        role = null;
    }
}