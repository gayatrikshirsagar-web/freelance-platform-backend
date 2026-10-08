package com.freelance_platform.dto;
import java.math.BigDecimal;
public class StudentProfileUpdateRequest {

    private String name;
    private String phone;

    private String profilePicture;

    private String collegeName;
    private String course;
    private Integer yearOfStudy;
    private String education;
    private String bio;
    private String location;

    private BigDecimal hourlyRate;

    private String availabilityStatus;


    // ==========================================
    // NAME
    // ==========================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // ==========================================
    // PHONE
    // ==========================================

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // ==========================================
    // PROFILE PICTURE
    // ==========================================

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(
            String profilePicture) {

        this.profilePicture =
                profilePicture;
    }


    // ==========================================
    // COLLEGE
    // ==========================================

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(
            String collegeName) {

        this.collegeName =
                collegeName;
    }


    // ==========================================
    // COURSE
    // ==========================================

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }


    // ==========================================
    // YEAR
    // ==========================================

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(
            Integer yearOfStudy) {

        this.yearOfStudy =
                yearOfStudy;
    }


    // ==========================================
    // EDUCATION
    // ==========================================

    public String getEducation() {
        return education;
    }

    public void setEducation(
            String education) {

        this.education =
                education;
    }


    // ==========================================
    // BIO
    // ==========================================

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    // ==========================================
    // LOCATION
    // ==========================================

    public String getLocation() {
        return location;
    }

    public void setLocation(
            String location) {

        this.location = location;
    }


    // ==========================================
    // HOURLY RATE
    // ==========================================

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(
    		BigDecimal hourlyRate) {

        this.hourlyRate =
                hourlyRate;
    }


    // ==========================================
    // AVAILABILITY
    // ==========================================

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(
            String availabilityStatus) {

        this.availabilityStatus =
                availabilityStatus;
    }
}