package com.accenture.hackathon.services.iod;

import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisLanguage;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Interface representing the SentimentAnalysis API
 */
public interface CallbackSentimentAnalysisService {

    String URL = "/api/sync/analyzesentiment/v1";

    /**
     * Analyze the sentiment of the given text using an API key provided by a {@link retrofit.RequestInterceptor}
     * @param text The text to analyze
     * @param language The language of the text
     * @return The sentiment of the response
     */
    @GET(URL)
    void analyzeSentimentForText(@Query("text") String text, @Query("language") SentimentAnalysisLanguage language, Callback<SentimentAnalysisResponse> callback);
}
