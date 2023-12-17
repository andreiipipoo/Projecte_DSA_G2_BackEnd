package edu.upc.dsa.models;

public class Shop {

    private String nombreItem;
    private String descripcionItem;
    private int precioItem;

    public Shop() {
    }

    public Shop(String nombreItem, String descripcionItem, int precioItem) {
        this.nombreItem = nombreItem;
        this.descripcionItem = descripcionItem;
        this.precioItem = precioItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public String getDescripcionItem() {
        return descripcionItem;
    }

    public void setDescripcionItem(String descripcionItem) {
        this.descripcionItem = descripcionItem;
    }

    public int getPrecioItem() {
        return precioItem;
    }

    public void setPrecioItem(int precioItem) {
        this.precioItem = precioItem;
    }
}
