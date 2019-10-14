package com.loopkillers.serveez.controller;

import com.loopkillers.serveez.model.ApiResponse;
import com.loopkillers.serveez.model.Service;
import com.loopkillers.serveez.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServicesController {
    @Autowired
    private ServicesService mServicesService;

    @GetMapping("/services")
    public ResponseEntity<Object> getAllServices() {
        List<Service> services = mServicesService.getAllServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PostMapping("/services")
    public ResponseEntity<Object> createService(@RequestBody Service service) {
        // ID will be created by server. Ignore the ID passed by client.
        service.setId(0);
        mServicesService.saveService(service);
        return new ResponseEntity<>(new ApiResponse("New Service added successfully"), HttpStatus.OK);
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<Object> updateService(@PathVariable(name = "id") Long serviceId,
                                                @RequestBody Service service) {
        mServicesService.updateService(serviceId, service);
        return new ResponseEntity<>(
                new ApiResponse(String.format("Service with ID = %s updated successfully", serviceId)),
                HttpStatus.OK);
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable(name = "id") Long serviceId) {
        mServicesService.deleteService(serviceId);
        return new ResponseEntity<>(
                new ApiResponse(String.format("Service with ID = %s deleted successfully", serviceId)),
                HttpStatus.OK);
    }
}
