package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Society", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"society_name"}, name = "uk_society_name")})
public class Society {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("id")
    private long mId;

    @Column(name = "society_name", nullable = false)
    @JsonProperty("society_name")
    private String mName;

    @Column(name = "address", nullable = false)
    @JsonProperty("address")
    private String mAddress;

    @Column(name = "latitude", nullable = false)
    @JsonProperty("latitude")
    private BigDecimal mLatitude;

    @Column(name = "longitude", nullable = false)
    @JsonProperty("longitude")
    private BigDecimal mLongitude;

    public Society() {
    }

    public Society(long id, String name, String address, BigDecimal latitude, BigDecimal longitude) {
        mId = id;
        mName = name;
        mAddress = address;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    @JsonIgnore
    public long getId() {
        return mId;
    }

    @JsonIgnore
    public String getName() {
        return mName;
    }

    @JsonIgnore
    public String getAddress() {
        return mAddress;
    }

    @JsonIgnore
    public BigDecimal getLatitude() {
        return mLatitude;
    }

    @JsonIgnore
    public BigDecimal getLongitude() {
        return mLongitude;
    }

    public void setId(long id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public void setLatitude(BigDecimal latitude) {
        mLatitude = latitude;
    }

    public void setLongitude(BigDecimal longitude) {
        mLongitude = longitude;
    }
}
