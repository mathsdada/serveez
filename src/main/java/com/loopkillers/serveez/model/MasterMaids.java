package com.loopkillers.serveez.model;

public class MasterMaids {
    private long mMasterId;
    private long mMaidId;

    public MasterMaids() {
    }

    public MasterMaids(long masterId, long maidId) {
        mMasterId = masterId;
        mMaidId = maidId;
    }

    public long getMasterId() {
        return mMasterId;
    }

    public void setMasterId(long masterId) {
        mMasterId = masterId;
    }

    public long getMaidId() {
        return mMaidId;
    }

    public void setMaidId(long maidId) {
        mMaidId = maidId;
    }
}
