package model;

public class CartModel {
    String title,thumbnail,price;

    public CartModel(String title, String thumbnail, String price) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPrice() {
        return price;
    }
}
