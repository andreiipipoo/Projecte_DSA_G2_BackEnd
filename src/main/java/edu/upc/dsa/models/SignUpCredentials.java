package edu.upc.dsa.models;

public class SignUpCredentials {
    private String username;
    private String password;
    private String telephone;
    private String email;

    //Void constructor
    public SignUpCredentials() {
    }

    //Constructor
    public SignUpCredentials(String username, String password, String telephone, String email) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
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
}
