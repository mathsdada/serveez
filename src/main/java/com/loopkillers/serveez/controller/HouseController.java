package com.loopkillers.serveez.controller;

import com.loopkillers.serveez.model.ApiResponse;
import com.loopkillers.serveez.model.House;
import com.loopkillers.serveez.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HouseController {
    @Autowired
    private HouseService mHouseService;

    @GetMapping("/societies/{societyId}/houses")
    public ResponseEntity<Object> getAllHousesBySocietyId(@PathVariable(value = "societyId") Long societyId) {
        List<House> houseList = mHouseService.getAllBySocietyId(societyId);
        return new ResponseEntity<>(houseList, HttpStatus.OK);
    }

    @PostMapping("/societies/{societyId}/houses")
    public ResponseEntity<Object> createHouse(@PathVariable(value = "societyId") Long societyId,
                                              @RequestBody House house) {
        mHouseService.createHouse(societyId, house);
        return new ResponseEntity<>(
                new ApiResponse(String.format("created a new house with societyId = %s", societyId)),
                HttpStatus.OK);
    }

    @DeleteMapping("/societies/{societyId}/houses/{houseId}")
    public ResponseEntity<Object> deleteHouse(@PathVariable(value = "societyId") Long societyId,
                                              @PathVariable(value = "houseId") Long houseId) {
        mHouseService.deleteHouse(houseId, societyId);
        return new ResponseEntity<>(
                new ApiResponse(String.format("deleted house with id = %s and societyId = %s", houseId, societyId)),
                HttpStatus.OK);
    }

    @PutMapping("/societies/{societyId}/houses/{houseId}")
    public ResponseEntity<Object> updateHouse(@PathVariable(value = "societyId") Long societyId,
                                              @PathVariable(value = "houseId") Long houseId,
                                              @RequestBody House house) {
        mHouseService.updateHouse(houseId, societyId, house);
        return new ResponseEntity<>(
                new ApiResponse(String.format("updated house with id = %s and societyId = %s", houseId, societyId)),
                HttpStatus.OK);
    }
}
