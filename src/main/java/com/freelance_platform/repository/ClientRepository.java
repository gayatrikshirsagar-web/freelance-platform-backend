package com.freelance_platform.repository;

import com.freelance_platform.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository
        extends JpaRepository<Client, Integer> {

    Optional<Client> findByUserUserId(Integer userId);

    List<Client> findAllByOrderByClientIdDesc();

    List<Client> findByVerificationStatus(String verificationStatus);
}