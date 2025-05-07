package com.telecom.dto;
import jakarta.persistence.Column;

import java.time.LocalDate;

import java.time.LocalDate;

public class UserPlanResponseDTO {

    private Long userPlanId;
    private String userName;
    private String userEmail;
    private String planValidity;
    private Double planPrice;
    private String status;
    private LocalDate startDate;
    private String dataAllowance;
    private String talkTime;
    private String smsAllowance;
    private String phoneNumber;
    private LocalDate expiryDate;


    public UserPlanResponseDTO(Long userPlanId, String userName, String userEmail, String planValidity, Double planPrice, String status, LocalDate startDate, String dataAllowance, String talkTime, String smsAllowance, String phoneNumber, LocalDate expiryDate) {
        this.userPlanId = userPlanId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.planValidity = planValidity;
        this.planPrice = planPrice;
        this.status = status;
        this.startDate = startDate;
        this.dataAllowance = dataAllowance;
        this.talkTime = talkTime;
        this.smsAllowance = smsAllowance;
        this.phoneNumber = phoneNumber;
        this.expiryDate = expiryDate;
    }

    public Long getUserPlanId() {
        return userPlanId;
    }

    public void setUserPlanId(Long userPlanId) {
        this.userPlanId = userPlanId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPlanValidity() {
        return planValidity;
    }

    public void setPlanValidity(String planValidity) {
        this.planValidity = planValidity;
    }

    public Double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Double planPrice) {
        this.planPrice = planPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getDataAllowance() {
        return dataAllowance;
    }

    public void setDataAllowance(String dataAllowance) {
        this.dataAllowance = dataAllowance;
    }

    public String getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(String talkTime) {
        this.talkTime = talkTime;
    }

    public String getSmsAllowance() {
        return smsAllowance;
    }

    public void setSmsAllowance(String smsAllowance) {
        this.smsAllowance = smsAllowance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}

