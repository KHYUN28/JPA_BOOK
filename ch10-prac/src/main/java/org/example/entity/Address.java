package org.example.entity;

public class Address {

    private String city;
    private String street;
    private String zipcode;

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
}
