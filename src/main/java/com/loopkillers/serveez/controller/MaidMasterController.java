package com.loopkillers.serveez.controller;

import com.loopkillers.serveez.model.ApiResponse;
import com.loopkillers.serveez.model.User;
import com.loopkillers.serveez.service.MaidMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaidMasterController {
    @Autowired
    private MaidMasterService mMaidMasterService;

    @PostMapping("/masters/{masterId}/maids/{maidId}")
    public ResponseEntity<Object> addMaidMasterEntry(@PathVariable(name = "masterId") Long masterId,
                                                     @PathVariable(name = "maidId") Long maidId) {
        mMaidMasterService.addMaidMasterEntry(maidId, masterId);
        return new ResponseEntity<>(
                new ApiResponse(String.format("Maid %s assigned to Master %s successfully", maidId, masterId)),
                HttpStatus.OK);
    }

    @DeleteMapping("/masters/{masterId}/maids/{maidId}")
    public ResponseEntity<Object> deleteMaidMasterEntry(@PathVariable(name = "masterId") Long masterId,
                                                        @PathVariable(name = "maidId") Long maidId) {
        mMaidMasterService.removeMaidMasterEntry(maidId, masterId);
        return new ResponseEntity<>(
                new ApiResponse(String.format("Maid %s unassigned from Master %s successfully", maidId, masterId)),
                HttpStatus.OK);
    }

    @GetMapping("/masters/{masterId}/maids")
    public ResponseEntity<Object> getMaidsOfMaster(@PathVariable(name = "masterId") Long masterId) {
        List<User> maidList = mMaidMasterService.getMaidsByMasterId(masterId);
        return new ResponseEntity<>(maidList, HttpStatus.OK);
    }
}
