package www.sydlinaonline.com.sydlinaonline.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.stream.Stream;

public class PharmacyInfo {

    private String pharmacyName;
    private String pharmacyPhone;
    private String pharmacyLat;
    private String pharmacyLan;
    private String pharmacyKey;



    public PharmacyInfo() {
    }

    public PharmacyInfo(String pharmacyName, String pharmacyPhone, String pharmacyLat, String pharmacyLan, String pharmacyKey) {
        this.pharmacyName = pharmacyName;
        this.pharmacyPhone = pharmacyPhone;
        this.pharmacyLat = pharmacyLat;
        this.pharmacyLan = pharmacyLan;
        this.pharmacyKey = pharmacyKey;
    }

    protected PharmacyInfo(Parcel in) {
        pharmacyName = in.readString();
        pharmacyPhone = in.readString();
        pharmacyLat = in.readString();
        pharmacyLan = in.readString();
        pharmacyKey = in.readString();
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
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyPhone() {
        return pharmacyPhone;
    }

    public void setPharmacyPhone(String pharmacyPhone) {
        this.pharmacyPhone = pharmacyPhone;
    }

}
