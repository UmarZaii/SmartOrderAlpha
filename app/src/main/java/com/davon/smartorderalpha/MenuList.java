package com.davon.smartorderalpha;

public class MenuList {

    private String menuName;
    private String menuPrice;

    public MenuList() {

    }

    public MenuList(String menuName, String menuPrice) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

}
