package edu.upc.dsa.models;
import edu.upc.dsa.CRUD.util.RandomUtils;

public class Game {
    private String id;
    private String idPlayer;
    private boolean finished;
    private int croCoins;

    //Void constructor
    public Game() {
    }

    //Constructor
    public Game(String idPlayer, int croCoins) {
        this.id = RandomUtils.getId();
        this.idPlayer = idPlayer;
        this.finished = false;
        this.croCoins = croCoins;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getCroCoins() {
        return croCoins;
    }

    public void setCroCoins(int croCoins) {
        this.croCoins = croCoins;
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", idPlayer=" + idPlayer + ", finished=" + finished + ", croCoins=" + croCoins + "]";
    }
}
