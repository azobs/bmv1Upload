package com.c2psi.bmv1.upload.controllers;

import com.c2psi.bmv1.api.UploadApi;
import com.c2psi.bmv1.upload.services.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UploadController implements UploadApi {
    final UploadService uploadService;

    @Override
    public ResponseEntity<String> uploadPictureofPerson(MultipartFile fileName) {
        Map<String, Object> map = new LinkedHashMap<>();
        String uploadedFilename = uploadService.uploadPersonImage(fileName);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Image of Person uploaded successfully");
        map.put("data", uploadedFilename);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

}
