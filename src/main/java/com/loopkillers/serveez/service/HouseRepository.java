package com.loopkillers.serveez.service;

import com.loopkillers.serveez.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long> {
    List<House> findBySocietyId(Long societyId);
    Optional<House> findByIdAndSocietyId(Long id, Long societyId);
}
