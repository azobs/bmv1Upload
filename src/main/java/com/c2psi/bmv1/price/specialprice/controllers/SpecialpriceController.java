package com.c2psi.bmv1.price.specialprice.controllers;

import com.c2psi.bmv1.api.SpApi;
import com.c2psi.bmv1.dto.SpecialpriceDto;
import com.c2psi.bmv1.price.specialprice.services.SpecialpriceService;
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
public class SpecialpriceController implements SpApi {
    final SpecialpriceService specialpriceService;

    @Override
    public ResponseEntity<Boolean> deleteSpecialpriceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = specialpriceService.deleteSpecialpriceById(id);
        log.info("Specialprice deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Specialprice deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SpecialpriceDto> getSpecialpriceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        SpecialpriceDto spDtoFound = specialpriceService.getSpecialpriceById(id);
        log.info("Specialprice found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Specialprice found successfully");
        map.put("data", spDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SpecialpriceDto> saveSpecialprice(SpecialpriceDto specialpriceDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        SpecialpriceDto spDtoSaved = specialpriceService.updateSpecialprice(specialpriceDto);
        log.info("Entity Specialprice updated successfully {}", spDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Specialprice created successfully");
        map.put("data", spDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SpecialpriceDto> updateSpecialprice(SpecialpriceDto specialpriceDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        SpecialpriceDto spDtoSaved = specialpriceService.updateSpecialprice(specialpriceDto);
        log.info("Entity Specialprice updated successfully {}", spDtoSaved);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Specialprice created successfully");
        map.put("data", spDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
