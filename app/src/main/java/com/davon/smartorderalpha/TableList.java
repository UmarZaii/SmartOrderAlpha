package com.davon.smartorderalpha;

public class TableList {

    private String orderID;
    private String staffView;
    private String tableNo;
    private String tableStatus;

    public TableList() {

    }

    public TableList(String orderID, String staffView, String tableStatus, String tableNo) {
        this.orderID = orderID;
        this.staffView = staffView;
        this.tableStatus = tableStatus;
        this.tableNo = tableNo;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getStaffView() {
        return staffView;
    }

    public void setStaffView(String staffView) {
        this.staffView = staffView;
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
