package com.c2psi.bmv1Upload.upload.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadPersonImage(MultipartFile fileToUpload);
    Resource loadImage(String filename);
    void deleteAllPerson();
}
