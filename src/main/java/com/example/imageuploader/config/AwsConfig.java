package com.example.imageuploader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import java.net.URI;



@Configuration
public class AwsConfig {
    @Value("${aws.region:us-east-1}")
    private String awsRegion;
    @Value("${aws.s3.endpoint:}")
    private String awsEndpoint;

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return DefaultCredentialsProvider.builder().build();
    }

    @Bean
    public S3Client s3Client(AwsCredentialsProvider creds) {

        // 1. The S3Client.Builder class is now recognized because the necessary
        //    imports for S3Client and Region were already present.
        S3Client.Builder b = S3Client.builder()
                .credentialsProvider(creds)
                // We use the injected region value
                .region(Region.of(awsRegion));

        // 2. URI.create is now recognized due to the added 'import java.net.URI;'
        // We use the injected endpoint value
        if (!awsEndpoint.isEmpty()) {
            b = b.endpointOverride(URI.create(awsEndpoint));
        }

        return b.build();
    }

}
