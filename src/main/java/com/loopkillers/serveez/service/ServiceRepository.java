package com.loopkillers.serveez.service;

import com.loopkillers.serveez.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
