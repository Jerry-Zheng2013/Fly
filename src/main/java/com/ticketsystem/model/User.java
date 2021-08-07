package com.ticketsystem.model;

import java.math.BigDecimal;

public class User {
    private Integer userId;

    private String isManager;

    private String userName;

    private String userPassword;

    private BigDecimal isVip;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIsManager() {
        return isManager;
    }

    public void setIsManager(String isManager) {
        this.isManager = isManager == null ? null : isManager.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public BigDecimal getIsVip() {
        return isVip;
    }

    public void setIsVip(BigDecimal isVip) {
        this.isVip = isVip;
    }
}