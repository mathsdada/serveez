package com.loopkillers.serveez.model;

public class House {
    private long mId;
    private String mBlock;
    private String mHouseNum;
    private long mSocietyId;

    public House() {
    }

    public House(long id, String block, String houseNum, long societyId) {
        mId = id;
        mBlock = block;
        mHouseNum = houseNum;
        mSocietyId = societyId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getBlock() {
        return mBlock;
    }

    public void setBlock(String block) {
        mBlock = block;
    }

    public String getHouseNum() {
        return mHouseNum;
    }

    public void setHouseNum(String houseNum) {
        mHouseNum = houseNum;
    }

    public long getSocietyId() {
        return mSocietyId;
    }

    public void setSocietyId(long societyId) {
        mSocietyId = societyId;
    }
}
