package com.accenture.hackathon.processor;

import com.accenture.hackathon.models.SentimentData;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisEntity;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;

import java.util.ArrayList;
import java.util.List;

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


        double averageScore = getAverageScore(entities);

        return new SentimentData(averageScore);
    }

    private static double getAverageScore(List<SentimentAnalysisEntity> entities) {
        double totalScore = 0;
        int entityCount = entities.size();
        if (entityCount == 0) return 0;
        for (SentimentAnalysisEntity entity: entities) {
            totalScore += entity.getScore();
        }
        return totalScore / entityCount;
    }
}
