package com.accenture.hackathon.models.iod;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = Concept.Builder.class)
public class Concept {

    private final String concept;
    private final int occurences;

    public static class Builder {

        private String concept;
        private int occurences;

        public Concept build() {
            return new Concept(concept, occurences);
        }
    }
}