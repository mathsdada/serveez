package com.loopkillers.serveez.service;

import com.loopkillers.serveez.exception.DataValidationException;
import com.loopkillers.serveez.exception.ResourceNotFoundException;
import com.loopkillers.serveez.model.MaidTimeSlot;
import com.loopkillers.serveez.model.TimeSlot;
import com.loopkillers.serveez.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSlotService {
    @Autowired
    private TimeSlotRepository mTimeSlotRepository;

    @Autowired
    private UserRepository mMaidRepository;

    public void save(TimeSlot timeSlot) {
        try {
            mTimeSlotRepository.save(timeSlot);
        } catch (DataIntegrityViolationException ex) {
            String exMsg = ex.getMostSpecificCause().getMessage();
            if (exMsg.contains("uk_start_time")) {
                throw new DataValidationException("TimeSlot with start_time already exist");
            } else if (exMsg.contains("uk_end_time")) {
                throw new DataValidationException("TimeSlot with end_time already exist");
            } else {
                throw new DataValidationException(exMsg);
            }
        }
    }

    public List<TimeSlot> getTimeSlots() {
        return mTimeSlotRepository.findAll();
    }

    public void createTimeSlot(TimeSlot timeSlot) {
        timeSlot.setId(0);
        save(timeSlot);
    }

    public void deleteTimeSlot(Long timeslotId) {
        TimeSlot timeSlot = mTimeSlotRepository.findById(timeslotId).orElse(null);
        if (timeSlot == null) throw new ResourceNotFoundException("TimeSlot Id does not exist");
        mTimeSlotRepository.delete(timeSlot);
    }

    public TimeSlot getTimeSlotById(Long timeSlotId) {
        TimeSlot timeSlot = mTimeSlotRepository.findById(timeSlotId).orElse(null);
        if (timeSlot == null) {
            throw new ResourceNotFoundException("TimeSlot not found");
        }
        return timeSlot;
    }
}
