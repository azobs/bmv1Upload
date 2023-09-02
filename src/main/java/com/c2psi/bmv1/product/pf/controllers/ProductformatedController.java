package com.c2psi.bmv1.product.pf.controllers;

import com.c2psi.bmv1.api.PfApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.product.pf.service.ProductformatedService;
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
public class ProductformatedController implements PfApi {
    final ProductformatedService pfService;

    @Override
    public ResponseEntity<Boolean> deleteProductformatedById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = pfService.deleteProductformatedById(id);
        log.info("Productformated deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Productformated deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductformatedDto> getProductformatedById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        ProductformatedDto pfDtoFound = pfService.getProductformatedById(id);
        log.info("Productformated found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Productformated found successfully");
        map.put("data", pfDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductformatedDto>> getProductformatedList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<ProductformatedDto> pfDtoList = pfService.getListofProductformated(filterRequest);
        log.info("Productformated list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Productformated list found successfully");
        map.put("data", pfDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofProductformatedDto> getProductformatedPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofProductformatedDto pageofpfDtoFound = pfService.getPageofProductformated(filterRequest);
        log.info("Productformated page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Productformated page found successfully");
        map.put("data", pageofpfDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductformatedDto> saveProductformated(ProductformatedDto productformatedDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ProductformatedDto pfDtoSaved = pfService.saveProductformated(productformatedDto);
        log.info("Productformated saved successfully");

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Productformated saved successfully");
        map.put("data", pfDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductformatedDto> updateProductformated(ProductformatedDto productformatedDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ProductformatedDto pfDtoUpdated = pfService.updateProductformated(productformatedDto);
        log.info("Productformated updated successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Productformated updated successfully");
        map.put("data", pfDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
