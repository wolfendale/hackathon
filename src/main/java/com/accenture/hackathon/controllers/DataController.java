package com.accenture.hackathon.controllers;

import com.accenture.hackathon.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController {

    @Autowired
    public DataController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    final private TwitterService twitterService;

    @RequestMapping("/tweets")
    public List<Status> getTweets() {
        try {
            return twitterService.getTweets();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Status>();
        }
    }
}
