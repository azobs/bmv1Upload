package com.c2psi.bmv1.userbm.controllers;

import com.c2psi.bmv1.api.UserbmApi;
import com.c2psi.bmv1.dto.UserbmDto;
import com.c2psi.bmv1.userbm.service.UserbmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
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
}
