package com.example.meetfinance;

import com.google.gson.annotations.SerializedName;

public class Profile {
    private String companyName;
    private String industry;
    private String description;
    private String sector;
    private double price;
    private String image;
    @SerializedName("symbol")
    private String symbol;

    public String getCompanyName() {
        return companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public String getDescription() {
        return description;
    }

    public String getSector() {
        return sector;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getSymbol() {
        return symbol;
    }
}
