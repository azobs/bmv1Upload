package com.c2psi.bmv1.upload.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadPersonImage(Long id, MultipartFile fileToUpload);
    String uploadArticleImage(Long id, MultipartFile fileToUpload);
    String uploadInvoiceImage(Long id, MultipartFile fileToUpload);
    String uploadLogoImage(Long id, MultipartFile fileToUpload);
    String uploadPfImage(Long id, MultipartFile fileToUpload);
    Resource loadPersonImage(String filename);
    Resource loadArticleImage(String filename);
    Resource loadInvoiceImage(String filename);
    Resource loadLogoImage(String filename);
    Resource loadPfImage(String filename);
    Resource loadImage(String filename);
    Boolean renameFile(String folderName, String oldName, String newName);
    void deleteAll();
    void deleteAllPerson();
    void deleteAllArticle();
    void deleteAllInvoice();
    void deleteAllLogo();
}
