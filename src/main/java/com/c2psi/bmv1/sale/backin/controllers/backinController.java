package com.c2psi.bmv1.sale.backin.controllers;

import com.c2psi.bmv1.api.BackinApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.sale.backin.backin.services.BackinService;
import com.c2psi.bmv1.sale.backin.backindetails.services.BackindetailsService;
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
public class backinController implements BackinApi {
    final BackinService backinService;
    final BackindetailsService backindetailsService;

    @Override
    public ResponseEntity<Boolean> deleteBackinById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = backinService.deleteBackinById(id);
        log.info("Backin deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Backin deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteBackindetailsById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = backindetailsService.deleteBackindetailsById(id);
        log.info("Backindetails deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Backindetails deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BackinDto> getBackinById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        BackinDto backinDto = backinService.getBackinById(id);
        log.info("Backin found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Backin found successfully");
        map.put("data", backinDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BackinDto>> getBackinList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<BackinDto> backinDtoList = backinService.getListofBackin(filterRequest);
        log.info("Backin list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Backin list found successfully");
        map.put("data", backinDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofBackinDto> getBackinPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofBackinDto pageofBackinDto = backinService.getPageofBackin(filterRequest);
        log.info("Backin page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Backin page found successfully");
        map.put("data", pageofBackinDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BackindetailsDto> getBackindetailsById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        BackindetailsDto backindetailsDto = backindetailsService.getBackindetailsById(id);
        log.info("Backindetails found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Backindetails found successfully");
        map.put("data", backindetailsDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BackindetailsDto>> getBackindetailsList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<BackindetailsDto> backindetailsDtoList = backindetailsService.getListofBackindetails(filterRequest);
        log.info("Backindetails list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Backindetails list found successfully");
        map.put("data", backindetailsDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofBackindetailsDto> getBackindetailsPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofBackindetailsDto pageofBackindetailsDto = backindetailsService.getPageofBackindetails(filterRequest);
        log.info("Backindetails page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Backindetails page found successfully");
        map.put("data", pageofBackindetailsDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BackinDto> saveBackin(BackinDto backinDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        BackinDto backinDtoSaved = backinService.saveBackin(backinDto);
        log.info("Entity Backin saved successfully {}", backinDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Backin created successfully");
        map.put("data", backinDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BackindetailsDto> saveBackindetails(BackindetailsDto backindetailsDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        BackindetailsDto backindetailsDtoSaved = backindetailsService.saveBackindetails(backindetailsDto);
        log.info("Entity Backindetails saved successfully {}", backindetailsDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Backindetails created successfully");
        map.put("data", backindetailsDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BackinDto> updateBackin(BackinDto backinDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        BackinDto backinDtoUpdated = backinService.updateBackin(backinDto);
        log.info("Entity Backin updated successfully {}", backinDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Backin updated successfully");
        map.put("data", backinDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BackindetailsDto> updateBackindetails(BackindetailsDto backindetailsDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        BackindetailsDto backindetailsDtoUpdated = backindetailsService.updateBackindetails(backindetailsDto);
        log.info("Entity Backindetails updated successfully {}", backindetailsDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Backindetails updated successfully");
        map.put("data", backindetailsDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
