package com.freelance_platform.repository;

import com.freelance_platform.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Integer> {

    Optional<Student> findByUserUserId(Integer userId);
    
    List<Student> findAllByOrderByStudentIdDesc();

    List<Student> findByVerificationStatus(String verificationStatus);
}