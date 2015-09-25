package com.accenture.hackathon.models;

import java.util.List;

public class SentimentData {

    public int positiveCount;
    public int negativeCount;
    public int neutralCount;
    private double averageScore;
    private List<TopicData> sortedTopicData;

    public SentimentData() {}

    public SentimentData(int positiveCount, int negativeCount, int neutralCount, double averageScore, List<TopicData> sortedTopicData) {
        this.averageScore = averageScore;
        this.sortedTopicData = sortedTopicData;
        this.positiveCount = positiveCount;
        this.negativeCount = negativeCount;
        this.neutralCount = neutralCount;
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
