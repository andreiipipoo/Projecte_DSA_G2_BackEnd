package edu.upc.dsa.models;

import edu.upc.dsa.CRUD.util.RandomUtils;
import edu.upc.dsa.exceptions.YouAreBrokeWithCoinsException;

public class Player {
    String idPlayer;
    String username;
    String password;
    String telephone;
    String email;
    int trappycoins;

    public Player() {
    }

    public Player(String idPlayer, String username, String password, String telephone, String email) {
        this();
        this.idPlayer = RandomUtils.getId();
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.trappycoins = 300;
    }
    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        return;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        return;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
        return;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        return;
    }

    public int getTrappycoins() {
        return trappycoins;
    }

    public void setTrappycoins(int trappycoins) {
        this.trappycoins = trappycoins;
        return;
    }

    @Override
    public String toString() {
        return "Player [idPlayer=" + idPlayer + ", username=" + username + ", password=" + password + ", telephone="
                + telephone + ", email=" + email + ", trappycoins=" + trappycoins + "]";
    }

    public void purchaseItem(Item item) throws YouAreBrokeWithCoinsException{
        if(item.getPrice()>this.trappycoins) {
            throw new YouAreBrokeWithCoinsException();
        }
        else{
            this.trappycoins -= item.getPrice();
        }
    }

    public void update(String username, String password, String telephone, String email) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
    }
}
