package com.example.imageuploader.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class SqsConsumerService {
    @SqsListener("image-upload-events")
    public void handleSqsMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
