package com.accenture.hackathon.processor;

import com.accenture.hackathon.models.SentimentData;
import com.accenture.hackathon.models.TopicData;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisEntity;

import java.util.*;

/**
 * Contains logic for producing meaningful information from the sentimental analysis responses
 */
public class SentimentAnalysisProcessor {

    private static int MAX_TOPIC_COUNT = 30;

    /**
     * Provide some meaningful data / statistics etc. to go the frontend
     */
    public static SentimentData process(List<String> originalMessages, List<SentimentAnalysisEntity> entities) {
        List<TopicData> sortedTopics = getTopicList(originalMessages, entities);
        if (sortedTopics.size() > MAX_TOPIC_COUNT) {
            sortedTopics = sortedTopics.subList(0, MAX_TOPIC_COUNT);
        }
        double averageScore = getAverageScore(entities);
        int positiveCount = getPositiveCount(entities);
        int negativeCount = getNegativeCount(entities);
        int neutralCount = getNeutralCount(entities);
        return new SentimentData(positiveCount, negativeCount, neutralCount, averageScore, sortedTopics);
    }

    private static final double NEUTRAL_THRESHOLD = 0.3;
    private static int getNegativeCount(List<SentimentAnalysisEntity> entities) {
        int count = 0;
        for (SentimentAnalysisEntity entity: entities) {
            if (entity.getScore() > -NEUTRAL_THRESHOLD) continue;
            count++;
        }
        return count;
    }

    private static int getPositiveCount(List<SentimentAnalysisEntity> entities) {
        int count = 0;
        for (SentimentAnalysisEntity entity: entities) {
            if (entity.getScore() < NEUTRAL_THRESHOLD) continue;
            count++;
        }
        return count;
    }

    private static int getNeutralCount(List<SentimentAnalysisEntity> entities) {
        int count = 0;
        for (SentimentAnalysisEntity entity: entities) {
            if (entity.getScore() > NEUTRAL_THRESHOLD || entity.getScore() < -NEUTRAL_THRESHOLD) continue;
            count++;
        }
        return count;
    }

    private static List<TopicEntities> getTopicsByCount(List<SentimentAnalysisEntity> entities) {
        //count topics
        HashMap<String, List<SentimentAnalysisEntity>> topicCountMap = new HashMap<>(entities.size());
        for (SentimentAnalysisEntity entity: entities) {
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

    private static List<TopicData> getTopicList(List<String> originalMessages, List<SentimentAnalysisEntity> entities) {
        List<TopicEntities> topicEntities = getTopicsByCount(entities);
        List<TopicData> topicData = new ArrayList<>(topicEntities.size());
        for (TopicEntities t: topicEntities) {
            double averageScore = getAverageScore(t.entities);
            String colour = SentimentToColourConverter.generateColor(averageScore);
            topicData.add(new TopicData(t.topic, t.getCount(), averageScore, colour, getAverageOriginalMessages(originalMessages, t)));
        }
        return topicData;
    }

    private static List<String> getAverageOriginalMessages(List<String> originalMessages, TopicEntities topicEntities) {
        List<String> messageList = getOriginalMessagesFromTopic(originalMessages, topicEntities);
        HashMap<String, Integer> commentCountMap = new HashMap<>();
        for (String msg : messageList) {
            Integer count = commentCountMap.get(msg);
            commentCountMap.put(msg, count == null ? 1 : count + 1);
        }

        List<MessageCounts> messageCounts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : commentCountMap.entrySet()) {
            messageCounts.add(new MessageCounts(entry.getKey(), entry.getValue()));
        }

        //sort original msgs by count
        Collections.sort(messageCounts, new Comparator<MessageCounts>() {
            @Override
            public int compare(MessageCounts o1, MessageCounts o2) {
                return o2.count - o1.count;
            }
        });

        List<String> orderedMsgs = new ArrayList<>();
        for (MessageCounts messageCount : messageCounts) {
            orderedMsgs.add(messageCount.originalMsg);
        }

        if (orderedMsgs.size() > 5) {
            orderedMsgs = orderedMsgs.subList(0, 5);
        }

        return orderedMsgs;
    }

    private static List<String> getOriginalMessagesFromTopic(List<String> originalMessages, TopicEntities topicEntities) {
        List<String> strs = new ArrayList<>();
        for (SentimentAnalysisEntity entity: topicEntities.entities) {
            String msg = getOriginalMessage(originalMessages, entity);
            if (msg != null) strs.add(msg);
        }
        return strs;
    }

    private static String getOriginalMessage(List<String> originalMessages, SentimentAnalysisEntity entity) {
        for (String originalMsg : originalMessages) {
            if (originalMsg.contains(entity.getOriginalText())) {
                return originalMsg;
            }
        }
        return null;
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

    private static class MessageCounts {
        private String originalMsg;
        private int count;

        MessageCounts(String originalMsg, int count) {
            this.originalMsg = originalMsg;
            this.count = count;
        }
    }
}
