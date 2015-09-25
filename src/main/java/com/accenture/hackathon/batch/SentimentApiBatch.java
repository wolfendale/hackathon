package com.accenture.hackathon.batch;

import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisEntity;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;

import java.util.List;

public interface SentimentApiBatch {

    /**
     * Converts a list of comments into a list of sentiment analysis entities
     */
    List<SentimentAnalysisEntity> getSentimentAnalysis(List<String> comments);
}
