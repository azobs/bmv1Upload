package com.c2psi.bmv1.price.baseprice.controllers;

import com.c2psi.bmv1.api.BpApi;
import com.c2psi.bmv1.dto.BasepriceDto;
import com.c2psi.bmv1.price.baseprice.services.BasepriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BasepriceController implements BpApi {
    final BasepriceService basepriceService;

    @Override
    public ResponseEntity<Boolean> deleteBasepriceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = basepriceService.deleteBasepriceById(id);
        log.info("Baseprice deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Baseprice deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BasepriceDto> getBasepriceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        BasepriceDto bpDtoFound = basepriceService.getBasepriceById(id);
        log.info("Baseprice found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Baseprice found successfully");
        map.put("data", bpDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BasepriceDto> saveBaseprice(BasepriceDto basepriceDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        BasepriceDto bpDtoSaved = basepriceService.updateBaseprice(basepriceDto);
        log.info("Entity Baseprice updated successfully {}", bpDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Baseprice created successfully");
        map.put("data", bpDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BasepriceDto> updateBaseprice(BasepriceDto basepriceDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        BasepriceDto bpDtoSaved = basepriceService.updateBaseprice(basepriceDto);
        log.info("Entity Baseprice updated successfully {}", bpDtoSaved);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Baseprice created successfully");
        map.put("data", bpDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
