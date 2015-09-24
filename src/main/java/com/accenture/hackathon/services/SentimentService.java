package com.accenture.hackathon.services;

import com.accenture.hackathon.models.APIKey;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisLanguage;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentimentService {

    @Autowired
    public SentimentService(
            SentimentAnalysisService service,
            APIKey apiKey
        ) {
        this.service = service;
        this.apiKey = apiKey.getKey();
    }

    private SentimentAnalysisService service;
    private String apiKey;

    public SentimentAnalysisResponse sentiment(String text) {
        return service.analyzeSentimentForText(apiKey, text, SentimentAnalysisLanguage.eng);
    }
}
