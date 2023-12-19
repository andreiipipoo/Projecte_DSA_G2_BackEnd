package edu.upc.dsa.models;

public class UpdatePlayer {
    String idPlayer;
    String username;
    String password;
    String telephone;
    String email;

    public UpdatePlayer() {
    }

    public UpdatePlayer(String idPlayer, String username, String password, String telephone, String email) {
        this.idPlayer = idPlayer;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
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

    @Override

    public String toString() {
        return "UpdatePlayer [idPlayer=" + idPlayer + ", username=" + username + ", password=" + password + ", telephone=" + telephone + ", email=" + email + "]";
    }
}
