package com.accenture.hackathon.services.twitter;

import com.accenture.hackathon.models.TweetsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class TwitterService {

    @Autowired
    public TwitterService(Twitter twitter) {
        this.twitter = twitter;
    }

    final private Twitter twitter;

    public TweetsResponse getTweets(String terms) {

        try {

            final List<List<Status>> results = new ArrayList<>();
            long maxId = 0;

            for (int i = 0; i < 10; i++) {

                final Query query = new Query(terms);
                query.setCount(100);

                if (i > 0) {
                    query.setMaxId(maxId);
                }

                final QueryResult result = twitter.search(query);
                final List<Status> tweets = result.getTweets();
                maxId = tweets.get(tweets.size() - 1).getId() - 1;
                results.add(tweets);
            }

            final List<Status> tweets = new LinkedList<>();

            for (List<Status> result: results) {
                tweets.addAll(result);
            }

            return new TweetsResponse(
                tweets
            );
        } catch (TwitterException e) {
            return new TweetsResponse(e);
        }
    }
}
