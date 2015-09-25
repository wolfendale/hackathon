package com.accenture.hackathon.models.iod;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Concept.Builder.class)
public class Concept {

    public Concept(String concept, int occurences) {
        this.concept = concept;
        this.occurences = occurences;
    }

    private final String concept;
    private final int occurences;

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private String concept;
        private int occurences;

        public Concept build() {
            return new Concept(concept, occurences);
        }
    }
}