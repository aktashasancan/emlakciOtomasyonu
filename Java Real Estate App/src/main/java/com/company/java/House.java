package com.company.java;

import java.time.LocalDate;

//Abstract class inherited from "HouseForRent" and "HouseForSale" classes.
public abstract class House {

    //Standard house informations
    private Integer id, roomCount;
    private String province, district, type;
    private LocalDate listingDate;
    private Double area;

    public House(Integer id, Integer roomCount, String province, String district, String type,
                 LocalDate listingDate, Double area) {
        this.id = id;
        this.roomCount = roomCount;
        this.province = province;
        this.district = district;
        this.type = type;
        this.listingDate = listingDate;
        this.area = area;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getRoomCount() { return roomCount; }

    public void setRoomCount(Integer roomCount) { this.roomCount = roomCount; }

    public String getProvince() { return province; }

    public void setProvince(String province) { this.province = province; }

    public String getDistrict() { return district; }

    public void setDistrict(String district) { this.district = district; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public LocalDate getListingDate() { return listingDate; }

    public void setListingDate(LocalDate listingDate) { this.listingDate = listingDate; }

    public Double getArea() { return area; }

    public void setArea(Double area) { this.area = area; }
}
