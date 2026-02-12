package org.example.base.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tools.jackson.databind.JsonNode;

import java.time.LocalDateTime;

@Entity
@Table(name = "geo_cashe", schema = "public")
public class GeoCashe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode coordinates;

    @Column
    private String coordinatesHash;

    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode address;

    @Column
    private String addressHash;

    @Column()
    private LocalDateTime createdAt;

    public GeoCashe() {
    }

    public GeoCashe(Long id, JsonNode coordinates, String coordinatesHash,
                    JsonNode address, String addressHash, LocalDateTime createdAt) {
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

    public JsonNode getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(JsonNode coordinates) {
        this.coordinates = coordinates;
    }

    public String getCoordinatesHash() {
        return coordinatesHash;
    }

    public void setCoordinatesHash(String coordinatesHash) {
        this.coordinatesHash = coordinatesHash;
    }

    public JsonNode getAddress() {
        return address;
    }

    public void setAddress(JsonNode address) {
        this.address = address;
    }

    public String getaddressHash() {
        return addressHash;
    }

    public void setaddressHash(String addressHash) {
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
        return "GeoCashe{" +
                "id=" + id +
                ", coordinates=" + coordinates +
                ", coordinatesHash='" + coordinatesHash + '\'' +
                ", address=" + address +
                ", addressHash='" + addressHash + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
