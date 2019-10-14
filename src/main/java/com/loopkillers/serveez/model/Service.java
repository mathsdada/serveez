package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "Service", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}, name = "uk_service_name")})
public class Service {
    private long mId;
    private String mName;
    private String mImageUrl;

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
}
