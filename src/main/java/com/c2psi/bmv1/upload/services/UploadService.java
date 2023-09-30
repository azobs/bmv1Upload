package com.c2psi.bmv1.upload.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadPersonImage(MultipartFile fileToUpload);
    String uploadArticleImage(MultipartFile fileToUpload);
    String uploadInvoiceImage(MultipartFile fileToUpload);
    String uploadLogoImage(MultipartFile fileToUpload);
    String uploadPfImage(MultipartFile fileToUpload);
    Resource loadImage(String filename);
    void deleteAllPerson();
    void deleteAllArticle();
    void deleteAllInvoice();
    void deleteAllLogo();
}
