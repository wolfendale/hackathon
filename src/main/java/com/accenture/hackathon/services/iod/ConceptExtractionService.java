package com.accenture.hackathon.services.iod;

import com.accenture.hackathon.models.iod.ConceptExtractionResponse;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedOutput;

public interface ConceptExtractionService {

    String URL = "/api/sync/extractconcepts/v1";

    @POST(URL)
    @Multipart
    ConceptExtractionResponse extractConceptsFromFile(@Part("file") TypedOutput file);
}
