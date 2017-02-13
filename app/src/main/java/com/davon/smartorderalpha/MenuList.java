package com.davon.smartorderalpha;

public class MenuList {

    private String menuName;
    private String menuPrice;
    private String menuType;

    public MenuList() {

    }

    public MenuList(String menuName, String menuPrice, String menuType) {
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

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

}
