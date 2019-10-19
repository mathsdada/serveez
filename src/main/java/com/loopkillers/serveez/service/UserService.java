package com.loopkillers.serveez.service;

import com.loopkillers.serveez.exception.DataValidationException;
import com.loopkillers.serveez.exception.ResourceNotFoundException;
import com.loopkillers.serveez.model.House;
import com.loopkillers.serveez.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private HouseRepository mHouseRepository;

    public void saveUser(User user) {
        try {
            mUserRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            String cause = ex.getMostSpecificCause().getMessage();
            if (cause.contains("uk_email_address")) {
                throw new DataValidationException("Email Address already exist");
            } else if (cause.contains("uk_phone_number")) {
                throw new DataValidationException("Phone number already exist");
            } else {
                throw new DataValidationException(cause);
            }
        }
    }
    public void createUser(User user) {
        user.setId(0);
        saveUser(user);
    }

    public void updateUser(Long userId, User user) {
        User dbUser = mUserRepository.findById(userId).orElse(null);
        if (dbUser == null) {
            throw new ResourceNotFoundException(String.format("User with ID = %s does not exist", userId));
        }
        dbUser.setName(user.getName());
        dbUser.setPhoneNum(user.getPhoneNum());
        dbUser.setEmail(user.getEmail());
        saveUser(dbUser);
    }

    public void deleteUser(Long userId) {
        User dbUser = mUserRepository.findById(userId).orElse(null);
        if (dbUser == null) {
            throw new ResourceNotFoundException(String.format("User with ID = %s does not exist", userId));
        }
        mUserRepository.delete(dbUser);
    }

    public List<User> getAllUsers() {
        return mUserRepository.findAll();
    }

    public User getUserById(Long userId) {
        User dbUser = mUserRepository.findById(userId).orElse(null);
        if (dbUser == null) {
            throw new ResourceNotFoundException(String.format("User with ID = %s does not exist", userId));
        }
        return dbUser;
    }

    public List<House> getUserHouses(Long userId) {
        User dbUser = mUserRepository.findById(userId).orElse(null);
        if (dbUser == null) {
            throw new ResourceNotFoundException(String.format("User with ID = %s does not exist", userId));
        }
        return new ArrayList<>(dbUser.getHouses());
    }

    public void addRemoveUserHouse(Long userId, Long houseId, boolean add) {
        User user = mUserRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException(String.format("User with ID = %s does not exist", userId));
        }
        House house = mHouseRepository.findById(houseId).orElse(null);
        if (house == null) {
            throw new ResourceNotFoundException(String.format("House with ID = %s does not exist", houseId));
        }
        if (add) {
            user.addHouse(house);
        } else {
            user.removeHouse(house);
        }
        saveUser(user);
    }
}
