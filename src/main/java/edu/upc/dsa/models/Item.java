package edu.upc.dsa.models;

import edu.upc.dsa.CRUD.util.RandomUtils;

public class Item {

    private String id;
    private String name;
    private String description;
    private String type;
    private int price;
    private String avatar;

    //Void Constructor
    public Item() {
    }

    //Constructor
    public Item(String name, String description, String type, int price) {
        this.id = RandomUtils.getId();
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.avatar = "images/" + name + ".png";
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type + ", price=" + price + ", avatar=" + avatar + "]";
    }
}
