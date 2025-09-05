package com.dsm.pdf_service.service.storage;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3FileStorageService implements FileStorageService {

    private final S3Client s3Client;
    private final String bucketName = "template-pdf-storage";

    public S3FileStorageService() {
        this.s3Client = S3Client.builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(DefaultCredentialsProvider.builder().build())
                .build();
    }
    
    @Override
    public String uploadFile(InputStream inputStream, String key) throws Exception {
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType("application/pdf")
                .build();

        s3Client.putObject(putRequest, RequestBody.fromInputStream(inputStream, inputStream.available()));

        return String.format("https://%s.s3.%s.amazonaws.com/%s",
                bucketName, s3Client.serviceClientConfiguration().region().id(), key);
    }
}
