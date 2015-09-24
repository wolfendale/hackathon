package com.accenture.hackathon.controllers;

import com.accenture.hackathon.services.SentimentService;
import com.accenture.hackathon.util.ApiCallback;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentimentController {

    @Autowired
    public SentimentController(SentimentService service) {
        this.service = service;
    }

    private SentimentService service;

    //TODO the big question is whether we are just snyc or async here - does it really matter?

    @RequestMapping("/sentiment")
    public String sentiment() {
        service.analyzeSentiment("I love cats", new ApiCallback<SentimentAnalysisResponse>() {
            @Override
            public void success(SentimentAnalysisResponse sentimentAnalysisResponse) {

            }
        });
        return "Test";
    }
}
