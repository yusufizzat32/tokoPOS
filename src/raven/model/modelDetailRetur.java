package raven.model;
/**
 *
 * @author revi
 */
public class modelDetailRetur {
    private int idDetailRetur;
    private int idRetur;
    private String kdProduk;
    private int quantity;
    private int hargaSatuan;
    private String alasan;
    private int subtotal;

    // Constructor
    public modelDetailRetur() {}

    public modelDetailRetur(int idDetailRetur, int idRetur, String kdProduk, int quantity, int hargaSatuan, String alasan, int subtotal) {
        this.idDetailRetur = idDetailRetur;
        this.idRetur = idRetur;
        this.kdProduk = kdProduk;
        this.quantity = quantity;
        this.hargaSatuan = hargaSatuan;
        this.alasan = alasan;
        this.subtotal = subtotal;
    }

    // Getters and Setters
    public int getIdDetailRetur() {
        return idDetailRetur;
    }

    public void setIdDetailRetur(int idDetailRetur) {
        this.idDetailRetur = idDetailRetur;
    }

    public int getIdRetur() {
        return idRetur;
    }

    public void setIdRetur(int idRetur) {
        this.idRetur = idRetur;
    }

    public String getKdProduk() {
        return kdProduk;
    }

    public void setKdProduk(String kdProduk) {
        this.kdProduk = kdProduk;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(int hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
