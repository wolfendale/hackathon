package com.accenture.hackathon.models;

public class TopicData {

    public String topic;
    public int count;
    public double averageScore;
    public String averageScoreColour;

    public TopicData() {}

    public TopicData(String topic, int count, double averageScore, String averageScoreColour) {
        this.topic = topic;
        this.count = count;
        this.averageScore = averageScore;
        this.averageScoreColour = averageScoreColour;
    }
}
