package org.example.base.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.type.SqlTypes;


import java.time.LocalDateTime;

@Entity
@Table(name = "geo_cache", schema = "public")
public class GeoCache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coordinates")
    private String coordinates;

    @Column(name = "coordinates_hash")
    @Size(max = 255)
    private String coordinatesHash;

    @Column(name = "address")
    private String address;

    @Column(name = "address_hash")
    @Size(max = 255)
    private String addressHash;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public GeoCache() {
    }

    public GeoCache(Long id, String coordinates, String coordinatesHash,
                    String address, String addressHash, LocalDateTime createdAt) {
        this.id = id;
        this.coordinates = coordinates;
        this.coordinatesHash = coordinatesHash;
        this.address = address;
        this.addressHash = addressHash;
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCoordinatesHash() {
        return coordinatesHash;
    }

    public void setCoordinatesHash(String coordinatesHash) {
        this.coordinatesHash = coordinatesHash;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressHash() {
        return addressHash;
    }

    public void setAddressHash(String addressHash) {
        this.addressHash = addressHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "GeoCache{" +
                "id=" + id +
                ", coordinates=" + coordinates +
                ", coordinatesHash='" + coordinatesHash + '\'' +
                ", address=" + address +
                ", addressHash='" + addressHash + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
