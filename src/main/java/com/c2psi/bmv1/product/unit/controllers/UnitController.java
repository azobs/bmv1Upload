package com.c2psi.bmv1.product.unit.controllers;

import com.c2psi.bmv1.api.UnitApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.product.unit.unit.service.UnitService;
import com.c2psi.bmv1.product.unit.unitconversion.service.UnitconversionService;
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
public class UnitController implements UnitApi {
    final UnitService unitService;
    final UnitconversionService unitconversionService;

    @Override
    public ResponseEntity<Boolean> deleteUnitById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = unitService.deleteUnitById(id);
        log.info("Unit deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Unit deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteUnitconversionById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = unitconversionService.deleteUnitconversionById(id);
        log.info("Unitconversion deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Unitconversion deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UnitDto> getUnitById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        UnitDto unitDtoFound = unitService.getUnitById(id);
        log.info("Unit found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Unit found successfully");
        map.put("data", unitDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UnitDto>> getUnitList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<UnitDto> unitDtoList = unitService.getListofUnit(filterRequest);
        log.info("Unit list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Unit list found successfully");
        map.put("data", unitDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofUnitDto> getUnitPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofUnitDto pageofUnitDto = unitService.getPageofUnit(filterRequest);
        log.info("Unit page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Unit page found successfully");
        map.put("data", pageofUnitDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UnitconversionDto> getUnitconversionById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        UnitconversionDto unitconversionDtoFound = unitconversionService.getUnitconversionById(id);
        log.info("Unitconversion found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Unitconversion found successfully");
        map.put("data", unitconversionDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UnitconversionDto>> getUnitconversionList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<UnitconversionDto> unitconversionDtoList = unitconversionService.getListofUnitconversion(filterRequest);
        log.info("Unitconversion list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Unitconversion list found successfully");
        map.put("data", unitconversionDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofUnitconversionDto> getUnitconversionPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofUnitconversionDto pageofUnitconvDto = unitconversionService.getPageofUnitconversion(filterRequest);
        log.info("Unitconversion page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Unitconversion page found successfully");
        map.put("data", pageofUnitconvDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UnitDto> saveUnit(UnitDto unitDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        UnitDto unitDtoSaved = unitService.saveUnit(unitDto);
        log.info("Unit saved successfully");

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Unit saved successfully");
        map.put("data", unitDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UnitconversionDto> saveUnitconversion(UnitconversionDto unitconversionDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        UnitconversionDto unitconvDtoSaved = unitconversionService.saveUnitconversion(unitconversionDto);
        log.info("Unitconversion saved successfully");

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Unitconversion saved successfully");
        map.put("data", unitconvDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UnitDto> updateUnit(UnitDto unitDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        UnitDto unitDtoUpdated = unitService.updateUnit(unitDto);
        log.info("Unit updated successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Unit updated successfully");
        map.put("data", unitDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UnitconversionDto> updateUnitconversion(UnitconversionDto unitconversionDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        UnitconversionDto unitconvDtoUpdated = unitconversionService.updateUnitconversion(unitconversionDto);
        log.info("Unitconversion updated successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Unitconversion updated successfully");
        map.put("data", unitconvDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
