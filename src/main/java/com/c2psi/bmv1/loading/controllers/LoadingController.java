package com.c2psi.bmv1.loading.controllers;

import com.c2psi.bmv1.api.LoadingApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.loading.loading.services.LoadingService;
import com.c2psi.bmv1.loading.loadingdetails.services.LoadingdetailsService;
import com.c2psi.bmv1.loading.packagingdetails.services.PackagingdetailsService;
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
public class LoadingController implements LoadingApi {
    final LoadingService loadingService;
    final LoadingdetailsService loadingdetailsService;
    final PackagingdetailsService packagingdetailsService;

    @Override
    public ResponseEntity<LoadingDto> closeLoadingById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean closed = loadingService.closeLoading(id);
        log.info("Loading closed successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loading closed successfully");
        map.put("data", closed);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteLoadingById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = loadingService.deleteLoadingById(id);
        log.info("Loading deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loading deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteLoadingdetailsById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = loadingdetailsService.deleteLoadingdetailsById(id);
        log.info("Loading details deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loading details deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deletePackagingdetailsById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = packagingdetailsService.deletePackagingdetailsById(id);
        log.info("Packaging details deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Packaging details deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoadingDto> getLoadingById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        LoadingDto loadingDto = loadingService.getLoadingById(id);
        log.info("Loading found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loading found successfully");
        map.put("data", loadingDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LoadingDto>> getLoadingList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<LoadingDto> loadingDtoList = loadingService.getListofLoading(filterRequest);
        log.info("Loading list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loading list found successfully");
        map.put("data", loadingDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofLoadingDto> getLoadingPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofLoadingDto pageofLoadingDto = loadingService.getPageofLoading(filterRequest);
        log.info("Loading page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loading page found successfully");
        map.put("data", pageofLoadingDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoadingdetailsDto> getLoadingdetailsById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        LoadingdetailsDto loadingdetailsDto = loadingdetailsService.getLoadingdetailsById(id);
        log.info("Loading details found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loading details found successfully");
        map.put("data", loadingdetailsDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LoadingdetailsDto>> getLoadingdetailsList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<LoadingdetailsDto> loadingdetailsDtoList = loadingdetailsService.getListofLoadingdetails(filterRequest);
        log.info("Loading details list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loading details list found successfully");
        map.put("data", loadingdetailsDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofLoadingdetailsDto> getLoadingdetailsPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofLoadingdetailsDto pageofLoadingdetailsDto = loadingdetailsService.getPageofLoadingdetails(filterRequest);
        log.info("Loadingdetails page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loadingdetails page found successfully");
        map.put("data", pageofLoadingdetailsDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PackagingdetailsDto> getPackagingdetailsById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        PackagingdetailsDto packagingdetailsDto = packagingdetailsService.getPackagingdetailsById(id);
        log.info("Packaging details found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Packaging details found successfully");
        map.put("data", packagingdetailsDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PackagingdetailsDto>> getPackagingdetailsList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<PackagingdetailsDto> packagingdetailsDtoList = packagingdetailsService.getListofPackagingdetails(filterRequest);
        log.info("Packaging details list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Packaging details list found successfully");
        map.put("data", packagingdetailsDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofPackagingdetailsDto> getPackagingdetailsPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofPackagingdetailsDto pageofPackagingdetailsDto = packagingdetailsService.getPageofPackagingdetails(filterRequest);
        log.info("Packaging details page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Packagingdetails page found successfully");
        map.put("data", pageofPackagingdetailsDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoadingDto> openLoadingById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean opened = loadingService.openLoading(id);
        log.info("Loading opened successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loading opened successfully");
        map.put("data", opened);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoadingDto> saveLoading(LoadingDto loadingDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        LoadingDto loadingDtoSaved = loadingService.saveLoading(loadingDto);
        log.info("Entity Loading saved successfully {}", loadingDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Loading created successfully");
        map.put("data", loadingDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LoadingdetailsDto> saveLoadingdetails(LoadingdetailsDto loadingdetailsDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        LoadingdetailsDto loadingdetailsDtoSaved = loadingdetailsService.saveLoadingdetails(loadingdetailsDto);
        log.info("Entity Loadingdetails saved successfully {}", loadingdetailsDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Loadingdetails created successfully");
        map.put("data", loadingdetailsDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PackagingdetailsDto> savePackagingdetails(PackagingdetailsDto packagingdetailsDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        PackagingdetailsDto packagingdetailsDtoSaved = packagingdetailsService.savePackagingdetails(packagingdetailsDto);
        log.info("Entity Packagingdetails saved successfully {}", packagingdetailsDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Packagingdetails created successfully");
        map.put("data", packagingdetailsDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LoadingDto> updateLoading(LoadingDto loadingDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        LoadingDto loadingDtoUpdated = loadingService.updateLoading(loadingDto);
        log.info("Entity Loading updated successfully {}", loadingDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loading updated successfully");
        map.put("data", loadingDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LoadingdetailsDto> updateLoadingdetails(LoadingdetailsDto loadingdetailsDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        LoadingdetailsDto loadingdetailsDtoUpdated = loadingdetailsService.updateLoadingdetails(loadingdetailsDto);
        log.info("Entity Loadingdetails updated successfully {}", loadingdetailsDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loadingdetails updated successfully");
        map.put("data", loadingdetailsDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PackagingdetailsDto> updatePackagingdetails(PackagingdetailsDto packagingdetailsDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        PackagingdetailsDto packagingdetailsDtoUpdated = packagingdetailsService.updatePackagingdetails(packagingdetailsDto);
        log.info("Entity packagingdetails updated successfully {}", packagingdetailsDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Loadingdetails updated successfully");
        map.put("data", packagingdetailsDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
