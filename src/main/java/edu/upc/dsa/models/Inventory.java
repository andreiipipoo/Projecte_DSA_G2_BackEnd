package edu.upc.dsa.models;
import edu.upc.dsa.CRUD.util.RandomUtils;

public class Inventory {
    private String id;
    private String idPlayer;
    private String idItem;

    //Void constructor
    public Inventory() {
    }

    //Constructor
    public Inventory(String idPlayer, String idItem) {
        this.id = RandomUtils.getId();
        this.idPlayer = idPlayer;
        this.idItem = idItem;
    }

    //Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }
}
