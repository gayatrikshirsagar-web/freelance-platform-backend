package com.freelance_platform.dto;

import lombok.Data;

@Data
public class ApplicationStatusRequest {

    private Integer userId;

    private String status;
}