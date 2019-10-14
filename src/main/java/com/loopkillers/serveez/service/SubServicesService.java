package com.loopkillers.serveez.service;

import com.loopkillers.serveez.exception.DataValidationException;
import com.loopkillers.serveez.exception.ResourceNotFoundException;
import com.loopkillers.serveez.model.Service;
import com.loopkillers.serveez.model.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@org.springframework.stereotype.Service
public class SubServicesService {
    @Autowired
    private SubServicesRepository mSubServicesRepository;

    @Autowired
    private ServiceRepository mServiceRepository;

    public List<SubService> getSubServicesByServiceId(Long serviceId) {
        return mSubServicesRepository.findByServiceId(serviceId);
    }

    private void saveSubService(SubService subService) {
        try {
            mSubServicesRepository.save(subService);
        } catch (DataIntegrityViolationException ex) {
            String exceptionMsg = ex.getMostSpecificCause().getMessage();
            if (exceptionMsg.contains("uk_sub_service_name_service_id")) {
                exceptionMsg = String.format("SubService with name = %s and ServiceId = %s already exist",
                        subService.getName(), subService.getService().getId());
            }
            throw new DataValidationException(exceptionMsg);
        }
    }

    public void createSubService(Long serviceId, SubService subService) {
        Service service = mServiceRepository.findById(serviceId).orElse(null);
        if (service == null) {
            throw new ResourceNotFoundException(String.format("Service with ID = %s does not exist", serviceId));
        }
        subService.setService(service);
        saveSubService(subService);
    }

    public void deleteSubService(Long subServiceId, Long serviceId) {
        SubService subService = mSubServicesRepository.findByIdAndServiceId(subServiceId, serviceId).orElse(null);
        if (subService == null) {
            throw new ResourceNotFoundException(
                    String.format("SubService with ID = %s and serviceId = %s does not exist", subServiceId, serviceId));
        }
        mSubServicesRepository.delete(subService);
    }

    public void updateSubService(Long subServiceId, Long serviceId, SubService subService) {
        SubService dbSubService =
                mSubServicesRepository.findByIdAndServiceId(subServiceId, serviceId).orElse(null);
        if (dbSubService == null) {
            throw new ResourceNotFoundException(
                    String.format("SubService with ID = %s and serviceId = %s does not exist", subServiceId, serviceId));
        }
        dbSubService.setName(subService.getName());
        dbSubService.setImageUrl(subService.getImageUrl());
        saveSubService(dbSubService);
    }
}
