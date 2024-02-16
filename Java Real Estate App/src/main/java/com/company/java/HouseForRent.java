package com.company.java;

import java.time.LocalDate;

//The class created to hold rental home information
//Unlike the "House" class, it contains the "rent" value
public class HouseForRent extends House {

    private String rent;

    public HouseForRent(Integer id, Integer roomCount, String province, String district,
                        String type, LocalDate listingDate, Double area, String rent) {
        super(id, roomCount, province, district, type, listingDate, area);
        this.rent = rent;
    }

    public String getRent() { return rent; }

    public void setRent(String rent) { this.rent = rent; }
}
