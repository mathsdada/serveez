package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Service", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}, name = "uk_service_name")})
public class Service {
    private long mId;
    private String mName;
    private String mImageUrl;
    private Set<Society> mSocieties = new HashSet<>();

    public Service() {
    }

    public Service(long id, String name, String imageUrl) {
        mId = id;
        mName = name;
        mImageUrl = imageUrl;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    public long getId() {
        return mId;
    }

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    public String getName() {
        return mName;
    }

    @Column(name = "image_url", nullable = false)
    @JsonProperty("image_url")
    public String getImageUrl() {
        return mImageUrl;
    }

    public void setId(long id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "services")
    @JsonIgnore
    public Set<Society> getSocieties() {
        return mSocieties;
    }

    public void setSocieties(Set<Society> societies) {
        mSocieties = societies;
    }

    public void addSociety(Society society) {
        mSocieties.add(society);
    }

    public void removeSociety(Society society) {
        mSocieties.remove(society);
    }
}
