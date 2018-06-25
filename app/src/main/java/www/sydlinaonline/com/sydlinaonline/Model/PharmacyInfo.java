package www.sydlinaonline.com.sydlinaonline.Model;

public class PharmacyInfo {

    private String PharmacyName;
    private String PharmacyPhone;
    private String pharmacyLat;
    private String pharmacyLan;
    private String pharmacyKey;



//    public PharmacyInfo() {
//    }

    public PharmacyInfo(String pharmacyName, String pharmacyPhone, String pharmacyLat, String pharmacyLan, String pharmacyKey) {
        PharmacyName = pharmacyName;
        PharmacyPhone = pharmacyPhone;
        this.pharmacyLat = pharmacyLat;
        this.pharmacyLan = pharmacyLan;
        this.pharmacyKey = pharmacyKey;
    }

    public String getPharmacyLat() {
        return pharmacyLat;
    }

    public void setPharmacyLat(String pharmacyLat) {
        this.pharmacyLat = pharmacyLat;
    }

    public String getPharmacyLan() {
        return pharmacyLan;
    }

    public void setPharmacyLan(String pharmacyLan) {
        this.pharmacyLan = pharmacyLan;
    }

    public String getPharmacyKey() {
        return pharmacyKey;
    }

    public void setPharmacyKey(String pharmacyKey) {
        this.pharmacyKey = pharmacyKey;
    }


    public String getPharmacyName() {
        return PharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        PharmacyName = pharmacyName;
    }

    public String getPharmacyPhone() {
        return PharmacyPhone;
    }

    public void setPharmacyPhone(String pharmacyPhone) {
        PharmacyPhone = pharmacyPhone;
    }


}
