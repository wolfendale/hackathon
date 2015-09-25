package com.accenture.hackathon.batch.datasource;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.CharEncoding;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NewLineCsvBatch implements FeedbackBatch {
    private String fileName;

    public NewLineCsvBatch(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String> getFeedback(Map<String, String> params) {
        File csvData = new File(fileName);

        List<String> comments = new LinkedList<>();
        try {
            for (String line: FileUtils.readLines(csvData, "UTF-8")) {
                comments.add(line.substring(1, line.length() - 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to read CSV file");
        }

        return comments;
    }
}
