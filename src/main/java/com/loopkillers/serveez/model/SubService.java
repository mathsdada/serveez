package com.loopkillers.serveez.model;

public class SubService {
    private long mId;
    private String mName;
    private String mImageUrl;
    private long mServiceId;

    public SubService() {
    }

    public SubService(long id, String name, String imageUrl, long serviceId) {
        mId = id;
        mName = name;
        mImageUrl = imageUrl;
        mServiceId = serviceId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public long getServiceId() {
        return mServiceId;
    }

    public void setServiceId(long serviceId) {
        mServiceId = serviceId;
    }
}
