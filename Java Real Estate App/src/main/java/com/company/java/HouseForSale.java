package com.company.java;

import java.time.LocalDate;

//The class created to hold house for sale information
//Unlike the "House" class, it contains the "purchasePrice" value
public class HouseForSale extends House {

    private String purchasePrice;

    public HouseForSale(Integer id, Integer roomCount, String province, String district,
                        String type, LocalDate listingDate, Double area, String purchasePrice) {
        super(id, roomCount, province, district, type, listingDate, area);
        this.purchasePrice = purchasePrice;
    }

    public String getPurchasePrice() { return purchasePrice; }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}