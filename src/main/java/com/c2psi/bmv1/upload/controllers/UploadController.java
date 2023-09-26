package com.c2psi.bmv1.upload.controllers;

import com.c2psi.bmv1.api.UploadApi;
import com.c2psi.bmv1.upload.services.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
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
    public ResponseEntity<Resource> uploadPicture(Long id, MultipartFile fileName, String imageOf) {
        Map<String, Object> map = new LinkedHashMap<>();
        String nameofFile="";
        Resource image = null;
        if(imageOf.equalsIgnoreCase("ARTICLE")){
            nameofFile = uploadService.uploadArticleImage(id, fileName);
            image = uploadService.loadArticleImage(String.valueOf(id));
        }
        if(imageOf.equalsIgnoreCase("LOGO")){
            nameofFile = uploadService.uploadLogoImage(id, fileName);
            image = uploadService.loadLogoImage(String.valueOf(id));
        }
        if(imageOf.equalsIgnoreCase("PERSON")){
            nameofFile = uploadService.uploadPersonImage(id, fileName);
            image = uploadService.loadPersonImage(String.valueOf(id));
        }
        if(imageOf.equalsIgnoreCase("PRODUCTFORMATED")){
            nameofFile = uploadService.uploadPfImage(id, fileName);
            image = uploadService.loadPfImage(String.valueOf(id));
        }
        if(imageOf.equalsIgnoreCase("INVOICE")){
            nameofFile = uploadService.uploadInvoiceImage(id, fileName);
            image = uploadService.loadInvoiceImage(String.valueOf(id));
        }
        log.info("Image uploaded successfully and the image file name was {} ", nameofFile);

        /***********************
         * Now we must load the uploaded image and return back
         */

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Image uploaded successfully");
        map.put("data", image);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
