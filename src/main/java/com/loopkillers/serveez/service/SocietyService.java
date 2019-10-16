package com.loopkillers.serveez.service;

import com.loopkillers.serveez.exception.DataValidationException;
import com.loopkillers.serveez.exception.ResourceNotFoundException;
import com.loopkillers.serveez.model.Service;
import com.loopkillers.serveez.model.Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class SocietyService {
    @Autowired
    private SocietyRepository mSocietyRepository;

    @Autowired
    private ServiceRepository mServiceRepository;

    public void addSociety(Society society) throws DataValidationException {
        try {
            mSocietyRepository.save(society);
        } catch (DataIntegrityViolationException ex) {
            String exceptionMsg = ex.getMostSpecificCause().getMessage();
            if (exceptionMsg.contains("uk_society_name")) {
                exceptionMsg = String.format("Society with name = %s already exist", society.getName());
            }
            throw new DataValidationException(exceptionMsg);
        }
    }

    public void deleteSociety(long societyId) throws ResourceNotFoundException {
        Society society = mSocietyRepository.findById(societyId).orElse(null);
        if (society == null) {
            throw new ResourceNotFoundException(String.format("Society with ID = %s does not exist", societyId));
        }
        mSocietyRepository.delete(society);
    }

    public void updateSociety(long societyId, Society society) {
        Society dbSociety = mSocietyRepository.findById(societyId).orElse(null);
        if (dbSociety == null) {
            throw new ResourceNotFoundException(String.format("Society with ID = %s does not exist", society.getId()));
        }
        society.setId(dbSociety.getId());
        addSociety(society);
    }

    public List<Society> findAll() {
        return mSocietyRepository.findAll();
    }

    public void addRemoveService(Long societyId, Long serviceId, boolean add) {
        Society society = mSocietyRepository.findById(societyId).orElse(null);
        Service service = mServiceRepository.findById(serviceId).orElse(null);
        if (society == null) {
            throw new ResourceNotFoundException(String.format("Society with ID = %s does not exist", societyId));
        }
        if (service == null) {
            throw new ResourceNotFoundException(String.format("Service with ID = %s does not exist", serviceId));
        }
        if (add) {
            society.addService(service);
        } else {
            society.removeService(service);
        }
        mSocietyRepository.save(society);
    }

    public List<Service> getSocietyServices(Long societyId) {
        Society society = mSocietyRepository.findById(societyId).orElse(null);
        if (society == null) {
            throw new ResourceNotFoundException(String.format("Society with ID = %s does not exist", societyId));
        }
        return new ArrayList<>(society.getServices());
    }
}
