package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Society", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}, name = "uk_society_name")})
public class Society {
    private long mId;
    private String mName;
    private String mAddress;
    private BigDecimal mLatitude;
    private BigDecimal mLongitude;
    private Set<Service> mServices = new HashSet<>();

    public Society() {
    }

    public Society(long id, String name, String address, BigDecimal latitude, BigDecimal longitude) {
        mId = id;
        mName = name;
        mAddress = address;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("id")
    public long getId() {
        return mId;
    }

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    public String getName() {
        return mName;
    }

    @Column(name = "address", nullable = false)
    @JsonProperty("address")
    public String getAddress() {
        return mAddress;
    }

    @Column(name = "latitude", nullable = false)
    @JsonProperty("latitude")
    public BigDecimal getLatitude() {
        return mLatitude;
    }

    @Column(name = "longitude", nullable = false)
    @JsonProperty("longitude")
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "SocietyServices",
            joinColumns = {@JoinColumn(name = "society_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")})
    @JsonIgnore
    public Set<Service> getServices() {
        return mServices;
    }

    public void setServices(Set<Service> services) {
        mServices = services;
    }

    public void addService(Service service) {
        mServices.add(service);
        service.addSociety(this);
    }

    public void removeService(Service service) {
        mServices.remove(service);
        service.removeSociety(this);
    }
}
