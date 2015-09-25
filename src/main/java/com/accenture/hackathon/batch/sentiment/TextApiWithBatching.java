package com.accenture.hackathon.batch.sentiment;

import com.accenture.hackathon.services.SentimentService;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisEntity;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;

import java.util.ArrayList;
import java.util.List;

public class TextApiWithBatching implements SentimentApiBatch {

    private SentimentService service;

    public TextApiWithBatching(SentimentService service) {
        this.service = service;
    }

    @Override
    public List<SentimentAnalysisEntity> getSentimentAnalysis(List<String> comments) {
        StringBuilder commentSb = new StringBuilder();
        for (String comment : comments) {
            commentSb.append(comment.trim()).append(". ");
        }
        SentimentAnalysisResponse response = service.analyzeSentiment(commentSb.toString().trim());
        return aggregateResponses(response);
    }

    /**
     * Aggregate all the positive and negative responses into a single list
     */
    private List<SentimentAnalysisEntity> aggregateResponses(SentimentAnalysisResponse response) {
        List<SentimentAnalysisEntity> entities = new ArrayList<>();
        if (response.getNegative() != null) {
            entities.addAll(response.getNegative());
        }
        if (response.getPositive() != null) {
            entities.addAll(response.getPositive());
        }
        return entities;
    }
}
