package com.accenture.hackathon.batch.sentiment;

import com.accenture.hackathon.services.SentimentService;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisEntity;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisResponse;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileApiWithBatching implements SentimentApiBatch {

    private SentimentService service;

    public FileApiWithBatching(SentimentService service) {
        this.service = service;
    }

    @Override
    public List<SentimentAnalysisEntity> getSentimentAnalysis(List<String> comments) {
        StringBuilder commentSb = new StringBuilder();
        for (String comment : comments) {
            commentSb.append(comment.trim()).append("\n");
        }
        String commentBatch = commentSb.toString().trim();

        File file = new File("resource/comments.txt");
        try {
            FileUtils.writeStringToFile(file, commentBatch);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file");
        }

        SentimentAnalysisResponse response = service.analyzeSentiment(file);
        return aggregateResponses(response);
    }

    /**
     * Aggregate all the positive and negative responses into a single list
     */
    private List<SentimentAnalysisEntity> aggregateResponses(SentimentAnalysisResponse response) {
        List<SentimentAnalysisEntity> entities = new ArrayList<>();
        if (response.getNegative() != null) {
            entities.addAll(response.getNegative());
        }
        if (response.getPositive() != null) {
            entities.addAll(response.getPositive());
        }
        return entities;
    }
}
