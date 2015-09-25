package com.accenture.hackathon.services.twitter;

import com.accenture.hackathon.models.CacheEntry;
import com.accenture.hackathon.models.Response;
import com.accenture.hackathon.services.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.LinkedList;
import java.util.List;

@Service
public class TwitterService {

    @Autowired
    public TwitterService(
            Twitter twitter,
            CacheService cacheService
    ) {
        this.twitter = twitter;
        this.cacheService = cacheService;
    }

    final private Twitter twitter;
    final private CacheService cacheService;

    public List<Status> getTweets(String terms) throws TwitterException {

        final List<Status> totalResult = new LinkedList<>();
        final Response<CacheEntry<List<Status>>> cacheResult = cacheService.getStatus(terms);

        if (cacheResult.failed()) {

            System.out.println("Cache miss :(");

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
                totalResult.addAll(tweets);
            }

            cacheService.putStatus(terms, totalResult);
        } else {
            System.out.println("Cache hit!");
            totalResult.addAll(cacheResult.getData().getData());
        }

        return totalResult;
    }
}
