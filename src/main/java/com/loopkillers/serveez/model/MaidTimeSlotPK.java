package com.loopkillers.serveez.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MaidTimeSlotPK implements Serializable {
    private Long mMaidId;
    private Long mTimeSlotId;

    public MaidTimeSlotPK(Long maidId, Long timeSlotId) {
        mMaidId = maidId;
        mTimeSlotId = timeSlotId;
    }

    public MaidTimeSlotPK() {
    }

    @Column(name = "maid_id")
    public Long getMaidId() {
        return mMaidId;
    }

    @Column(name = "time_slot_id")
    public Long getTimeSlotId() {
        return mTimeSlotId;
    }

    public void setMaidId(Long maidId) {
        mMaidId = maidId;
    }

    public void setTimeSlotId(Long timeSlotId) {
        mTimeSlotId = timeSlotId;
    }
}
