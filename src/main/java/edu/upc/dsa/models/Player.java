package edu.upc.dsa.models;



public class Player {

    //Attributes

    //int id;
    String username;
    String password;
    String telephone;
    String email;
    int coins;

    //Constructor
    public Player() {
    }


    public Player(String username, String password, String telephone, String email) {
        this();
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.coins = 200;
    }

    //Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
