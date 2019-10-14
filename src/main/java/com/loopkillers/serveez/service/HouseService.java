package com.loopkillers.serveez.service;

import com.loopkillers.serveez.exception.DataValidationException;
import com.loopkillers.serveez.exception.ResourceNotFoundException;
import com.loopkillers.serveez.model.House;
import com.loopkillers.serveez.model.Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {
    @Autowired
    private HouseRepository mHouseRepository;

    @Autowired
    private SocietyRepository mSocietyRepository;

    public List<House> getAllBySocietyId(Long societyId) {
        return mHouseRepository.findBySocietyId(societyId);
    }

    private void saveHouse(House house) {
        try {
            mHouseRepository.save(house);
        } catch (DataIntegrityViolationException ex) {
            String exceptionMsg = ex.getMostSpecificCause().getMessage();
            if (exceptionMsg.contains("uk_block_house_num_society_id")) {
                exceptionMsg = String.format(
                        "House with details = (%s, %s, %s) already exist",
                        house.getBlock(), house.getHouseNum(), house.getSociety().getId());
            }
            throw new DataValidationException(exceptionMsg);
        }
    }

    public void createHouse(Long societyId, House house) {
        Society society = mSocietyRepository.findById(societyId).orElse(null);
        if (society == null) {
            throw new ResourceNotFoundException(String.format("SocietyId %s not found", societyId));
        }
        house.setSociety(society);
        saveHouse(house);
    }

    public void deleteHouse(Long houseId, Long societyId) {
        House house = mHouseRepository.findByIdAndSocietyId(houseId, societyId).orElse(null);
        if (house == null) {
            throw new ResourceNotFoundException(
                    String.format("House not found with Id = %s and SocietyId = %s", houseId, societyId));
        }
        mHouseRepository.delete(house);
    }

    public void updateHouse(Long houseId, Long societyId, House house) {
        House dbHouse = mHouseRepository.findByIdAndSocietyId(houseId, societyId).orElse(null);
        if (dbHouse == null) {
            throw new ResourceNotFoundException(
                    String.format("House not found with Id = %s and SocietyId = %s", houseId, societyId));
        }
        dbHouse.setBlock(house.getBlock());
        dbHouse.setHouseNum(house.getHouseNum());
        saveHouse(dbHouse);
    }
}
