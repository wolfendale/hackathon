package com.accenture.hackathon.controllers;

import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisLanguage;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisService;
import com.hp.autonomy.iod.client.converter.IodConverter;
import com.hp.autonomy.iod.client.error.IodErrorHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

@RestController
public class SentimentController {

    @RequestMapping("/sentiment")
    public String sentiment() {

        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.idolondemand.com/1")
                .setConverter(new IodConverter(new JacksonConverter()))
                .setErrorHandler(new IodErrorHandler())
                .build();

        final SentimentAnalysisService service = restAdapter.create(SentimentAnalysisService.class);

        final SentimentAnalysisResponse response = service.analyzeSentimentForText("26340f38-75ff-458d-8a3d-b66a76582263", "I like cats", SentimentAnalysisLanguage.eng);

        return response.toString();
    }
}
