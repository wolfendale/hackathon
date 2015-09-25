package com.accenture.hackathon.services;

import com.accenture.hackathon.models.APIKey;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisLanguage;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit.mime.TypedFile;

import java.io.File;

@Service
public class SentimentService {

    private static final SentimentAnalysisLanguage LANG = SentimentAnalysisLanguage.eng;

    private final SentimentAnalysisService sentimentAnalysisService;
    private final APIKey apiKey;

    @Autowired
    public SentimentService(SentimentAnalysisService sentimentAnalysisService, APIKey key) {
        this.sentimentAnalysisService = sentimentAnalysisService;
        this.apiKey = key;
    }

    public SentimentAnalysisResponse analyzeSentiment(String text) {
        return sentimentAnalysisService.analyzeSentimentForText(text, LANG);
    }

    public SentimentAnalysisResponse analyzeSentiment(File file) {
        return sentimentAnalysisService.analyzeSentimentForFile(apiKey.getKey(), new TypedFile("multipart/form-data", file), LANG);
    }
}
