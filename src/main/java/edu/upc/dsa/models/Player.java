package edu.upc.dsa.models;
import edu.upc.dsa.CRUD.util.RandomUtils;

public class Player {
    private String id;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private Integer croCoins;

    //Constructor
    public Player(String username, String password, String telephone, String email) {
        this.id = RandomUtils.getId();
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.croCoins = 200;
    }

    //Void constructor
    public Player() {
    }

    //Getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getCroCoins() {
        return croCoins;
    }

    public void setCroCoins(Integer croCoins) {
        this.croCoins = croCoins;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", coins=" + croCoins +
                '}';
    }
}
