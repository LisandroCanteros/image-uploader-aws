package com.example.imageuploader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Configuration
public class AwsConfig {
    @Value("${aws.region:us-east-1}")
    private String awsRegion;

    @Value("${aws.endpoint:}")
    private String awsEndpoint;

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return DefaultCredentialsProvider.builder().build();
    }

    @Bean
    public S3Client s3Client(AwsCredentialsProvider creds) {
        return S3Client.builder()
                .credentialsProvider(creds)
                .region(Region.of(awsRegion))
                .build();
    }

    @Bean
    public SqsClient sqsClient(AwsCredentialsProvider creds) {
        return SqsClient.builder()
                .credentialsProvider(creds)
                .region(Region.of(awsRegion))
                .build();
    }
}