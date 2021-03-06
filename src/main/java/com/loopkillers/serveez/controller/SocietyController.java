package com.loopkillers.serveez.controller;

import com.loopkillers.serveez.model.ApiResponse;
import com.loopkillers.serveez.model.Service;
import com.loopkillers.serveez.model.Society;
import com.loopkillers.serveez.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocietyController {
    @Autowired
    private SocietyService mSocietyService;

    @GetMapping("/societies")
    public ResponseEntity<List<Society>> getAll() {
        List<Society> societyList = mSocietyService.findAll();
        return new ResponseEntity<>(societyList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/societies")
    public ResponseEntity<ApiResponse> addSociety(@RequestBody Society society) {
        // let database decide the ID. So ignore the ID passed by client
        society.setId(0);
        mSocietyService.addSociety(society);
        return new ResponseEntity<>(new ApiResponse("Society added successfully"), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/societies/{id}")
    public ResponseEntity<?> deleteSociety(@PathVariable(name = "id") long societyId) {
        mSocietyService.deleteSociety(societyId);
        return new ResponseEntity<>(new ApiResponse("Society deleted successfully"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/societies/{id}")
    public ResponseEntity<ApiResponse> updateSociety(@PathVariable(name = "id") long societyId,
                                                     @RequestBody Society society) {
        mSocietyService.updateSociety(societyId, society);
        return new ResponseEntity<>(new ApiResponse("Society updated successfully"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/societies/{societyId}/services")
    public ResponseEntity<Object> getSocietyServices(@PathVariable(name = "societyId") Long societyId) {
        List<Service> services = mSocietyService.getSocietyServices(societyId);
        return new ResponseEntity<>(services, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/societies/{societyId}/services/{serviceId}")
    public ResponseEntity<ApiResponse> addSocietyService(@PathVariable(name = "societyId") Long societyId,
                                                         @PathVariable(name = "serviceId") Long serviceId) {
        mSocietyService.addRemoveService(societyId, serviceId, true);
        return new ResponseEntity<>(new ApiResponse("Society Service added successfully"), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/societies/{societyId}/services/{serviceId}")
    public ResponseEntity<ApiResponse> deleteSocietyService(@PathVariable(name = "societyId") Long societyId,
                                                            @PathVariable(name = "serviceId") Long serviceId) {
        mSocietyService.addRemoveService(societyId, serviceId, false);
        return new ResponseEntity<>(new ApiResponse("Society Service removed successfully"), new HttpHeaders(), HttpStatus.OK);
    }
}
