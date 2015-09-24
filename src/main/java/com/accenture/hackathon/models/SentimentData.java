package com.accenture.hackathon.models;

import java.util.List;

public class SentimentData {

    private double averageScore;
    private List<TopicData> sortedTopicData;

    public SentimentData() {}

    public SentimentData(double averageScore, List<TopicData> sortedTopicData) {
        this.averageScore = averageScore;
        this.sortedTopicData = sortedTopicData;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public List<TopicData> getSortedTopicData() {
        return sortedTopicData;
    }

    public void setSortedTopicData(List<TopicData> sortedTopicData) {
        this.sortedTopicData = sortedTopicData;
    }
}
