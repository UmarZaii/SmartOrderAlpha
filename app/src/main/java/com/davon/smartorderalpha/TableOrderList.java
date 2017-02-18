package com.davon.smartorderalpha;

public class TableOrderList {

    private String menuAmount;
    private String menuName;
    private String menuPrice;
    private String menuStatus;
    private String menuType;

    public TableOrderList() {

    }

    public TableOrderList(String menuAmount, String menuName, String menuPrice, String menuStatus, String menuType) {
        this.menuAmount = menuAmount;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuStatus = menuStatus;
        this.menuType = menuType;
    }

    public String getMenuAmount() {
        return menuAmount;
    }

    public void setMenuAmount(String menuAmount) {
        this.menuAmount = menuAmount;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

}
