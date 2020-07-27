package com.kmms.fkhr;

public class test {
    String IsAttended;
    String date;

    public test(String isAttended, String date) {
        IsAttended = isAttended;
        this.date = date;
    }

    public test() {

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
