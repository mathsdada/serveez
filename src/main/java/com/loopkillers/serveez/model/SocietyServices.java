package com.loopkillers.serveez.model;

import java.util.Date;

public class SocietyServices {
    private long mSocietyId;
    private long mServiceId;
    private Date mStartDate;

    public SocietyServices() {
    }

    public SocietyServices(long societyId, long serviceId, Date startDate) {
        mSocietyId = societyId;
        mServiceId = serviceId;
        mStartDate = startDate;
    }

    public long getSocietyId() {
        return mSocietyId;
    }

    public void setSocietyId(long societyId) {
        mSocietyId = societyId;
    }

    public long getServiceId() {
        return mServiceId;
    }

    public void setServiceId(long serviceId) {
        mServiceId = serviceId;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }
}
