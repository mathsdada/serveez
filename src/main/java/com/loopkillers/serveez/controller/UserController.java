package com.loopkillers.serveez.controller;

import com.loopkillers.serveez.model.ApiResponse;
import com.loopkillers.serveez.model.House;
import com.loopkillers.serveez.model.User;
import com.loopkillers.serveez.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService mUserService;

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = mUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable(name = "userId") Long userId) {
        User user = mUserService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        mUserService.createUser(user);
        return new ResponseEntity<>(new ApiResponse("User created successfully"), HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable(name = "userId") Long userId,
                                          @RequestBody User user) {
        mUserService.updateUser(userId, user);
        return new ResponseEntity<>(new ApiResponse("User updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable(name = "userId") Long userId) {
        mUserService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/houses")
    public ResponseEntity<Object> getUserHouses(@PathVariable(name = "userId") Long userId) {
        List<House> houseList = mUserService.getUserHouses(userId);
        return new ResponseEntity<>(houseList, HttpStatus.OK);
    }

    @PutMapping("/users/{userId}/houses/{houseId}")
    public ResponseEntity<Object> addUserHouse(@PathVariable(name = "userId") Long userId,
                                                @PathVariable(name = "houseId") Long houseId) {
        mUserService.addRemoveUserHouse(userId, houseId, true);
        return new ResponseEntity<>(new ApiResponse("User House Added Successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/houses/{houseId}")
    public ResponseEntity<Object> deleteUserHouse(@PathVariable(name = "userId") Long userId,
                                               @PathVariable(name = "houseId") Long houseId) {
        mUserService.addRemoveUserHouse(userId, houseId, false);
        return new ResponseEntity<>(new ApiResponse("User House Deleted Successfully"), HttpStatus.OK);
    }
}
