package com.handshake.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongDownloadService {
    
    private final S3Client s3Client;
    
    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;
    
    public InputStream downloadSong(String fileName) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
            
            log.info("Downloading song: {} from bucket: {}", fileName, bucketName);
            
            return s3Client.getObject(getObjectRequest);
            
        } catch (S3Exception e) {
            log.error("Error downloading song from S3: {}", e.getMessage());
            throw new RuntimeException("Failed to download song: " + fileName, e);
        }
    }
    
    public boolean songExists(String fileName) {
        try {
            s3Client.headObject(builder -> builder.bucket(bucketName).key(fileName));
            return true;
        } catch (S3Exception e) {
            if (e.statusCode() == 404) {
                return false;
            }
            log.error("Error checking if song exists: {}", e.getMessage());
            throw new RuntimeException("Failed to check if song exists: " + fileName, e);
        }
    }
}