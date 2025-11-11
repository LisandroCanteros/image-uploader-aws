package com.example.imageuploader.controller;

import com.example.imageuploader.model.Image;
import com.example.imageuploader.repository.ImageRepository;
import com.example.imageuploader.service.S3Service;
import com.example.imageuploader.service.SqsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final S3Service s3;
    private final ImageRepository imageRepository;
    private final SqsService sqs;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ImageController(S3Service s3, ImageRepository imageRepository, SqsService sqs) {
        this.s3 = s3;
        this.imageRepository = imageRepository;
        this.sqs = sqs;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "uploadedBy", required = false) String uploadedBy)
        throws Exception {
        String key = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String url = s3.upload(key, file.getBytes(), file.getContentType());
        Image img = new Image();
        img.setFilename(file.getOriginalFilename());
        img.setS3Url(url);
        img.setUploadedBy(uploadedBy == null ? "anonymous" : uploadedBy);
        imageRepository.save(img);

        // send SQS event
        String msg = objectMapper.writeValueAsString(Map.of(
                "imageId", img.getId(),
                "imageUrl", img.getS3Url(),
                "uploadedBy", img.getUploadedBy()
        ));
        sqs.send(msg);

        return ResponseEntity.ok(Map.of("id", img.getId(), "url", url));
    }
}
