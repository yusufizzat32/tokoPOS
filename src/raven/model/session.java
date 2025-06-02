/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.model;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author yusuf
 */
public class session {
    private static session instance;
    private String username;
    private int idUser;
    private String role;
     
    private static final List<String> MENU_ADMIN = Arrays.asList(
        "dashboard", "transaksi", "kasir", "riwayat", 
        "manajemen stok", "master produk", "stok masuk",
        "manajemen user", "data user", "ganti password", "logout"
    );
    
      private static final List<String> MENU_KASIR = Arrays.asList(
        "dashboard", "transaksi", "kasir", "riwayat",
        "manajemen stok", "master produk", "stok masuk", "logout"
    );
      
      private static final List<String> MENU_MANAJEMEN_STOK = Arrays.asList(
        "manajemen stok", "master produk", "stok masuk", "logout"
    );
      
        private session() {}
        
          
          
    public static session getInstance() {
        if (instance == null) {
            instance = new session();
        }
        return instance;
    }
    
    public void setUserSession(int id, String uname, String userRole) {
        idUser = id;
        username = uname;
        role =  userRole.toLowerCase(); 
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
    
       public boolean isAdmin() 
       { return "admin".equals(role); }
    public boolean isKasir()
    
    { return "kasir".equals(role); }
    public boolean isManajemenStok()
    { return "stok".equals(role); }
    
      public boolean bisaAksesMenu(String menuName) {
        menuName = menuName.toLowerCase();
        
        if (isAdmin()) {
            return MENU_ADMIN.contains(menuName);
        }
        if (isKasir()) {
            return MENU_KASIR.contains(menuName);
        }
        if (isManajemenStok()) {
            return MENU_MANAJEMEN_STOK.contains(menuName);
        }
        return false;
    }
      public String[][] filterMenu(String[][] semuaMenu) {
        return Arrays.stream(semuaMenu)
            .map(grupMenu -> Arrays.stream(grupMenu)
                .filter(menu -> bisaAksesMenu(menu))
                .toArray(String[]::new))
            .filter(grupMenu -> grupMenu.length > 0)
            .toArray(String[][]::new);
    }   
      
}