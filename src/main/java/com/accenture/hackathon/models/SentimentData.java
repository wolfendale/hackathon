package com.accenture.hackathon.models;

public class SentimentData {
    //TODO information to be given to the frontend (through AJAX)

    private double averageScore;

    public SentimentData() {}

    public SentimentData(double averageScore) {
        this.averageScore = averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public double getAverageScore() {
        return averageScore;
    }
}
