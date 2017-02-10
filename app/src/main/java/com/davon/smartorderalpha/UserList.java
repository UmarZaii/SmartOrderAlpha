package com.davon.smartorderalpha;

public class UserList {

    private String userEmail;
    private String userIC;
    private String userName;
    private String userType;

    public UserList(){

    }

    public UserList(String userEmail, String userIC, String userName, String userType) {
        this.userEmail = userEmail;
        this.userIC = userIC;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}



