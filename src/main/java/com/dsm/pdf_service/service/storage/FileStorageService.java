package com.dsm.pdf_service.service.storage;

import java.io.InputStream;

import org.springframework.stereotype.Service;

@Service
public interface FileStorageService {
    String uploadFile(InputStream inputStream, String key) throws Exception;
}
