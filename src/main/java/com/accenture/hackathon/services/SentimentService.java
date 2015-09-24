package com.accenture.hackathon.services;

import com.accenture.hackathon.services.iod.SentimentAnalysisService;
import com.accenture.hackathon.util.ApiCallback;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisLanguage;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentimentService {
    private final SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    public SentimentService(SentimentAnalysisService sentimentAnalysisService) {
        this.sentimentAnalysisService = sentimentAnalysisService;
    }

    public void analyzeSentiment(String text, ApiCallback<SentimentAnalysisResponse> callback) {
        sentimentAnalysisService.analyzeSentimentForText(text, SentimentAnalysisLanguage.eng, callback);
    }
}
