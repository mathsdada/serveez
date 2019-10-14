package com.loopkillers.serveez.service;

import com.loopkillers.serveez.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubServicesRepository extends JpaRepository<SubService, Long> {
    List<SubService> findByServiceId(Long serviceId);
    Optional<SubService> findByIdAndServiceId(Long id, Long serviceId);
}
