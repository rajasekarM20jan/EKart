package model;

import java.util.ArrayList;

public class OrderModel {
    ArrayList products;
    String orderDate;

    public OrderModel(ArrayList products, String orderDate) {
        this.products = products;
        this.orderDate = orderDate;
    }

    public ArrayList getProducts() {
        return products;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
