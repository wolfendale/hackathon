package com.accenture.hackathon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.List;

@Service
public class TwitterService {

    @Autowired
    public TwitterService(Twitter twitter) {
        this.twitter = twitter;
    }

    final private Twitter twitter;

    public List<Status> getTweets() throws TwitterException {
        final Query query = new Query("source:twitter4j yusukey");
        final QueryResult result = twitter.search(query);
        return result.getTweets();
    }
}
