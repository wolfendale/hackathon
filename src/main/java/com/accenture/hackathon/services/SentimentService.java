package com.accenture.hackathon.services;

import com.accenture.hackathon.models.APIKey;
import com.accenture.hackathon.services.iod.CallbackSentimentAnalysisService;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisLanguage;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit.Callback;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

import java.io.File;

@Service
public class SentimentService {
    private static final SentimentAnalysisLanguage LANG = SentimentAnalysisLanguage.eng;

    private final CallbackSentimentAnalysisService callbackSentimentAnalysisService;
    private final SentimentAnalysisService sentimentAnalysisService;
    private final APIKey apiKey;

    @Autowired
    public SentimentService(CallbackSentimentAnalysisService callbackSentimentAnalysisService, SentimentAnalysisService sentimentAnalysisService, APIKey key) {
        this.callbackSentimentAnalysisService = callbackSentimentAnalysisService;
        this.sentimentAnalysisService = sentimentAnalysisService;
        this.apiKey = key;
    }

    public void analyzeSentiment(String text, Callback<SentimentAnalysisResponse> callback) {
        callbackSentimentAnalysisService.analyzeSentimentForText(text, LANG, callback);
    }

    public SentimentAnalysisResponse analyzeSentiment(String text) {
        return sentimentAnalysisService.analyzeSentimentForText(text, LANG);
    }

    public SentimentAnalysisResponse analyzeSentiment(File file) {
        return sentimentAnalysisService.analyzeSentimentForFile(apiKey.getKey(), new TypedFile("multipart/form-data", file), LANG);
    }
}
