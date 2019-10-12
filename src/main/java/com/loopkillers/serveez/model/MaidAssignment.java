package com.loopkillers.serveez.model;

import java.util.Date;

public class MaidAssignment {
    private long mSubscriptionId;
    private long mMaidId;
    private Date mDate;

    public MaidAssignment() {
    }

    public MaidAssignment(long subscriptionId, long maidId, Date date) {
        mSubscriptionId = subscriptionId;
        mMaidId = maidId;
        mDate = date;
    }

    public long getSubscriptionId() {
        return mSubscriptionId;
    }

    public void setSubscriptionId(long subscriptionId) {
        mSubscriptionId = subscriptionId;
    }

    public long getMaidId() {
        return mMaidId;
    }

    public void setMaidId(long maidId) {
        mMaidId = maidId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
