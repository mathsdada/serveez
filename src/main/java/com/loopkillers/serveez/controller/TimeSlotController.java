package com.loopkillers.serveez.controller;

import com.loopkillers.serveez.model.ApiResponse;
import com.loopkillers.serveez.model.TimeSlot;
import com.loopkillers.serveez.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimeSlotController {
    @Autowired
    private TimeSlotService mTimeSlotService;

    @PostMapping("/timeslots")
    public ResponseEntity<Object> create(@RequestBody TimeSlot timeSlot) {
        mTimeSlotService.createTimeSlot(timeSlot);
        return new ResponseEntity<>(new ApiResponse("Timeslot added"), HttpStatus.OK);
    }

    @DeleteMapping("/timeslots/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Long timeSlotId) {
        mTimeSlotService.deleteTimeSlot(timeSlotId);
        return new ResponseEntity<>(new ApiResponse("Timeslot deleted"), HttpStatus.OK);
    }

    @GetMapping("/timeslots")
    public ResponseEntity<Object> get() {
        return new ResponseEntity<>(mTimeSlotService.getTimeSlots(), HttpStatus.OK);
    }
}
