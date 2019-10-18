package com.loopkillers.serveez.service;

import com.loopkillers.serveez.model.MaidMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaidMasterRepository extends JpaRepository<MaidMaster, Long> {
    List<MaidMaster> findByMasterId(Long maidId);
    Optional<MaidMaster> findByMaidIdAndMasterId(Long maidId, Long masterId);
}
