package com.accenture.hackathon.processor;

import com.accenture.hackathon.models.SentimentData;
import com.accenture.hackathon.models.TopicData;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisEntity;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;

import java.util.*;

/**
 * Contains logic for producing meaningful information from the sentimental analysis responses
 */
public class SentimentAnalysisProcessor {

    /**
     * Aggregate all the positive and negative responses into a single list
     */
    public static SentimentData processResponses(List<SentimentAnalysisResponse> sentimentAnalysisResponses) {
        List<SentimentAnalysisEntity> entities = new ArrayList<>();
        for (SentimentAnalysisResponse response : sentimentAnalysisResponses) {
            if (response.getNegative() != null) {
                entities.addAll(response.getNegative());
            }
            if (response.getPositive() != null) {
                entities.addAll(response.getPositive());
            }
        }
        return process(entities);
    }

    /**
     * Provide some meaningful data / statistics etc. to go the frontend
     */
    public static SentimentData process(List<SentimentAnalysisEntity> entities) {
        //TODO create some meaningful data

        List<TopicData> sortedTopics = getTopicList(entities);
        double averageScore = getAverageScore(entities);

        return new SentimentData(averageScore, sortedTopics);
    }

    private static List<TopicEntities> getTopicsByCount(List<SentimentAnalysisEntity> entities) {
        //count topics
        HashMap<String, List<SentimentAnalysisEntity>> topicCountMap = new HashMap<>(entities.size());
        for (SentimentAnalysisEntity entity: entities) {
            //TODO here - is this information ok to ignore or should be put in summary somehow?
            if (entity.getTopic() == null || entity.getTopic().trim().isEmpty()) continue;
            List<SentimentAnalysisEntity> topicEntities = topicCountMap.get(entity.getTopic());
            if (topicEntities == null) {
                topicEntities = new ArrayList<>(4);
                topicCountMap.put(entity.getTopic(), topicEntities);
            }
            topicEntities.add(entity);
        }
        List<TopicEntities> topicEntities = new ArrayList<>(entities.size());
        for (Map.Entry<String, List<SentimentAnalysisEntity>> entry : topicCountMap.entrySet()) {
            topicEntities.add(new TopicEntities(entry.getKey(), entry.getValue()));
        }

        //sort topics by count
        Collections.sort(topicEntities, new Comparator<TopicEntities>() {
            @Override
            public int compare(TopicEntities o1, TopicEntities o2) {
                return o2.getCount() - o1.getCount();
            }
        });

        return topicEntities;
    }

    private static List<TopicData> getTopicList(List<SentimentAnalysisEntity> entities) {
        List<TopicEntities> topicEntities = getTopicsByCount(entities);
        List<TopicData> topicData = new ArrayList<>(topicEntities.size());
        for (TopicEntities t: topicEntities) {
            topicData.add(new TopicData(t.topic, t.getCount(), getAverageScore(t.entities)));
        }
        return topicData;
    }

    private static double getAverageScore(List<SentimentAnalysisEntity> entities) {
        if (entities == null || entities.isEmpty()) return 0;
        double totalScore = 0;
        for (SentimentAnalysisEntity entity: entities) {
            totalScore += entity.getScore();
        }
        return totalScore / entities.size();
    }

    /**
     * Intermediate class used just during the processing of the data
     */
    private static class TopicEntities {
        private String topic;
        private List<SentimentAnalysisEntity> entities;

        TopicEntities(String topic, List<SentimentAnalysisEntity> entities) {
            this.topic = topic;
            this.entities = entities;
        }

        public int getCount() {
            return entities == null ? 0 : entities.size();
        }
    }
}
