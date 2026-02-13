package org.example.base.dto;

public class NominationDto {

    private String place_id;
    private String lat;
    private String lon;
    private String address;

    public NominationDto() {
    }

    public NominationDto(String place_id, String lat, String lon, String address) {
        this.place_id = place_id;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{" +
                "\"place_id\":" + (place_id == null ? "null" : "\"" + place_id + "\"") + ", " +
                "\"lat\":" + (lat == null ? "null" : "\"" + lat + "\"") + ", " +
                "\"lon\":" + (lon == null ? "null" : "\"" + lon + "\"") + ", " +
                "\"address\":" + (address == null ? "null" : "\"" + address + "\"") +
                "}";
    }
}
