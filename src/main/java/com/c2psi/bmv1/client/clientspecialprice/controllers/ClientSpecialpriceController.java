package com.c2psi.bmv1.client.clientspecialprice.controllers;

import com.c2psi.bmv1.api.ClientspecialpriceApi;
import com.c2psi.bmv1.client.clientspecialprice.services.ClientSpecialpriceService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.ClientSpecialpriceDto;
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
public class ClientSpecialpriceController implements ClientspecialpriceApi {
    final ClientSpecialpriceService clientSpecialpriceService;

    @Override
    public ResponseEntity<Boolean> deleteClientSpecialpriceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = clientSpecialpriceService.deleteClientSpecialpriceById(id);
        log.info("ClientSpecialprice deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "ClientSpecialprice deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientSpecialpriceDto> getClientSpecialpriceById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        ClientSpecialpriceDto clientspecialpriceDto = clientSpecialpriceService.getClientSpecialpriceById(id);
        log.info("ClientSpecialprice found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "ClientSpecialprice found successfully");
        map.put("data", clientspecialpriceDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClientSpecialpriceDto>> getClientSpecialpriceList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<ClientSpecialpriceDto> clientspecialpriceDtoList = clientSpecialpriceService.getListofClientSpecialprice(filterRequest);
        log.info("ClientSpecialprice list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "ClientSpecialprice list found successfully");
        map.put("data", clientspecialpriceDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofClientSpecialpriceDto> getClientSpecialpricePage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofClientSpecialpriceDto pageofClientSpecialpriceDto = clientSpecialpriceService.getPageofClientSpecialprice(filterRequest);
        log.info("ClientSpecialprice page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "ClientSpecialprice page found successfully");
        map.put("data", pageofClientSpecialpriceDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientSpecialpriceDto> saveClientSpecialprice(ClientSpecialpriceDto clientSpecialpriceDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ClientSpecialpriceDto clientspecialpriceDtoSaved = clientSpecialpriceService.saveClientSpecialprice(clientSpecialpriceDto);
        log.info("Entity ClientSpecialprice saved successfully");

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "ClientSpecialprice created successfully");
        map.put("data", clientspecialpriceDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClientSpecialpriceDto> updateClientSpecialprice(ClientSpecialpriceDto clientSpecialpriceDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ClientSpecialpriceDto clientSpecialpriceDtoUpdated = clientSpecialpriceService.updateClientSpecialprice(clientSpecialpriceDto);
        log.info("Entity ClientSpecialprice updated successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "ClientSpecialprice updated successfully");
        map.put("data", clientSpecialpriceDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
