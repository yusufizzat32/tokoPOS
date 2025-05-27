package raven.model;
public class modelReturBarang {
    private int idRetur;
    private String ref;
    private int idMember;
    private String alasanRetur;
    private int totalPengembalian;
    private String statusRetur;

    // Constructor
    public modelReturBarang() {}

    public modelReturBarang(int idRetur, String ref, String alasanRetur, int totalPengembalian, String statusRetur) {
        this.idRetur = idRetur;
        this.ref = ref;
        this.idMember = idMember;
        this.alasanRetur = alasanRetur;
        this.totalPengembalian = totalPengembalian;
        this.statusRetur = statusRetur;
    }

    // Getters and Setters
    public int getIdRetur() {
        return idRetur;
    }

    public void setIdRetur(int idRetur) {
        this.idRetur = idRetur;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getAlasanRetur() {
        return alasanRetur;
    }

    public void setAlasanRetur(String alasanRetur) {
        this.alasanRetur = alasanRetur;
    }

    public int getTotalPengembalian() {
        return totalPengembalian;
    }

    public void setTotalPengembalian(int totalPengembalian) {
        this.totalPengembalian = totalPengembalian;
    }

    public String getStatusRetur() {
        return statusRetur;
    }

    public void setStatusRetur(String statusRetur) {
        this.statusRetur = statusRetur;
    }
}
