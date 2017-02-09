package com.davon.smartorderalpha;

public class UserList {

    private String userEmail;
    private String userIC;
    private String userName;

    public UserList(){

    }
    public UserList(String userEmail, String userIC, String userName) {
        this.userEmail = userEmail;
        this.userIC = userIC;
        this.userName = userName;
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
}



