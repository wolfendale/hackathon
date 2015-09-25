package com.accenture.hackathon;

import com.accenture.hackathon.batch.*;
import com.accenture.hackathon.models.APIKey;
import com.accenture.hackathon.services.SentimentService;
import com.accenture.hackathon.services.iod.CallbackSentimentAnalysisService;
import com.hp.autonomy.iod.client.api.textanalysis.SentimentAnalysisService;
import com.hp.autonomy.iod.client.converter.IodConverter;
import com.hp.autonomy.iod.client.error.IodErrorHandler;
import com.hp.autonomy.iod.client.util.ApiKeyRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@EnableAsync
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
    public CallbackSentimentAnalysisService callbackSentimentAnalysisService(RestAdapter restAdapter) {
         return restAdapter.create(CallbackSentimentAnalysisService.class);
    }

    @Bean
    public SentimentAnalysisService sentimentAnalysisService(RestAdapter restAdapter) {
        return restAdapter.create(SentimentAnalysisService.class);
    }

    @Bean
    public FeedbackBatch feedbackBatch() {
        return new TestFeedbackBatch();
    }

    @Bean
    public APIKey apiKey() {
        return new APIKey("26340f38-75ff-458d-8a3d-b66a76582263");
    }

    @Bean
    public Twitter twitter() {
        final Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(
                "ZZotVk7oHjiHlfMUV0KVnJpNT",
                "bkMYwz47rNcM7iWSS3AxzsY7RfH9Fh4z3a9GL1clct8edLSsYu"
        );
        return twitter;
    }

    @Bean
    public SentimentApiBatch sentimentApiBatch(SentimentService service) {
        return new FileApiWithBatching(service);
    }
}
