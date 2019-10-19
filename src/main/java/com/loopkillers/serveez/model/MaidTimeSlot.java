package com.loopkillers.serveez.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "maids_timeslots")
public class MaidTimeSlot implements Serializable {
    private MaidTimeSlotPK mId;
    private User mMaid;
    private TimeSlot mTimeSlot;

    public MaidTimeSlot(User maid, TimeSlot timeSlot, boolean isAvailable, boolean isSubscribed) {
        mMaid = maid;
        mTimeSlot = timeSlot;
        mIsAvailable = isAvailable;
        mIsSubscribed = isSubscribed;
    }

    private boolean mIsAvailable;
    private boolean mIsSubscribed;

    @EmbeddedId
    public MaidTimeSlotPK getId() {
        return mId;
    }

    @ManyToOne
    @MapsId("maid_id")
    @JoinColumn(name = "maid_id")
    public User getMaid() {
        return mMaid;
    }

    @ManyToOne
    @MapsId("time_slot_id")
    @JoinColumn(name = "time_slot_id")
    public TimeSlot getTimeSlot() {
        return mTimeSlot;
    }

    @Column(name = "available", nullable = false)
    public boolean isAvailable() {
        return mIsAvailable;
    }

    @Column(name = "subscribed", nullable = false)
    public boolean isSubscribed() {
        return mIsSubscribed;
    }

    public void setId(MaidTimeSlotPK id) {
        mId = id;
    }

    public void setMaid(User maid) {
        mMaid = maid;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        mTimeSlot = timeSlot;
    }

    public MaidTimeSlot(User maid, TimeSlot timeSlot) {
        mMaid = maid;
        mTimeSlot = timeSlot;
    }

    public MaidTimeSlot() {
    }

    public void setAvailable(boolean available) {
        mIsAvailable = available;
    }

    public void setSubscribed(boolean subscribed) {
        mIsSubscribed = subscribed;
    }
}
