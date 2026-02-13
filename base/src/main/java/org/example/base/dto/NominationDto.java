package org.example.base.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NominationDto {

    private String lat;
    private String lon;
    private String displayName;

    private Long place_id;
    private String licence;
    private String osm_type;
    private Long osm_id;
    private String type;
    private Long place_rank;
    private String importance;
    private String addressType;
    private String name;
    private List<Double> boundingBox;
    private OsmAddressDto address;

    public NominationDto() {
    }

    public NominationDto(String lat, String lon, String displayName, Long place_id, String licence, String osm_type,
                         Long osm_id, String type, Long place_rank, String importance, String addressType, String name,
                         List<Double> boundingBox, OsmAddressDto address) {
        this.lat = lat;
        this.lon = lon;
        this.displayName = displayName;
        this.place_id = place_id;
        this.licence = licence;
        this.osm_type = osm_type;
        this.osm_id = osm_id;
        this.type = type;
        this.place_rank = place_rank;
        this.importance = importance;
        this.addressType = addressType;
        this.name = name;
        this.boundingBox = boundingBox;
        this.address = address;
    }

    public String getLat() {
        return lat == null ? "" : lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon == null ? "" : lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Long place_id) {
        this.place_id = place_id;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getOsm_type() {
        return osm_type;
    }

    public void setOsm_type(String osm_type) {
        this.osm_type = osm_type;
    }

    public Long getOsm_id() {
        return osm_id;
    }

    public void setOsm_id(Long osm_id) {
        this.osm_id = osm_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPlace_rank() {
        return place_rank;
    }

    public void setPlace_rank(Long place_rank) {
        this.place_rank = place_rank;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getBoundingBox() {
        return boundingBox == null ? new ArrayList<>() : boundingBox;
    }

    public void setBoundingBox(List<Double> boundingBox) {
        this.boundingBox = boundingBox == null ? new ArrayList<Double>() : boundingBox;
    }

    public OsmAddressDto getAddress() {
        return address;
    }

    public void setAddress(OsmAddressDto address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{" +
                "\"lat\":" + (lat == null ? "null" : "\"" + lat + "\"") + ", " +
                "\"lon\":" + (lon == null ? "null" : "\"" + lon + "\"") + ", " +
                "\"display_name\":" + (displayName == null ? "null" : "\"" + displayName + "\"") + ", " +
                "\"place_id\":" + (place_id == null ? "null" : "\"" + place_id + "\"") + ", " +
                "\"licence\":" + (licence == null ? "null" : "\"" + licence + "\"") + ", " +
                "\"osm_type\":" + (osm_type == null ? "null" : "\"" + osm_type + "\"") + ", " +
                "\"osm_id\":" + (osm_id == null ? "null" : "\"" + osm_id + "\"") + ", " +
                "\"type\":" + (type == null ? "null" : "\"" + type + "\"") + ", " +
                "\"place_rank\":" + (place_rank == null ? "null" : "\"" + place_rank + "\"") + ", " +
                "\"importance\":" + (importance == null ? "null" : "\"" + importance + "\"") + ", " +
                "\"addresstype\":" + (addressType == null ? "null" : "\"" + addressType + "\"") + ", " +
                "\"name\":" + (name == null ? "null" : "\"" + name + "\"") + ", " +
                "\"boundingbox\":" + (boundingBox == null ? "null" : Arrays.toString(boundingBox.toArray())) +
                "\"osmAddress\":" + (address == null ? "null" : "\"" + address + "\"") + ", " +
                "}";
    }
}
