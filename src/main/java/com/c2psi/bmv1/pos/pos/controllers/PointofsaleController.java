package com.c2psi.bmv1.pos.pos.controllers;

import com.c2psi.bmv1.api.PosApi;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofPointofsaleDto;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
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
public class PointofsaleController implements PosApi {
    final PointofsaleService posService;


    @Override
    public ResponseEntity<Boolean> deletePosById(Long id) {
        return PosApi.super.deletePosById(id);
    }

    @Override
    public ResponseEntity<PointofsaleDto> getPosById(Long id) {
        return PosApi.super.getPosById(id);
    }

    @Override
    public ResponseEntity<List<PointofsaleDto>> getPosList(FilterRequest filterRequest) {
        return PosApi.super.getPosList(filterRequest);
    }

    @Override
    public ResponseEntity<PageofPointofsaleDto> getPosPage(FilterRequest filterRequest) {
        return PosApi.super.getPosPage(filterRequest);
    }

    @Override
    public ResponseEntity<PointofsaleDto> savePos(PointofsaleDto pointofsaleDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        PointofsaleDto posDtoSaved = posService.savePointofsale(pointofsaleDto);
        log.info("Entity Pointofsale saved successfully {}", posDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Pointofsale created successfully");
        map.put("data", posDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PointofsaleDto> updatePos(PointofsaleDto pointofsaleDto) {
        return PosApi.super.updatePos(pointofsaleDto);
    }
}
