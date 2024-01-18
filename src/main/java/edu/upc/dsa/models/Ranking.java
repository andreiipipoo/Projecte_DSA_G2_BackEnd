package edu.upc.dsa.models;

public class Ranking {
    String username;
    int score;

    //Void constructor
    public Ranking() {
    }

    //Constructor
    public Ranking(String username, int score) {
        this.username = username;
        this.score = score;
    }

    //Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
