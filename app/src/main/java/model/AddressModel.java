package model;

public class AddressModel {
    String mobile;
    String address;

    public AddressModel(String mobile, String address) {
        this.mobile = mobile;
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }
}
