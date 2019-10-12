package com.loopkillers.serveez.controller;

import com.loopkillers.serveez.model.ApiResponse;
import com.loopkillers.serveez.model.Society;
import com.loopkillers.serveez.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/society")
public class SocietyController {
    @Autowired
    private SocietyService mSocietyService;

    @GetMapping("/")
    public ResponseEntity<List<Society>> getAll() {
        List<Society> societyList = mSocietyService.findAll();
        return new ResponseEntity<>(societyList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addSociety(@RequestBody Society society) {
        // let database decide the ID. So ignore the ID passed by client
        society.setId(0);
        mSocietyService.addSociety(society);
        return new ResponseEntity<>(new ApiResponse("Society added successfully"), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSociety(@PathVariable(name = "id") long societyId) {
        mSocietyService.deleteSociety(societyId);
        return new ResponseEntity<>(new ApiResponse("Society deleted successfully"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateSociety(@RequestBody Society society) {
        mSocietyService.updateSociety(society);
        return new ResponseEntity<>(new ApiResponse("Society updated successfully"), new HttpHeaders(), HttpStatus.OK);
    }
}
