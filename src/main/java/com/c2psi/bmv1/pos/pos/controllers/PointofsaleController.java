package com.c2psi.bmv1.pos.pos.controllers;

import com.c2psi.bmv1.api.PosApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
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
public class PointofsaleController implements PosApi {
    final PointofsaleService posService;


    @Override
    public ResponseEntity<Boolean> deletePosById(Long id) {

        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = posService.deletePointofsaleById(id);
        log.info("Pointofsale deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Pointofsale deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PointofsaleDto> getPosById(Long id) {

        Map<String, Object> map = new LinkedHashMap<>();
        PointofsaleDto posDto = posService.getPointofsaleById(id);
        log.info("Pointofsale found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Pointofsale found successfully");
        map.put("data", posDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PointofsaleDto>> getPosList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<PointofsaleDto> posDtoList = posService.getListofPointofsale(filterRequest);
        log.info("Pointofsale list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Pointofsale list found successfully");
        map.put("data", posDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofPointofsaleDto> getPosPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofPointofsaleDto pageofPointofsaleDto = posService.getPageofPointofsale(filterRequest);
        log.info("Pointofsale page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Pointofsale page found successfully");
        map.put("data", pageofPointofsaleDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PointofsaleDto> savePos(PointofsaleDto pointofsaleDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        PointofsaleDto posDtoSaved = posService.savePointofsale(pointofsaleDto);
        log.info("Entity Pointofsale saved successfully {}", posDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Pointofsale created successfully");
        map.put("data", posDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PointofsaleDto> updatePos(PointofsaleDto pointofsaleDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        PointofsaleDto posDtoUpdated = posService.updatePointofsale(pointofsaleDto);
        log.info("Entity Pointofsale updated successfully {}", posDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Pointofsale updated successfully");
        map.put("data", posDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
