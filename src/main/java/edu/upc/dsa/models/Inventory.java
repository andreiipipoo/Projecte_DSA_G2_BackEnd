package edu.upc.dsa.models;

public class Inventory {
    String name;
    public String idPlayer;
    public String idItem;

    public Inventory() {
    }

    public Inventory(String name, String idPlayer, String idItem) {
        this.name = name;
        this.idPlayer = idPlayer;
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    @Override
    public String toString() {
        return "Inventory [name=" + name + ", idPlayer=" + idPlayer + ", idItem=" + idItem + "]";
    }
}
