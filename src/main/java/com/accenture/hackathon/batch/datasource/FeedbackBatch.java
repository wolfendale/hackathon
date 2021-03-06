package com.accenture.hackathon.batch.datasource;

import java.util.List;
import java.util.Map;

public interface FeedbackBatch {
    /**
     * Retrieves a list of comments/feedback
     */
    List<String> getFeedback(Map<String, String> params);
}
