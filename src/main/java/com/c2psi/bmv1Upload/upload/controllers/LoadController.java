package com.c2psi.bmv1Upload.upload.controllers;

import com.c2psi.bmv1.api.LoadresourceApi;
import com.c2psi.bmv1Upload.upload.services.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoadController implements LoadresourceApi {
    final UploadService uploadService;

    @Override
    public ResponseEntity<Resource> loadResourceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Resource image = uploadService.loadImage(String.valueOf(id));

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Image uploaded successfully");
        map.put("data", image);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
