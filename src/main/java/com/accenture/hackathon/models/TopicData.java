package com.accenture.hackathon.models;

import java.util.List;

public class TopicData {

    public String topic;
    public int count;
    public double averageScore;
    public String averageScoreColour;
    public List<String> originalComments;

    public TopicData() {}

    public TopicData(String topic, int count, double averageScore, String averageScoreColour, List<String> originalComments) {
        this.topic = topic;
        this.count = count;
        this.averageScore = averageScore;
        this.averageScoreColour = averageScoreColour;
        this.originalComments = originalComments;
    }
}
