package com.accenture.hackathon.batch;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestFeedbackBatch implements FeedbackBatch {
    @Override
    public List<String> getFeedback(Map<String, String> params) {
        return Arrays.asList("This is some feedback that I really like to show you", "Some more test feedback woop woop");
    }
}
