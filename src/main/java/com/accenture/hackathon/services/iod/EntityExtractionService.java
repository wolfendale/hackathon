package com.accenture.hackathon.services.iod;

import com.accenture.hackathon.models.iod.EntityExtractionEntity;
import com.accenture.hackathon.models.iod.EntityExtractionResponse;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedOutput;

import java.util.List;

public interface EntityExtractionService {

    String URL = "api/sync/extractentities/v1";

    @POST(URL)
    @Multipart
    EntityExtractionResponse extractEntitiesFromFile(@Part("file") TypedOutput file, @Query("entity_type") List<EntityExtractionEntity> type);
}
