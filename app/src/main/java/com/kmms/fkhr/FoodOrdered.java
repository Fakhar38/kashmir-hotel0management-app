package com.kmms.fkhr;

class FoodOrdered {
    String menu_name;
    String total_price;
    String order_date;

    public FoodOrdered() {
    }

    public FoodOrdered(String dishes_names, String dishes_price, String order_date) {
        this.menu_name = dishes_names;
        this.total_price = dishes_price;
        this.order_date = order_date;
    }

    public String getDishes_names() {
        return menu_name;
    }

    public void setDishes_names(String dishes_names) {
        this.menu_name = dishes_names;
    }

    public String getDishes_price() {
        return total_price;
    }

    public void setDishes_price(String dishes_price) {
        this.total_price = dishes_price;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

}
