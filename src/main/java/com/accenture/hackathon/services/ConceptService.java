package com.accenture.hackathon.services;

import com.accenture.hackathon.models.iod.ConceptExtractionResponse;
import com.accenture.hackathon.services.iod.ConceptExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit.mime.TypedFile;

import java.io.File;

@Service
public class ConceptService {

    @Autowired
    public ConceptService(ConceptExtractionService conceptService) {
        this.conceptService = conceptService;
    }

    final private ConceptExtractionService conceptService;

    public ConceptExtractionResponse extractConceptsForFile(File file) {
        return conceptService.extractConceptsFromFile(new TypedFile("multipart/form-data", file));
    }
}
