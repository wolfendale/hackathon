package com.accenture.hackathon.batch;

import java.util.Arrays;
import java.util.List;

public class TwitterFeedbackBatch implements FeedbackBatch {
    @Override
    public List<String> getFeedback() {
        return Arrays.asList("TODO", "PLEASE");
    }
}
