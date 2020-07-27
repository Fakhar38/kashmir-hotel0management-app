package com.kmms.fkhr;

public class MenuModel {
    String menuitem1;
    String menuitem2;
    String menuitem3;
    String menuitem4;
    String menuitem5;
    int one,two,three,four,five;

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getThree() {
        return three;
    }

    public void setThree(int three) {
        this.three = three;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getFive() {
        return five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    Boolean isVisible;
    String startDateTime;
    String endDateTime;

    public MenuModel(String menuitem1, String menuitem2, String menuitem3, String menuitem4, String menuitem15) {
        this.menuitem1 = menuitem1;
        this.menuitem2 = menuitem2;
        this.menuitem3 = menuitem3;
        this.menuitem4 = menuitem4;
        this.menuitem5 = menuitem15;
    }

    public MenuModel(String menuitem1, String menuitem2, String menuitem3, String menuitem4, String menuitem5,int one,int two,int three,int four,int five, boolean isVisible, String startDateTime, String endDateTime) {
        this.menuitem1 = menuitem1;
        this.menuitem2 = menuitem2;
        this.menuitem3 = menuitem3;
        this.menuitem4 = menuitem4;
        this.menuitem5 = menuitem5;
        this.isVisible = isVisible;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.isVisible = isVisible;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;

    }
//   public MenuModel(String menuitem1, String menuitem2, String menuitem3, String menuitem4, String menuitem5, boolean isVisible, String startDateTime, String endDateTime) {
//        this.menuitem1 = menuitem1;
//        this.menuitem2 = menuitem2;
//        this.menuitem3 = menuitem3;
//        this.menuitem4 = menuitem4;
//        this.menuitem5 = menuitem5;
//        this.isVisible = isVisible;
//        this.startDateTime = startDateTime;
//        this.endDateTime = endDateTime;
//    }

    public MenuModel() {
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getMenuitem1() {
        return menuitem1;
    }

    public void setMenuitem1(String menuitem1) {
        this.menuitem1 = menuitem1;
    }

    public String getMenuitem2() {
        return menuitem2;
    }

    public void setMenuitem2(String menuitem2) {
        this.menuitem2 = menuitem2;
    }

    public String getMenuitem3() {
        return menuitem3;
    }

    public void setMenuitem3(String menuitem3) {
        this.menuitem3 = menuitem3;
    }

    public String getMenuitem4() {
        return menuitem4;
    }

    public void setMenuitem4(String menuitem4) {
        this.menuitem4 = menuitem4;
    }

    public String getMenuitem5() {
        return menuitem5;
    }

    public void setMenuitem5(String menuitem5) {
        this.menuitem5 = menuitem5;
    }
}
