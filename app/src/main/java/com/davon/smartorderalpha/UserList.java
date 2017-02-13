package com.davon.smartorderalpha;

public class UserList {

    private String userEmail;
    private String userIC;
    private String userID;
    private String userName;
    private String userPass;
    private String userType;

    public UserList() {
    }

    public UserList(String userEmail, String userIC, String userID, String userName, String userPass, String userType) {
        this.userEmail = userEmail;
        this.userIC = userIC;
        this.userIC = userID;
        this.userName = userName;
        this.userName = userPass;
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserIC() {
        return userIC;
    }

    public void setUserIC(String userIC) {
        this.userIC = userIC;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}



