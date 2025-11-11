package com.example.imageuploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;


@SpringBootApplication
public class ImageUploaderApplication {
    public static void main(String[] args) {
        // necessary to initialize the app with the right timezone for postgres
        TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
        SpringApplication.run(ImageUploaderApplication.class, args);
    }
}
