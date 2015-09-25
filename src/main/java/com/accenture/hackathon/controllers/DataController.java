package com.accenture.hackathon.controllers;

import com.accenture.hackathon.models.TweetsResponse;
import com.accenture.hackathon.services.twitter.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @Autowired
    public DataController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    final private TwitterService twitterService;

    @RequestMapping("/tweets/{query}")
    public TweetsResponse getTweets(
        @PathVariable("query") String query
    ) {
        return twitterService.getTweets(query);
    }
}
