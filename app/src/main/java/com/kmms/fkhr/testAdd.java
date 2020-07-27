package com.kmms.fkhr;

public class testAdd {
    String IsAttended;
    String date;
    String menu;
    String name;


    public testAdd() {

    }

    public testAdd(String isAttended, String date, String menu, String name) {
        IsAttended = isAttended;
        this.date = date;
        this.menu = menu;
        this.name = name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsAttended() {
        return IsAttended;
    }

    public void setIsAttended(String isAttended) {
        IsAttended = isAttended;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
