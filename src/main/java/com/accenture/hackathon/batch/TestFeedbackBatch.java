package com.accenture.hackathon.batch;

import java.util.Arrays;
import java.util.List;

public class TestFeedbackBatch implements FeedbackBatch {
    @Override
    public List<String> getFeedback() {
        return Arrays.asList("This is some feedback that I really like to show you", "Some more test feedback woop woop");
    }
}
