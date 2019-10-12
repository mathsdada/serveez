package com.loopkillers.serveez.model;

import java.util.Date;

public class SocietyMaster {
    private long mMasterId;
    private long mSocietyId;
    private Date mStartDate;
    private Date mEndDate;

    public SocietyMaster() {
    }

    public SocietyMaster(long masterId, long societyId, Date startDate, Date endDate) {
        mMasterId = masterId;
        mSocietyId = societyId;
        mStartDate = startDate;
        mEndDate = endDate;
    }

    public long getMasterId() {
        return mMasterId;
    }

    public void setMasterId(long masterId) {
        mMasterId = masterId;
    }

    public long getSocietyId() {
        return mSocietyId;
    }

    public void setSocietyId(long societyId) {
        mSocietyId = societyId;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }

    public Date getEndDate() {
        return mEndDate;
    }

    public void setEndDate(Date endDate) {
        mEndDate = endDate;
    }
}
