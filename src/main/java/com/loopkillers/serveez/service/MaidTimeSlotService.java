package com.loopkillers.serveez.service;

import com.loopkillers.serveez.model.MaidTimeSlot;
import com.loopkillers.serveez.model.TimeSlot;
import com.loopkillers.serveez.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaidTimeSlotService {
    @Autowired
    private UserService mUserService;

    @Autowired
    private TimeSlotService mTimeSlotService;

    public void createMaidTimeSlots(Long maidId) {
        User maid = mUserService.getUserById(maidId);
        List<TimeSlot> timeSlots = mTimeSlotService.getTimeSlots();
        for (TimeSlot timeSlot: timeSlots) {
            MaidTimeSlot maidTimeSlot = new MaidTimeSlot(maid, timeSlot, false, false);
            maid.getTimeSlot().add(maidTimeSlot);
        }
        mUserService.saveUser(maid);
    }

    public List<User> getMaidsByTimeSlot(Long timeSlotId) {
        List<User> maidList = new ArrayList<>();
        TimeSlot timeSlot = mTimeSlotService.getTimeSlotById(timeSlotId);
        for (MaidTimeSlot maidTimeSlot: timeSlot.getMaid()) {
            if (maidTimeSlot.isAvailable() &&
                    !maidTimeSlot.isSubscribed()) {
                maidList.add(maidTimeSlot.getMaid());
            }
        }
        return maidList;
    }

    public void setMaidTimeSlotAvailability(Long maidId, Long timeSlotId, boolean isAvailable) {
        User maid = mUserService.getUserById(maidId);
        TimeSlot timeSlot = mTimeSlotService.getTimeSlotById(timeSlotId);
        for (MaidTimeSlot maidTimeSlot : maid.getTimeSlot()) {
            if (maidTimeSlot.getTimeSlot() == timeSlot) {
                maidTimeSlot.setAvailable(isAvailable);
            }
        }
        mUserService.saveUser(maid);
    }

    public void setMaidTimeSlotSubscriptionStatus(Long maidId, Long timeSlotId, boolean isSubscribed) {
        User maid = mUserService.getUserById(maidId);
        TimeSlot timeSlot = mTimeSlotService.getTimeSlotById(timeSlotId);
        for (MaidTimeSlot maidTimeSlot : maid.getTimeSlot()) {
            if (maidTimeSlot.getTimeSlot() == timeSlot) {
                maidTimeSlot.setSubscribed(isSubscribed);
            }
        }
        mUserService.saveUser(maid);
    }
}
