package com.accenture.hackathon.models.iod;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonDeserialize(builder = ConceptExtractionResponse.Builder.class)
public class ConceptExtractionResponse {

    private final List<Concept> concepts;

    @JsonPOJOBuilder(withPrefix = "set")
    @Setter
    @Accessors(chain = true)
    public static class Builder {

        private List<Concept> concepts;

        public ConceptExtractionResponse build() {
            return new ConceptExtractionResponse(concepts);
        }
    }
}
