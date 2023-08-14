package com.c2psi.bmv1.userbm.controllers;

import com.c2psi.bmv1.api.UserbmApi;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofUserbmDto;
import com.c2psi.bmv1.dto.UserbmDto;
import com.c2psi.bmv1.userbm.services.UserbmService;
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
public class UserbmController implements UserbmApi {
    final UserbmService userbmService;

    @Override
    public ResponseEntity<UserbmDto> saveUserbm(UserbmDto userbmDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        UserbmDto userbmDtoSaved = userbmService.saveUserbm(userbmDto);
        log.info("Entity Userbm saved successfully {} ", userbmDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Userbm created successfully");
        map.put("data", userbmDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<UserbmDto>> getUserbmList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<UserbmDto> userbmDtoList = userbmService.getListofUserbm(filterRequest);
        log.info("Userbm list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Userbm list found successfully");
        map.put("data", userbmDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofUserbmDto> getUserbmPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofUserbmDto userbmDtoPage = userbmService.getPageofUserbm(filterRequest);
        log.info("Userbm Page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Userbm page found successfully");
        map.put("data", userbmDtoPage);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteUserbmById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleteUserbm = userbmService.deleteUserbmById(id);
        log.info("Entity Userbm deleted successfully {} ", deleteUserbm);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Userbm deleted successfully");
        map.put("data", deleteUserbm);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserbmDto> getUserbmByCni(String cni) {
        Map<String, Object> map = new LinkedHashMap<>();
        UserbmDto userbmDtoFound = userbmService.getUserbmByCni(cni);
        log.info("Entity Userbm found successfully {} ", userbmDtoFound);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Userbm found successfully");
        map.put("data", userbmDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserbmDto> getUserbmByEmail(String email) {
        Map<String, Object> map = new LinkedHashMap<>();
        UserbmDto userbmDtoFound = userbmService.getUserbmByEmail(email);
        log.info("Entity Userbm found successfully {} ", userbmDtoFound);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Userbm found successfully");
        map.put("data", userbmDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserbmDto> getUserbmById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        UserbmDto userbmDtoFound = userbmService.getUserbmById(id);
        log.info("Entity Userbm found successfully {} ", userbmDtoFound);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Userbm found successfully");
        map.put("data", userbmDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserbmDto> getUserbmByLogin(String login) {
        Map<String, Object> map = new LinkedHashMap<>();
        UserbmDto userbmDtoFound = userbmService.getUserbmByLogin(login);
        log.info("Entity Userbm found successfully {} ", userbmDtoFound);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Userbm found successfully");
        map.put("data", userbmDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserbmDto> updateUserbm(UserbmDto userbmDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        UserbmDto userbmDtoUpdated = userbmService.updateUserbm(userbmDto);
        log.info("Entity Userbm updated successfully {} ", userbmDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Userbm updated successfully");
        map.put("data", userbmDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
