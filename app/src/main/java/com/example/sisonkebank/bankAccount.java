package com.example.sisonkebank;

import java.io.Serializable;

public class bankAccount implements Serializable {

    private String username;
    private String userSurname;
    private String emailAddress;
    private String password;
    private String phoneNumber;
    private String gender;
    private double amountSavings;



    private double amountCurrent;

    public bankAccount() {
    }

    public bankAccount(String username, String userSurname, String emailAddress, String password, String phoneNumber, String gender, double amountSavings, String amountCurrent) {
        this.username = username;
        this.userSurname = userSurname;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.amountSavings = amountSavings;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAmountSavings() {
        return amountSavings;
    }

    public void setAmountSavings(double amountSavings) {
        this.amountSavings = amountSavings;
    }

    public void setAmountCurrent(double amountCurrent) {
        this.amountCurrent = amountCurrent;
    }

    public double getAmountCurrent() {
        return amountCurrent;
    }
}
