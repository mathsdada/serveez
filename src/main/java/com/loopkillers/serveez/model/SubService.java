package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "SubService", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "service_id"}, name = "uk_sub_service_name_service_id")
})
public class SubService {
    private long mId;
    private String mName;
    private String mImageUrl;
    private Service mService;

    public SubService() {
    }

    public SubService(long id, String name, String imageUrl) {
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Service getService() {
        return mService;
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

    public void setService(Service service) {
        mService = service;
    }
}
