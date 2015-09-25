package com.accenture.hackathon.controllers;

import com.accenture.hackathon.models.Response;
import com.accenture.hackathon.services.twitter.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    public DataController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    final private TwitterService twitterService;

    @RequestMapping("/tweets/{query}")
    public Response<List<Status>> getTweets(
        @PathVariable("query") String query
    ) {
        try {
            return new Response<>(
              twitterService.getTweets(query)
            );
        } catch (TwitterException e) {
            return new Response<>(e);
        }
    }
}
