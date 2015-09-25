package com.accenture.hackathon.models.iod;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = ConceptExtractionResponse.Builder.class)
public class ConceptExtractionResponse {

    public ConceptExtractionResponse(List<Concept> concepts) {
        this.concepts = concepts;
    }

    private final List<Concept> concepts;

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private List<Concept> concepts;

        public ConceptExtractionResponse build() {
            return new ConceptExtractionResponse(concepts);
        }
    }
}
