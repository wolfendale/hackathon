package com.accenture.hackathon.models;

public class TopicData {

    public String topic;
    public int count;
    public double averageScore;

    public TopicData() {}

    public TopicData(String topic, int count, double averageScore) {
        this.topic = topic;
        this.count = count;
        this.averageScore = averageScore;
    }

}
