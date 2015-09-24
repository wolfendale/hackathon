package com.accenture.hackathon.processor;

import com.accenture.hackathon.models.SentimentalData;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisEntity;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains logic for producing meaningful information from the sentimental analysis responses
 */
public class SentimentalAnalysisProcessor {

    public static SentimentalData processResponses(List<SentimentAnalysisResponse> sentimentalAnalysisResponses) {
        List<SentimentAnalysisEntity> entities = new ArrayList<SentimentAnalysisEntity>();
        for (SentimentAnalysisResponse response : sentimentalAnalysisResponses) {
            if (response.getNegative() != null) {
                entities.addAll(response.getNegative());
            }
            if (response.getPositive() != null) {
                entities.addAll(response.getPositive());
            }
        }
        return process(entities);
    }

    public static SentimentalData process(List<SentimentAnalysisEntity> sentimentalEntities) {

        //TODO create some meaningful data

        return new SentimentalData();
    }
}
