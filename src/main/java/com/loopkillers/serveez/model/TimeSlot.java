package com.loopkillers.serveez.model;

public class TimeSlot {
    private long mId;
    private String mStartTime;
    private String mEndTime;

    public TimeSlot() {
    }

    public TimeSlot(long id, String startTime, String endTime) {
        mId = id;
        mStartTime = startTime;
        mEndTime = endTime;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        mEndTime = endTime;
    }
}
