package com.loopkillers.serveez.model;

import java.util.Date;

public class MaidSubscription {
    private long mId;
    private long mHouseId;
    private long mSubServiceId;
    private long mTimeSlotId;
    private Date mSubscriptionDate;

    public MaidSubscription() {
    }

    public MaidSubscription(long id, long houseId, long subServiceId, long timeSlotId, Date subscriptionDate) {
        mId = id;
        mHouseId = houseId;
        mSubServiceId = subServiceId;
        mTimeSlotId = timeSlotId;
        mSubscriptionDate = subscriptionDate;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getHouseId() {
        return mHouseId;
    }

    public void setHouseId(long houseId) {
        mHouseId = houseId;
    }

    public long getSubServiceId() {
        return mSubServiceId;
    }

    public void setSubServiceId(long subServiceId) {
        mSubServiceId = subServiceId;
    }

    public long getTimeSlotId() {
        return mTimeSlotId;
    }

    public void setTimeSlotId(long timeSlotId) {
        mTimeSlotId = timeSlotId;
    }

    public Date getSubscriptionDate() {
        return mSubscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        mSubscriptionDate = subscriptionDate;
    }
}
