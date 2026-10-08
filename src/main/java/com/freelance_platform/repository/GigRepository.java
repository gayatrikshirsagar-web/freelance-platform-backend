package com.freelance_platform.repository;

import com.freelance_platform.entity.Gig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GigRepository extends JpaRepository<Gig, Integer> {

    List<Gig> findByClientId(Integer clientId);

    List<Gig> findByClientIdAndStatus(
            Integer clientId,
            String status
    );

    List<Gig> findByAssignedStudentIdAndStatus(
            Integer assignedStudentId,
            String status
    );

    List<Gig> findByStatus(String status);
    List<Gig> findAllByOrderByGigIdDesc();
	List<Gig> findByAssignedStudentId(Integer studentId);
}