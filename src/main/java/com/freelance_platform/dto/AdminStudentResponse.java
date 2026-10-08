package com.freelance_platform.dto;

import lombok.Data;

@Data
public class AdminStudentResponse {

    private Integer studentId;
    private Integer userId;
    private String name;
    private String email;
    private String collegeName;
    private String course;
    private Integer yearOfStudy;
    private String location;
    private String availabilityStatus;
    private String verificationStatus;
}