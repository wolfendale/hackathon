package com.accenture.hackathon.controllers;

import com.accenture.hackathon.services.SentimentService;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisLanguage;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisService;
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

    @RequestMapping("/sentiment")
    public String sentiment() {

        final SentimentAnalysisResponse response = service.sentiment("I like cats");

        return response.toString();
    }
}
