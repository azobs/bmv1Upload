package com.c2psi.bmv1.client.client.controllers;

import com.c2psi.bmv1.api.ClientApi;
import com.c2psi.bmv1.client.client.services.ClientService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.ClientDto;
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
public class ClientController implements ClientApi {
    final ClientService clientService;

    @Override
    public ResponseEntity<Boolean> deleteClientById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = clientService.deleteClientById(id);
        log.info("Client deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Client deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientDto> getClientById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        ClientDto clientDto = clientService.getClientById(id);
        log.info("Client found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Client found successfully");
        map.put("data", clientDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClientDto>> getClientList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<ClientDto> clientDtoList = clientService.getListofClient(filterRequest);
        log.info("Client list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Client list found successfully");
        map.put("data", clientDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofClientDto> getClientPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofClientDto pageofClientDto = clientService.getPageofClient(filterRequest);
        log.info("Client page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Client page found successfully");
        map.put("data", pageofClientDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientDto> saveClient(ClientDto clientDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ClientDto clientDtoSaved = clientService.saveClient(clientDto);
        log.info("Entity Client saved successfully {}", clientDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Client created successfully");
        map.put("data", clientDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClientDto> updateClient(ClientDto clientDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ClientDto clientDtoUpdated = clientService.updateClient(clientDto);
        log.info("Entity Client updated successfully {}", clientDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Client updated successfully");
        map.put("data", clientDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
