package com.accenture.hackathon.services;

import com.accenture.hackathon.services.iod.ConceptExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptService {

    @Autowired
    public ConceptService(ConceptExtractionService conceptService) {
        this.conceptService = conceptService;
    }

    final private ConceptExtractionService conceptService;
}
