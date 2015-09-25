package com.accenture.hackathon.models.iod;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

// TODO: This will never happen...
@JsonDeserialize(builder = EEEntity.Builder.class)
public class EEEntity {

    public EEEntity(
            String normalized_text,
            String original_text,
            EntityExtractionEntity type
    ) {
        this.normalized_text = normalized_text;
        this.original_text = original_text;
        this.type = type;
        // ...
    }

    final private String normalized_text;
    final private String original_text;
    final private EntityExtractionEntity type;
    // ...

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private String normalized_text;
        private String original_text;
        // ...

        public Builder setNormalized_text(String normalized_text) {
            this.normalized_text = normalized_text;
            return this;
        }

        public Builder setOriginal_text(String original_text) {
            this.original_text = original_text;
            return this;
        }
    }
}
