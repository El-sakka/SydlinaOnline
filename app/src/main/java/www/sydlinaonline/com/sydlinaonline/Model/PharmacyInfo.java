package www.sydlinaonline.com.sydlinaonline.Model;

public class PharmacyInfo {

    private String PharmacyName;
    private String PharmacyPhone;
    private String pharmacyAddress;
    private String pharmacyKey;

    public PharmacyInfo() {

    }

    public PharmacyInfo(String pharmacyName, String pharmacyPhone, String pharmacyAddress, String pharmacyKey) {
        PharmacyName = pharmacyName;
        PharmacyPhone = pharmacyPhone;
        this.pharmacyAddress = pharmacyAddress;
        this.pharmacyKey = pharmacyKey;
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

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
    }
}
