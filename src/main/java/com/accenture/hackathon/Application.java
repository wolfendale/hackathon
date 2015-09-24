package com.accenture.hackathon;

import com.accenture.hackathon.models.APIKey;
import com.accenture.hackathon.services.iod.SentimentAnalysisService;
import com.hp.autonomy.iod.client.converter.IodConverter;
import com.hp.autonomy.iod.client.error.IodErrorHandler;
import com.hp.autonomy.iod.client.util.ApiKeyRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestAdapter restAdapter(APIKey apiKey) {
        return new RestAdapter.Builder()
        .setEndpoint("https://api.idolondemand.com/1")
        .setConverter(new IodConverter(new JacksonConverter()))
        .setRequestInterceptor(new ApiKeyRequestInterceptor(apiKey.getKey()))
        .setErrorHandler(new IodErrorHandler())
        .build();
    }

    @Bean
    public SentimentAnalysisService sentimentAnalysisService(RestAdapter restAdapter) {
         return restAdapter.create(SentimentAnalysisService.class);
    }

    @Bean
    public APIKey apiKey() {
        return new APIKey("26340f38-75ff-458d-8a3d-b66a76582263");
    }
}
