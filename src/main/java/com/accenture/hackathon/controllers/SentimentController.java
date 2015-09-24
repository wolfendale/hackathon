package com.accenture.hackathon.controllers;

import com.accenture.hackathon.batch.FeedbackBatch;
import com.accenture.hackathon.models.SentimentalData;
import com.accenture.hackathon.processor.SentimentalAnalysisProcessor;
import com.accenture.hackathon.services.SentimentService;
import com.accenture.hackathon.util.ApiCallback;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SentimentController {

    private SentimentService service;
    private FeedbackBatch feedbackBatch;

    @Autowired
    public SentimentController(SentimentService service, FeedbackBatch feedbackBatch) {
        this.service = service;
        this.feedbackBatch = feedbackBatch;
    }

    //TODO the big question is whether we are just snyc or async here - does it really matter? we may need to test both
    //TODO memory might be an issue with the batching but lets see
    //TODO make it return JSON

    @RequestMapping("/sentiment")
    public SentimentalData batchSentiment() {
        //TODO pass in either a string to the feedback batch or a list of args?
        List<String> feedbackComments = feedbackBatch.getFeedback();

        //call the sentiment analysis API multiple times from the batch process
        List<SentimentAnalysisResponse> responses = new ArrayList<>();
        for (String comment : feedbackComments) {
            SentimentAnalysisResponse response = service.analyzeSentiment(comment);
            if (response.getNegative().isEmpty() && response.getPositive().isEmpty()) continue;
            responses.add(response);
        }

        //processing responses into meaningful data
        return SentimentalAnalysisProcessor.processResponses(responses);
    }
}
