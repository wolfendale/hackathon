package com.accenture.hackathon.batch.datasource;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.CharEncoding;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StaticCsvBatch implements FeedbackBatch {
    private String fileName;

    public StaticCsvBatch(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String> getFeedback(Map<String, String> params) {
        File csvData = new File(fileName);
        CSVParser parser = null;
        try {
            parser = CSVParser.parse(csvData, Charset.forName(CharEncoding.UTF_8), CSVFormat.RFC4180);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to read CSV file");
        }

        List<String> comments = new ArrayList<>();
        for (CSVRecord csvRecord : parser) {
            if (csvRecord.size() > 1) throw new RuntimeException("More than we expected");
            String comment = csvRecord.get(0);
            comments.add(comment);
        }

        return comments;
    }
}
