package com.freelance_platform.dto;

import lombok.Data;

@Data
public class AdminClientResponse {

    private Integer clientId;
    private Integer userId;
    private String name;
    private String email;
    private String companyName;
    private String companyDescription;
    private String companyWebsite;
    private String location;
    private String clientType;
    private String verificationStatus;
}