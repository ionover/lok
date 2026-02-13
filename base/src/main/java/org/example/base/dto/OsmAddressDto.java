package org.example.base.dto;

public class OsmAddressDto {

    private String municipality;
    private String county;
    private String state;
    private String region;
    private String postcode;
    private String country;
    private String country_code;

    public OsmAddressDto() {
    }

    public OsmAddressDto(String municipality,
                         String county,
                         String state,
                         String region,
                         String postcode,
                         String country,
                         String country_code) {
        this.municipality = municipality;
        this.county = county;
        this.state = state;
        this.region = region;
        this.postcode = postcode;
        this.country = country;
        this.country_code = country_code;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    @Override
    public String toString() {
        return "{" +
                "\"municipality\":" + (municipality == null ? "null" : "\"" + municipality + "\"") + ", " +
                "\"county\":" + (county == null ? "null" : "\"" + county + "\"") + ", " +
                "\"state\":" + (state == null ? "null" : "\"" + state + "\"") + ", " +
                "\"region\":" + (region == null ? "null" : "\"" + region + "\"") + ", " +
                "\"postcode\":" + (postcode == null ? "null" : "\"" + postcode + "\"") + ", " +
                "\"country\":" + (country == null ? "null" : "\"" + country + "\"") + ", " +
                "\"country_code\":" + (country_code == null ? "null" : "\"" + country_code + "\"") +
                "}";
    }
}
