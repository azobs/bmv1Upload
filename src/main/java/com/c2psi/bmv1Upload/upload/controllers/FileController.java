package com.c2psi.bmv1Upload.upload.controllers;

import com.c2psi.bmv1Upload.upload.services.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FileController {
    final UploadService uploadService;
    @PostMapping("/uploadimage")
    public void upload(@RequestPart(value = "multipartFile") MultipartFile multipartFile){
        log.info("Nous voici dans le controller");
        String nameofFile = uploadService.uploadPersonImage(multipartFile);
        log.info("The nameofFile uploaded is {}", nameofFile);
    }
}
