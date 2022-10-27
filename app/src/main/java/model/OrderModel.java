package model;

import java.util.ArrayList;

public class OrderModel {
    String products;
    String orderDate;

    public OrderModel(String products, String orderDate) {
        this.products = products;
        this.orderDate = orderDate;
    }

    public String getProducts() {
        return products;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
