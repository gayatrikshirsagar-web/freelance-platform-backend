package com.freelance_platform.repository;

import com.freelance_platform.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository
        extends JpaRepository<Application, Integer> {

    // Get all applications submitted by a particular student
    List<Application> findByStudentId(
            Integer studentId
    );

    // Get all applications for a particular project/gig
    List<Application> findByGigId(
            Integer gigId
    );

    // Get applications for multiple gigs,
    // newest applications first
    List<Application> findByGigIdInOrderByAppliedAtDesc(
            List<Integer> gigIds
    );

    // Check whether a student has already applied
    // to a project
    boolean existsByGigIdAndStudentId(
            Integer gigId,
            Integer studentId
    );
    
    List<Application> findAllByOrderByAppliedAtDesc();

    List<Application> findByApplicationStatus(String applicationStatus);
}