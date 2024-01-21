package edu.upc.dsa.models;

import edu.upc.dsa.CRUD.util.RandomUtils;

public class Item {

    private String id;
    private String name;
    private String description;
    private String type;
    private int price;
    private String image;

    //Void Constructor
    public Item() {
    }

    //Constructor
    public Item(String name, String description, String type, int price, String image) {
        this.id = RandomUtils.getId();
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.image = image;
    }

    //Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type + ", price="
                + price + ", image=" + image + "]";
    }
}
