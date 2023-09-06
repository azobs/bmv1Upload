package com.c2psi.bmv1.sale.sale.controllers;

import com.c2psi.bmv1.api.SaleApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.SaleDto;
import com.c2psi.bmv1.sale.sale.services.SaleService;
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
public class SaleController implements SaleApi {
    final SaleService saleService;

    @Override
    public ResponseEntity<Boolean> deleteSaleById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = saleService.deleteSaleById(id);
        log.info("Sale deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Sale deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SaleDto> getSaleById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        SaleDto saleDto = saleService.getSaleById(id);
        log.info("Sale found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Sale found successfully");
        map.put("data", saleDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SaleDto>> getSaleList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<SaleDto> saleDtoList = saleService.getListofSale(filterRequest);
        log.info("Sale list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Sale list found successfully");
        map.put("data", saleDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofSaleDto> getSalePage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofSaleDto pageofSaleDto = saleService.getPageofSale(filterRequest);
        log.info("Sale page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Sale page found successfully");
        map.put("data", pageofSaleDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SaleDto> saveSale(SaleDto saleDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        SaleDto saleDtoSaved = saleService.saveSale(saleDto);
        log.info("Entity Sale saved successfully {}", saleDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Sale created successfully");
        map.put("data", saleDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SaleDto> updateSale(SaleDto saleDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        SaleDto saleDtoUpdated = saleService.updateSale(saleDto);
        log.info("Entity Sale updated successfully {}", saleDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Sale updated successfully");
        map.put("data", saleDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
