package com.learning.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	List<Map<String, String>> upload(MultipartFile file) throws IOException;

}
