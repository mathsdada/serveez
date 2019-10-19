package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "timeslots", uniqueConstraints = {
        @UniqueConstraint(name = "uk_start_time", columnNames = "start_time"),
        @UniqueConstraint(name = "uk_end_time", columnNames = "end_time")
})
public class TimeSlot {
    private long mId;
    private String mStartTime;
    private String mEndTime;
    private Set<MaidTimeSlot> mMaid = new HashSet<>();

    public TimeSlot() {
    }

    public TimeSlot(long id, String startTime, String endTime) {
        mId = id;
        mStartTime = startTime;
        mEndTime = endTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @Column(name = "start_time", nullable = false)
    @JsonProperty("start_time")
    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }

    @Column(name = "end_time", nullable = false)
    @JsonProperty("end_time")
    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        mEndTime = endTime;
    }

    @OneToMany(mappedBy = "maid")
    public Set<MaidTimeSlot> getMaid() {
        return mMaid;
    }

    public void setMaid(Set<MaidTimeSlot> maid) {
        mMaid = maid;
    }
}
