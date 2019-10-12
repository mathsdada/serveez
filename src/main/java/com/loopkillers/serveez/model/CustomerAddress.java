package com.loopkillers.serveez.model;

public class CustomerAddress {
    private long mUserId;
    private long mHouseId;

    public CustomerAddress() {
    }

    public CustomerAddress(long userId, long houseId) {
        mUserId = userId;
        mHouseId = houseId;
    }

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public long getHouseId() {
        return mHouseId;
    }

    public void setHouseId(long houseId) {
        mHouseId = houseId;
    }
}
