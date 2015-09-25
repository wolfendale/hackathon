package com.accenture.hackathon.services.twitter;

import com.accenture.hackathon.models.TweetsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

@Service
public class TwitterService {

    @Autowired
    public TwitterService(Twitter twitter) {
        this.twitter = twitter;
    }

    final private Twitter twitter;

    public TweetsResponse getTweets(String query) {

        System.out.println(query);

        try {

            final QueryResult result = twitter.search(
                new Query(query)
            );

            return new TweetsResponse(
                result.getTweets()
            );
        } catch (TwitterException e) {
            return new TweetsResponse(e);
        }
    }
}
