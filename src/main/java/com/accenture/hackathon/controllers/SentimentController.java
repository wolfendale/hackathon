package com.accenture.hackathon.controllers;

import com.accenture.hackathon.batch.datasource.FeedbackBatch;
import com.accenture.hackathon.batch.sentiment.SentimentApiBatch;
import com.accenture.hackathon.models.SentimentData;
import com.accenture.hackathon.processor.SentimentAnalysisProcessor;
import com.accenture.hackathon.services.SentimentService;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SentimentController {

    private SentimentService service;
    private FeedbackBatch feedbackBatch;
    private SentimentApiBatch sentimentApiBatch;

    @Autowired
    public SentimentController(SentimentService service, FeedbackBatch feedbackBatch, SentimentApiBatch sentimentApiBatch) {
        this.service = service;
        this.feedbackBatch = feedbackBatch;
        this.sentimentApiBatch = sentimentApiBatch;
    }

    //TODO the big question is whether we are just snyc or async here - does it really matter? we may need to test both
    //TODO memory might be an issue with the batching but lets see

    @RequestMapping("/sentiment")
    public SentimentData sentiment(@RequestParam Map<String, String> params) {
        List<String> feedbackComments = feedbackBatch.getFeedback(params);

        List<SentimentAnalysisEntity> entities = sentimentApiBatch.getSentimentAnalysis(feedbackComments);

        //processing responses into meaningful data
        return SentimentAnalysisProcessor.process(entities);
    }
}
