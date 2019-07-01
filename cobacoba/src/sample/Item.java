package sample;

import javafx.beans.property.SimpleStringProperty;

public class Item {

    private SimpleStringProperty id , name , price , date , image , soldPrice, revenue;

    public Item(String id, String name , String price , String date , String image , String soldPrice , String revenue){
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.date = new SimpleStringProperty(date);
        this.image = new SimpleStringProperty(image);
        this.soldPrice = new SimpleStringProperty(soldPrice);
        this.revenue = new SimpleStringProperty(revenue);

    }

    public String getName() {
        return name.get();
    }

    public String getPrice() {
        return price.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getImage() {
        return image.get();
    }

    public String getId() {
        return id.get();
    }


    public String getSoldPrice() {
        return soldPrice.get();
    }

    public String getRevenue() {
        return revenue.get();
    }
}
