package com.loopkillers.serveez.service;

import com.loopkillers.serveez.exception.DataValidationException;
import com.loopkillers.serveez.exception.ResourceNotFoundException;
import com.loopkillers.serveez.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@org.springframework.stereotype.Service
public class ServicesService {
    @Autowired
    private ServiceRepository mServiceRepository;

    public void saveService(Service service) {
        try {
            mServiceRepository.save(service);
        } catch (DataIntegrityViolationException ex) {
            String exceptionMsg = ex.getMostSpecificCause().getMessage();
            if (exceptionMsg.contains("uk_service_name")) {
                exceptionMsg = String.format("Service with name = %s already exist", service.getName());
            }
            throw new DataValidationException(exceptionMsg);
        }
    }

    public List<Service> getAllServices() {
        return mServiceRepository.findAll();
    }


    public void deleteService(Long serviceId) {
          Service dbService = mServiceRepository.findById(serviceId).orElse(null);
          if (dbService == null) {
              throw new ResourceNotFoundException(String.format("Service with Id =  %s does not exist", serviceId));
          }
          mServiceRepository.delete(dbService);
    }

    public void updateService(Long serviceId, Service service) {
        Service dbService = mServiceRepository.findById(serviceId).orElse(null);
        if (dbService == null) {
            throw new ResourceNotFoundException(String.format("Service with Id =  %s does not exist", serviceId));
        }
        dbService.setName(service.getName());
        dbService.setImageUrl(service.getImageUrl());
        saveService(dbService);
    }
}
