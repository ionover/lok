package org.example.base.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NominationDto {

    private String lat;
    private String lon;
    private String display_name;

    private Long place_id;
    private String licence;
    private String osm_type;
    private Long osm_id;
    private String classs;
    private String type;
    private Long place_rank;
    private String importance;
    private String addresstype;
    private String name;
    private List<Double> boundingbox;

    public NominationDto() {
    }

    public NominationDto(String lat, String lon, String display_name, Long place_id, String licence, String osm_type,
                         Long osm_id, String classs, String type, Long place_rank, String importance,
                         String addresstype, String name, List<Double> boundingbox) {
        this.lat = lat;
        this.lon = lon;
        this.display_name = display_name;
        this.place_id = place_id;
        this.licence = licence;
        this.osm_type = osm_type;
        this.osm_id = osm_id;
        this.classs = classs;
        this.type = type;
        this.place_rank = place_rank;
        this.importance = importance;
        this.addresstype = addresstype;
        this.name = name;
        this.boundingbox = boundingbox;
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

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
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

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
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

    public String getAddresstype() {
        return addresstype;
    }

    public void setAddresstype(String addresstype) {
        this.addresstype = addresstype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getBoundingbox() {
        return boundingbox == null ? new ArrayList<>() : boundingbox;
    }

    public void setBoundingbox(List<Double> boundingbox) {
        this.boundingbox = boundingbox == null ? new ArrayList<Double>() : boundingbox;
    }

    @Override
    public String toString() {
        return "{" +
                "\"lat\":" + (lat == null ? "null" : "\"" + lat + "\"") + ", " +
                "\"lon\":" + (lon == null ? "null" : "\"" + lon + "\"") + ", " +
                "\"display_name\":" + (display_name == null ? "null" : "\"" + display_name + "\"") + ", " +
                "\"place_id\":" + (place_id == null ? "null" : "\"" + place_id + "\"") + ", " +
                "\"licence\":" + (licence == null ? "null" : "\"" + licence + "\"") + ", " +
                "\"osm_type\":" + (osm_type == null ? "null" : "\"" + osm_type + "\"") + ", " +
                "\"osm_id\":" + (osm_id == null ? "null" : "\"" + osm_id + "\"") + ", " +
                "\"classs\":" + (classs == null ? "null" : "\"" + classs + "\"") + ", " +
                "\"type\":" + (type == null ? "null" : "\"" + type + "\"") + ", " +
                "\"place_rank\":" + (place_rank == null ? "null" : "\"" + place_rank + "\"") + ", " +
                "\"importance\":" + (importance == null ? "null" : "\"" + importance + "\"") + ", " +
                "\"addresstype\":" + (addresstype == null ? "null" : "\"" + addresstype + "\"") + ", " +
                "\"name\":" + (name == null ? "null" : "\"" + name + "\"") + ", " +
                "\"boundingbox\":" + (boundingbox == null ? "null" : Arrays.toString(boundingbox.toArray())) +
                "}";
    }
}
