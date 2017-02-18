package com.davon.smartorderalpha;

public class TableList {

    private String orderID;
    private String tableNo;
    private String tableStatus;

    public TableList() {

    }

    public TableList(String orderID, String tableNo, String tableStatus) {
        this.orderID = orderID;
        this.tableNo = tableNo;
        this.tableStatus = tableStatus;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

}
