package edu.upc.dsa.models;

public class Badges {
    private String name;
    private String avatar;

    //Constructor
    public Badges() {
    }

    //Constructor
    public Badges(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    //Getters & Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    //toString
    @Override
    public String toString() {
        return "Badges{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
