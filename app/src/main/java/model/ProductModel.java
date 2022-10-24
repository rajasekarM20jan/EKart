package model;

public class ProductModel {

    String title,description,price;
    String thumbnail,category,brand;

    public ProductModel(String title,String description,String price,String thumbnail,String category,String brand){
        this.title=title;
        this.description=description;
        this.price=price;
        this.brand=brand;
        this.category=category;
        this.thumbnail=thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getThumbnail() {
        return thumbnail;
    }


    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }
}
