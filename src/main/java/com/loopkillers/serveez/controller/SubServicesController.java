package com.loopkillers.serveez.controller;

import com.loopkillers.serveez.model.ApiResponse;
import com.loopkillers.serveez.model.SubService;
import com.loopkillers.serveez.service.SubServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubServicesController {
    @Autowired
    private SubServicesService mSubServicesService;

    @GetMapping("/services/{serviceId}/subservices")
    private ResponseEntity<Object> getSubServicesByServiceId(@PathVariable(name = "serviceId") Long serviceId) {
        List<SubService> subServices = mSubServicesService.getSubServicesByServiceId(serviceId);
        return new ResponseEntity<>(subServices, HttpStatus.OK);
    }

    @PostMapping("/services/{serviceId}/subservices")
    private ResponseEntity<Object> createSubService(@PathVariable(name = "serviceId") Long serviceId,
                                                    @RequestBody SubService subService) {
        subService.setId(0);
        mSubServicesService.createSubService(serviceId, subService);
        return new ResponseEntity<>(
                new ApiResponse(String.format("New SubService with ServiceId = %s added successfully", serviceId)),
                HttpStatus.OK);
    }

    @PutMapping("/services/{serviceId}/subservices/{subserviceId}")
    private ResponseEntity<Object> updateSubService(@PathVariable(name = "serviceId") Long serviceId,
                                                    @PathVariable(name = "subserviceId") Long subServiceId,
                                                    @RequestBody SubService subService) {
        mSubServicesService.updateSubService(subServiceId, serviceId, subService);
        return new ResponseEntity<>(
                new ApiResponse(String.format("SubService with Id = %s and ServiceId = %s updated successfully",
                        subServiceId, serviceId)),
                HttpStatus.OK);
    }

    @DeleteMapping("/services/{serviceId}/subservices/{subserviceId}")
    private ResponseEntity<Object> deleteSubService(@PathVariable(name = "serviceId") Long serviceId,
                                                    @PathVariable(name = "subserviceId") Long subServiceId) {
        mSubServicesService.deleteSubService(subServiceId, serviceId);
        return new ResponseEntity<>(
                new ApiResponse(String.format("SubService with Id = %s and ServiceId = %s deleted successfully",
                        subServiceId, serviceId)),
                HttpStatus.OK);
    }
}
