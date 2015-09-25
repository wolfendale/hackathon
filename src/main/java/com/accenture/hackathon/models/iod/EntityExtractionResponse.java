package com.accenture.hackathon.models.iod;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = EntityExtractionResponse.Builder.class)
public class EntityExtractionResponse {

    public EntityExtractionResponse(List<EEEntity> entities) {
        this.entities = entities;
    }

    final private List<EEEntity> entities;

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private List<EEEntity> entities;

        public Builder setEntities(List<EEEntity> entities) {
            this.entities = entities;
            return this;
        }

        public EntityExtractionResponse build() {
            return new EntityExtractionResponse(entities);
        }
    }
}
