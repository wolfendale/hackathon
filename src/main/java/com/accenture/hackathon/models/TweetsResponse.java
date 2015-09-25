package com.accenture.hackathon.models;

import twitter4j.Status;

import java.util.List;

public class TweetsResponse {

    public TweetsResponse(List<Status> tweets, ErrorModel error) {
        this.error = error;
        this.tweets = tweets;
    }

    public TweetsResponse(List<Status> tweets) {
        this.tweets = tweets;
    }

    public TweetsResponse(Exception e) {
        this.error = new ErrorModel(e);
    }

    private ErrorModel error;
    private List<Status> tweets;

    public ErrorModel getError() {
        return error;
    }

    public List<Status> getTweets() {
        return tweets;
    }
}
