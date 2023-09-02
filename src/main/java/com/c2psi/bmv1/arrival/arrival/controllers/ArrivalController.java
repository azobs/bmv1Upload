package com.c2psi.bmv1.arrival.arrival.controllers;

import com.c2psi.bmv1.api.ArrivalApi;
import com.c2psi.bmv1.arrival.arrival.services.ArrivalService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.ArrivalDto;
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
public class ArrivalController implements ArrivalApi {
    final ArrivalService arrivalService;

    @Override
    public ResponseEntity<Boolean> deleteArrivalById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = arrivalService.deleteArrivalById(id);
        log.info("Arrival deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Arrival deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArrivalDto> getArrivalById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        ArrivalDto arrivalDto = arrivalService.getArrivalById(id);
        log.info("Arrival found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Arrival found successfully");
        map.put("data", arrivalDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ArrivalDto>> getArrivalList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<ArrivalDto> arrivalDtoList = arrivalService.getListofArrival(filterRequest);
        log.info("Arrival list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Arrival list found successfully");
        map.put("data", arrivalDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofArrivalDto> getArrivalPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofArrivalDto pageofArrivalDto = arrivalService.getPageofArrival(filterRequest);
        log.info("Arrival page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Arrival page found successfully");
        map.put("data", pageofArrivalDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArrivalDto> saveArrival(ArrivalDto arrivalDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ArrivalDto arrivalDtoSaved = arrivalService.saveArrival(arrivalDto);
        log.info("Entity Arrival saved successfully {}", arrivalDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Arrival created successfully");
        map.put("data", arrivalDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ArrivalDto> updateArrival(ArrivalDto arrivalDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ArrivalDto arrivalDtoUpdated = arrivalService.updateArrival(arrivalDto);
        log.info("Entity Arrival updated successfully {}", arrivalDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Arrival updated successfully");
        map.put("data", arrivalDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
