package com.c2psi.bmv1.packaging.packaging.controllers;

import com.c2psi.bmv1.api.PackagingApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.PackagingDto;
import com.c2psi.bmv1.packaging.packaging.services.PackagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PackagingController implements PackagingApi {
    final PackagingService packagingService;

    @Override
    public ResponseEntity<Boolean> deletePackagingById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = packagingService.deletePackagingById(id);
        log.info("Packaging deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Packaging deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PackagingDto> getPackagingById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        PackagingDto packagingDto = packagingService.getPackagingById(id);
        log.info("Packaging found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Packaging found successfully");
        map.put("data", packagingDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PackagingDto>> getPackagingList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<PackagingDto> packagingDtoList = packagingService.getListofPackaging(filterRequest);
        log.info("Packaging list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Packaging list found successfully");
        map.put("data", packagingDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofPackagingDto> getPackagingPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofPackagingDto pageofPackagingDto = packagingService.getPageofPackaging(filterRequest);
        log.info("Packaging page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Packaging page found successfully");
        map.put("data", pageofPackagingDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PackagingDto> savePackaging(PackagingDto packagingDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        PackagingDto packagingDtoSaved = packagingService.savePackaging(packagingDto);
        log.info("Entity Packaging saved successfully {}", packagingDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Packaging created successfully");
        map.put("data", packagingDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PackagingDto> updatePackaging(PackagingDto packagingDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        PackagingDto packagingDtoUpdated = packagingService.updatePackaging(packagingDto);
        log.info("Entity Packaging updated successfully {}", packagingDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Packaging updated successfully");
        map.put("data", packagingDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
