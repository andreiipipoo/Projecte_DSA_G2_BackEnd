package edu.upc.dsa.models;

public class Item {

    //Attributes

    String id;
    String name;
    String description;
    String type;
    int price;

    //Constructor

    public Item() {
    }

    public Item(String id, String name, String description, String type, int price) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.type=type;
        this.setPrice(price);
    }

    //Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price=price;
    }
}
