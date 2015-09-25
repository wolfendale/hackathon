package com.accenture.hackathon;

import com.accenture.hackathon.batch.datasource.NewLineCsvBatch;
import com.accenture.hackathon.batch.sentiment.FileApiWithBatching;
import com.accenture.hackathon.batch.sentiment.SentimentApiBatch;
import com.accenture.hackathon.models.APIKey;
import com.accenture.hackathon.services.SentimentService;
import com.accenture.hackathon.services.iod.ConceptExtractionService;
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
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

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
    public SentimentAnalysisService sentimentAnalysisService(RestAdapter restAdapter) {
        return restAdapter.create(SentimentAnalysisService.class);
    }

    @Bean
    public ConceptExtractionService contentExtractionService(RestAdapter restAdapter) {
        return restAdapter.create(ConceptExtractionService.class);
    }

    @Bean
    public APIKey apiKey() {
        return new APIKey("26340f38-75ff-458d-8a3d-b66a76582263");
    }

    @Bean
    public Twitter twitter() throws TwitterException {

        final String consumerKey = "ZZotVk7oHjiHlfMUV0KVnJpNT";
        final String consumerSecret = "bkMYwz47rNcM7iWSS3AxzsY7RfH9Fh4z3a9GL1clct8edLSsYu";

        ConfigurationBuilder builder = new ConfigurationBuilder()
            .setApplicationOnlyAuthEnabled(true)
            .setOAuthConsumerKey(consumerKey)
            .setOAuthConsumerSecret(consumerSecret);

        final Twitter twitter = new TwitterFactory(builder.build()).getInstance();

        twitter.getOAuth2Token();

        return twitter;
    }

    @Bean
    public SentimentApiBatch sentimentApiBatch(SentimentService service) {
        return new FileApiWithBatching(service);
    }
}
