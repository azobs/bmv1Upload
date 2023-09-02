package com.c2psi.bmv1.inventory.controllers;

import com.c2psi.bmv1.api.InventoryApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.inventory.inventory.services.InventoryService;
import com.c2psi.bmv1.inventory.inventoryline.services.InventorylineService;
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
public class InventoryController implements InventoryApi {
    final InventoryService invService;
    final InventorylineService invlineService;

    @Override
    public ResponseEntity<Boolean> deleteInventoryById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = invService.deleteInventoryById(id);
        log.info("Inventory deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Inventory deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteInventorylineById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = invlineService.deleteInventorylineById(id);
        log.info("Inventory line deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Inventory line deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InventoryDto> getInventoryById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        InventoryDto invDto = invService.getInventoryById(id);
        log.info("Inventory found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Inventory found successfully");
        map.put("data", invDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<InventoryDto>> getInventoryList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<InventoryDto> invDtoList = invService.getListofInventory(filterRequest);
        log.info("Inventory list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Inventory list found successfully");
        map.put("data", invDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofInventoryDto> getInventoryPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofInventoryDto pageofInventoryDto = invService.getPageofInventory(filterRequest);
        log.info("Inventory page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Inventory page found successfully");
        map.put("data", pageofInventoryDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InventorylineDto> getInventorylineById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        InventorylineDto invlineDto = invlineService.getInventorylineById(id);
        log.info("Inventory line found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Inventory line found successfully");
        map.put("data", invlineDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<InventorylineDto>> getInventorylineList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<InventorylineDto> invlineDtoList = invlineService.getListofInventoryline(filterRequest);
        log.info("Inventory line list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Inventory line list found successfully");
        map.put("data", invlineDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofInventorylineDto> getInventorylinePage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofInventorylineDto pageofInventorylineDto = invlineService.getPageofInventoryline(filterRequest);
        log.info("Inventory line page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Inventory line page found successfully");
        map.put("data", pageofInventorylineDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InventoryDto> saveInventory(InventoryDto invDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        InventoryDto invDtoSaved = invService.saveInventory(invDto);
        log.info("Entity Inventory saved successfully {}", invDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Inventory created successfully");
        map.put("data", invDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<InventorylineDto> saveInventoryline(InventorylineDto invlineDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        InventorylineDto invlineDtoSaved = invlineService.saveInventoryline(invlineDto);
        log.info("Entity Inventoryline saved successfully {}", invlineDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Inventoryline created successfully");
        map.put("data", invlineDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<InventoryDto> updateInventory(InventoryDto invDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        InventoryDto invDtoUpdated = invService.updateInventory(invDto);
        log.info("Entity Inventory updated successfully {}", invDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Inventory updated successfully");
        map.put("data", invDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InventorylineDto> updateInventoryline(InventorylineDto invlineDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        InventorylineDto invlineDtoUpdated = invlineService.updateInventoryline(invlineDto);
        log.info("Entity Inventory line updated successfully {}", invlineDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Inventoryline updated successfully");
        map.put("data", invlineDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
