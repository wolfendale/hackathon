package com.accenture.hackathon.batch;

import com.accenture.hackathon.services.SentimentService;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisEntity;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;

import java.util.ArrayList;
import java.util.List;

public class TextApiNoBatching implements SentimentApiBatch {

    private SentimentService service;

    public TextApiNoBatching(SentimentService service) {
        this.service = service;
    }

    @Override
    public List<SentimentAnalysisEntity> getSentimentAnalysis(List<String> comments) {
        List<SentimentAnalysisResponse> responses = new ArrayList<>();
        for (String comment : comments) {
            SentimentAnalysisResponse response = service.analyzeSentiment(comment);
            if (response.getNegative().isEmpty() && response.getPositive().isEmpty()) continue;
            responses.add(response);
        }
        return aggregateResponses(responses);
    }

    /**
     * Aggregate all the positive and negative responses into a single list
     */
    private List<SentimentAnalysisEntity> aggregateResponses(List<SentimentAnalysisResponse> sentimentAnalysisResponses) {
        List<SentimentAnalysisEntity> entities = new ArrayList<>();
        for (SentimentAnalysisResponse response : sentimentAnalysisResponses) {
            if (response.getNegative() != null) {
                entities.addAll(response.getNegative());
            }
            if (response.getPositive() != null) {
                entities.addAll(response.getPositive());
            }
        }
        return entities;
    }
}
