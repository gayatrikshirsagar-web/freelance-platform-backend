package com.freelance_platform.service;

import com.freelance_platform.dto.StudentProfileResponse;
import com.freelance_platform.dto.StudentProfileUpdateRequest;
import com.freelance_platform.entity.Student;
import com.freelance_platform.entity.User;
import com.freelance_platform.repository.StudentRepository;
import com.freelance_platform.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class StudentProfileService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;


    public StudentProfileService(
            StudentRepository studentRepository,
            UserRepository userRepository) {

        this.studentRepository =
                studentRepository;

        this.userRepository =
                userRepository;
    }


    // ==========================================
    // GET STUDENT PROFILE
    // ==========================================

    public StudentProfileResponse getProfile(
            Integer userId) {

        Student student =
                studentRepository
                        .findByUserUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found."
                                )
                        );


        User user = student.getUser();


        StudentProfileResponse response =
                new StudentProfileResponse();


        // ==========================================
        // USER INFORMATION
        // ==========================================

        response.setUserId(
                user.getUserId()
        );

        response.setStudentId(
                student.getStudentId()
        );

        response.setName(
                user.getName()
        );

        response.setEmail(
                user.getEmail()
        );

        response.setPhone(
                user.getPhone()
        );


        // ==========================================
        // PROFILE PICTURE
        // ==========================================

        response.setProfilePicture(
                student.getProfilePicture()
        );


        // ==========================================
        // EDUCATION
        // ==========================================

        response.setCollegeName(
                student.getCollegeName()
        );

        response.setCourse(
                student.getCourse()
        );

        response.setYearOfStudy(
                student.getYearOfStudy()
        );

        response.setEducation(
                student.getEducation()
        );


        // ==========================================
        // ABOUT STUDENT
        // ==========================================

        response.setBio(
                student.getBio()
        );

        response.setLocation(
                student.getLocation()
        );


        // ==========================================
        // PROFESSIONAL INFORMATION
        // ==========================================

        response.setHourlyRate(
                student.getHourlyRate()
        );

        response.setAvailabilityStatus(
                student.getAvailabilityStatus()
        );

        response.setVerificationStatus(
                student.getVerificationStatus()
        );


        return response;
    }


    // ==========================================
    // UPDATE STUDENT PROFILE
    // ==========================================

    public StudentProfileResponse updateProfile(
            Integer userId,
            StudentProfileUpdateRequest request) {


        Student student =
                studentRepository
                        .findByUserUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found."
                                )
                        );


        User user = student.getUser();


        // ==========================================
        // UPDATE USER TABLE
        // ==========================================

        if (request.getName() != null) {

            user.setName(
                    request.getName()
            );
        }


        if (request.getPhone() != null) {

            user.setPhone(
                    request.getPhone()
            );
        }


        // ==========================================
        // UPDATE STUDENT TABLE
        // ==========================================

        student.setProfilePicture(
                request.getProfilePicture()
        );

        student.setCollegeName(
                request.getCollegeName()
        );

        student.setCourse(
                request.getCourse()
        );

        student.setYearOfStudy(
                request.getYearOfStudy()
        );

        student.setEducation(
                request.getEducation()
        );

        student.setBio(
                request.getBio()
        );

        student.setLocation(
                request.getLocation()
        );

        student.setHourlyRate(
                request.getHourlyRate()
        );

        student.setAvailabilityStatus(
                request.getAvailabilityStatus()
        );


        // ==========================================
        // SAVE
        // ==========================================

        userRepository.save(user);

        studentRepository.save(student);


        // ==========================================
        // RETURN UPDATED PROFILE
        // ==========================================

        return getProfile(userId);
    }
}