package com.c2psi.bmv1.account.operation.controllers;

import com.c2psi.bmv1.account.operation.services.OperationService;
import com.c2psi.bmv1.api.OperationApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.OperationDto;
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
public class OperationController implements OperationApi {
    final OperationService operationService;

    @Override
    public ResponseEntity<Boolean> deleteOperationById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = operationService.deleteOperationById(id);
        log.info("Operation deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Operation deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OperationDto> getOperationById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        OperationDto operationDto = operationService.getOperationById(id);
        log.info("Operation found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Operation found successfully");
        map.put("data", operationDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OperationDto>> getOperationList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<OperationDto> operationDtoList = operationService.getListofOperation(filterRequest);
        log.info("Operation list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Operation list found successfully");
        map.put("data", operationDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofOperationDto> getOperationPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofOperationDto pageofOperationDto = operationService.getPageofOperation(filterRequest);
        log.info("Operation page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Operation page found successfully");
        map.put("data", pageofOperationDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OperationDto> saveOperation(OperationDto operationDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        OperationDto operationDtoSaved = operationService.saveOperation(operationDto);
        log.info("Entity Operation saved successfully {}", operationDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Operation created successfully");
        map.put("data", operationDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OperationDto> updateOperation(OperationDto operationDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        OperationDto operationDtoUpdated = operationService.updateOperation(operationDto);
        log.info("Entity Operation updated successfully {}", operationDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Operation updated successfully");
        map.put("data", operationDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
