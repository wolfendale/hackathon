package com.accenture.hackathon.batch.datasource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TwitterFeedbackBatch implements FeedbackBatch {
    @Override
    public List<String> getFeedback(Map<String, String> params) {
        return Arrays.asList("TODO", "PLEASE");
    }
}
