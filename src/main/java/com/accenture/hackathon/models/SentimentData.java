package com.accenture.hackathon.models;

public class SentimentData {

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
