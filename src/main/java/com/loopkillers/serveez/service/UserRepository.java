package com.loopkillers.serveez.service;

import com.loopkillers.serveez.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
