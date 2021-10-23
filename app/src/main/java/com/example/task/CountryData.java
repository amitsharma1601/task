package com.example.task;

public class CountryData {
    String countryName;
    int countryFlagResourceId;

    public CountryData(String countryName, int countryFlagResourceId){
        this.countryName = countryName;
        this.countryFlagResourceId = countryFlagResourceId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryFlagResourceId() {
        return countryFlagResourceId;
    }

    public void setCountryFlagResourceId(int countryFlagResourceId) {
        this.countryFlagResourceId = countryFlagResourceId;
    }
}
