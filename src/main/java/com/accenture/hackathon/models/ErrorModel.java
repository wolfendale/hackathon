package com.accenture.hackathon.models;

public class ErrorModel {

    public ErrorModel(String message) {
        this.message = message;
    }

    public ErrorModel(Exception e) {
        this.message = e.getMessage();
    }

    final private String message;

    public String getMessage() {
        return message;
    }
}
