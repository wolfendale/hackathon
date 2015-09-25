package com.accenture.hackathon.controllers;

import com.accenture.hackathon.batch.datasource.FeedbackBatch;
import com.accenture.hackathon.batch.sentiment.SentimentApiBatch;
import com.accenture.hackathon.models.Response;
import com.accenture.hackathon.models.SentimentData;
import com.accenture.hackathon.processor.SentimentAnalysisProcessor;
import com.accenture.hackathon.services.SentimentService;
import com.accenture.hackathon.services.twitter.TwitterService;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class SentimentController {

    private SentimentService service;
    private FeedbackBatch feedbackBatch;
    private SentimentApiBatch sentimentApiBatch;
    private TwitterService twitterService;

    @Autowired
    public SentimentController(SentimentService service, FeedbackBatch feedbackBatch, SentimentApiBatch sentimentApiBatch, TwitterService twitterService) {
        this.service = service;
        this.feedbackBatch = feedbackBatch;
        this.sentimentApiBatch = sentimentApiBatch;
        this.twitterService = twitterService;
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

    @RequestMapping("/twitter-sentiment/{query}")
    public Response<SentimentData> twitterSentiment(
        @PathVariable("query") String query
    ) {
        try {

            final List<String> comments = new LinkedList<>();

            for (Status status : twitterService.getTweets(query)) {
                comments.add(status.getText());
            }

            final List<SentimentAnalysisEntity> entities = sentimentApiBatch.getSentimentAnalysis(comments);
            return new Response<SentimentData>(
                SentimentAnalysisProcessor.process(entities)
            );
        } catch (TwitterException e) {
            return new Response<>(e);
        }
    }
}
