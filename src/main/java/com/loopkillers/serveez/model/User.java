package com.loopkillers.serveez.model;

import java.util.Date;

public class User {
    private long mId;
    private String mName;
    private String mImageUrl;
    private String mPhoneNum;
    private Date mJoinDate;
    private String mUserType; /* CUSTOMER, MAID, MASTER */

    public User() {
    }

    public User(long id, String name, String imageUrl, String phoneNum, Date joinDate, String userType) {
        mId = id;
        mName = name;
        mImageUrl = imageUrl;
        mPhoneNum = phoneNum;
        mJoinDate = joinDate;
        mUserType = userType;
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

    public String getPhoneNum() {
        return mPhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        mPhoneNum = phoneNum;
    }

    public Date getJoinDate() {
        return mJoinDate;
    }

    public void setJoinDate(Date joinDate) {
        mJoinDate = joinDate;
    }

    public String getUserType() {
        return mUserType;
    }

    public void setUserType(String userType) {
        mUserType = userType;
    }
}
