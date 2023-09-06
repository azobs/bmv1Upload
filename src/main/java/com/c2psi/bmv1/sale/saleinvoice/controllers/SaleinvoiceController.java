package com.c2psi.bmv1.sale.saleinvoice.controllers;

import com.c2psi.bmv1.api.SaleinvoiceApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.sale.saleinvoice.services.SaleinvoiceService;
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
public class SaleinvoiceController implements SaleinvoiceApi {
    final SaleinvoiceService siService;

    @Override
    public ResponseEntity<Boolean> deleteSaleinvoiceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = siService.deleteSaleinvoiceById(id);
        log.info("Saleinvoice deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Saleinvoice deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SaleinvoiceDto> getSaleinvoiceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        SaleinvoiceDto siDto = siService.getSaleinvoiceById(id);
        log.info("Saleinvoice found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Saleinvoice found successfully");
        map.put("data", siDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SaleinvoiceDto>> getSaleinvoiceList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<SaleinvoiceDto> siDtoList = siService.getListofSaleinvoice(filterRequest);
        log.info("Saleinvoice list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Saleinvoice list found successfully");
        map.put("data", siDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofSaleinvoiceDto> getSaleinvoicePage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofSaleinvoiceDto pageofSaleinvoiceDto = siService.getPageofSaleinvoice(filterRequest);
        log.info("Saleinvoice page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Saleinvoice page found successfully");
        map.put("data", pageofSaleinvoiceDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SaleinvoiceDto> saveSaleinvoice(SaleinvoiceDto siDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        SaleinvoiceDto siDtoSaved = siService.saveSaleinvoice(siDto);
        log.info("Entity Saleinvoice saved successfully {}", siDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Saleinvoice created successfully");
        map.put("data", siDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SaleinvoiceDto> updateSaleinvoice(SaleinvoiceDto siDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        SaleinvoiceDto siDtoUpdated = siService.updateSaleinvoice(siDto);
        log.info("Entity Saleinvoice updated successfully {}", siDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Saleinvoice updated successfully");
        map.put("data", siDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
