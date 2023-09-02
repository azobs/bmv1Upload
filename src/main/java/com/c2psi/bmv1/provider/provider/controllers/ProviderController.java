package com.c2psi.bmv1.provider.provider.controllers;

import com.c2psi.bmv1.api.ProviderApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.ProviderDto;
import com.c2psi.bmv1.provider.provider.service.ProviderService;
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
public class ProviderController implements ProviderApi {
    final ProviderService providerService;

    @Override
    public ResponseEntity<Boolean> deleteProviderById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = providerService.deleteProviderById(id);
        log.info("Provider deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Provider deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProviderDto> getProviderById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        ProviderDto providerDto = providerService.getProviderById(id);
        log.info("Provider found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Provider found successfully");
        map.put("data", providerDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProviderDto>> getProviderList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<ProviderDto> providerDtoList = providerService.getListofProvider(filterRequest);
        log.info("Provider list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Provider list found successfully");
        map.put("data", providerDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofProviderDto> getProviderPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofProviderDto pageofProviderDto = providerService.getPageofProvider(filterRequest);
        log.info("Provider page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Provider page found successfully");
        map.put("data", pageofProviderDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProviderDto> saveProvider(ProviderDto providerDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ProviderDto providerDtoSaved = providerService.saveProvider(providerDto);
        log.info("Entity Provider saved successfully {}", providerDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Provider created successfully");
        map.put("data", providerDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProviderDto> updateProvider(ProviderDto providerDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ProviderDto providerDtoUpdated = providerService.updateProvider(providerDto);
        log.info("Entity Provider updated successfully {}", providerDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Provider updated successfully");
        map.put("data", providerDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
