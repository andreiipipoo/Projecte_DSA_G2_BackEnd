package edu.upc.dsa.models;

public class Item {

    //Attributes

    //private int id;

    private String name;
    private String description;
    private String type;
    private int price;

    //Constructor

    public Item() {
    }

    public Item(String name, String description, String type, int price) {
        this();
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    //Getters and setters

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
