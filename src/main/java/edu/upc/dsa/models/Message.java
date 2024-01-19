package edu.upc.dsa.models;

public class Message {
    String message;

    //Constructor
    public Message() {
    }

    //Constructor
    public Message(String message) {
        this.message = message;
    }

    //Getters & Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
