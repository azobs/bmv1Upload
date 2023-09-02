package com.c2psi.bmv1.arrival.supplyinvoice.controllers;

import com.c2psi.bmv1.api.SupplyinvoiceApi;
import com.c2psi.bmv1.arrival.supplyinvoice.services.SupplyinvoiceService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.SupplyinvoiceDto;
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
public class SupplyinvoiceController implements SupplyinvoiceApi {
    final SupplyinvoiceService siService;

    @Override
    public ResponseEntity<Boolean> deleteSupplyinvoiceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = siService.deleteSupplyinvoiceById(id);
        log.info("Supplyinvoice deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Supplyinvoice deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SupplyinvoiceDto> getSupplyinvoiceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        SupplyinvoiceDto siDto = siService.getSupplyinvoiceById(id);
        log.info("Supplyinvoice found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Supplyinvoice found successfully");
        map.put("data", siDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SupplyinvoiceDto>> getSupplyinvoiceList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<SupplyinvoiceDto> siDtoList = siService.getListofSupplyinvoice(filterRequest);
        log.info("Supplyinvoice list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Supplyinvoice list found successfully");
        map.put("data", siDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofSupplyinvoiceDto> getSupplyinvoicePage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofSupplyinvoiceDto pageofSupplyinvoiceDto = siService.getPageofSupplyinvoice(filterRequest);
        log.info("Supplyinvoice page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Supplyinvoice page found successfully");
        map.put("data", pageofSupplyinvoiceDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SupplyinvoiceDto> saveSupplyinvoice(SupplyinvoiceDto siDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        SupplyinvoiceDto siDtoSaved = siService.saveSupplyinvoice(siDto);
        log.info("Entity Supplyinvoice saved successfully {}", siDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Supplyinvoice created successfully");
        map.put("data", siDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SupplyinvoiceDto> updateSupplyinvoice(SupplyinvoiceDto siDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        SupplyinvoiceDto siDtoUpdated = siService.updateSupplyinvoice(siDto);
        log.info("Entity Supplyinvoice updated successfully {}", siDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Supplyinvoice updated successfully");
        map.put("data", siDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
