package com.c2psi.bmv1.auth.permission.controllers;

import com.c2psi.bmv1.api.AuthApi;
import com.c2psi.bmv1.auth.permission.services.PermissionService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.PermissionDto;
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
public class PermissionController implements AuthApi {
    final PermissionService permissionService;
    @Override
    public ResponseEntity<Boolean> deletePermissionById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deletePermission = permissionService.deletePermissionById(id);
        log.info("Entity Permission deleted successfully {} ", deletePermission);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Permission deleted successfully");
        map.put("data", deletePermission);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PermissionDto> getPermissionById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        PermissionDto permissionDtoFound = permissionService.getPermissionById(id);
        log.info("Entity Permission found successfully {} ", permissionDtoFound);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Permission found successfully");
        map.put("data", permissionDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PermissionDto> getPermissionByName(String name) {
        Map<String, Object> map = new LinkedHashMap<>();
        PermissionDto permissionDtoFound = permissionService.getPermissionByName(name);
        log.info("Entity Permission found successfully {} ", permissionDtoFound);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Permission found successfully");
        map.put("data", permissionDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PermissionDto>> getPermissionList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<PermissionDto> permissionDtoList = permissionService.getListofPermission(filterRequest);
        log.info("Permission list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Permission list found successfully");
        map.put("data", permissionDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofPermissionDto> getPermissionPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofPermissionDto permissionDtoPage = permissionService.getPageofPermission(filterRequest);
        log.info("Permission Page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Permission page found successfully");
        map.put("data", permissionDtoPage);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PermissionDto> savePermission(PermissionDto permissionDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        PermissionDto permissionDtoSaved = permissionService.savePermission(permissionDto);
        log.info("Entity Permission saved successfully {} ", permissionDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Permission created successfully");
        map.put("data", permissionDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }
}
