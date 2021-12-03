package com.example.quizhalloween;

public class UserInformation {
    public String userName;
    public String userSurName;
    public int userResult;

    public UserInformation() {

    }

    public UserInformation(String name, String surName, int result) {
        this.userName = name;
        this.userSurName = surName;
        this.userResult = result;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public int getUserResult() {
        return userResult;
    }
}
