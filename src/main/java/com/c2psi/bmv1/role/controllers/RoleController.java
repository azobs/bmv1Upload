package com.c2psi.bmv1.role.controllers;

import com.c2psi.bmv1.api.RoleApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.role.services.RoleService;
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
public class RoleController implements RoleApi {
    final RoleService roleService;


    @Override
    public ResponseEntity<Boolean> deleteRoleById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleteRole = roleService.deleteRoleById(id);
        log.info("Entity Role deleted successfully {} ", deleteRole);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Role deleted successfully");
        map.put("data", deleteRole);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RoleDto> getRoleById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        RoleDto roleDtoFound = roleService.getRoleById(id);
        log.info("Entity Role found successfully {} ", roleDtoFound);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Role found successfully");
        map.put("data", roleDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RoleDto>> getRoleList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<RoleDto> roleDtoList = roleService.getListofRole(filterRequest);
        log.info("Role list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Role list found successfully");
        map.put("data", roleDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofRoleDto> getRolePage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofRoleDto roleDtoPage = roleService.getPageofRole(filterRequest);
        log.info("Role Page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Role page found successfully");
        map.put("data", roleDtoPage);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RoleDto> saveRole(RoleDto roleDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        log.info("Role dto = {} ", roleDto);
        RoleDto roleDtoSaved = roleService.saveRole(roleDto);
        log.info("Entity Role saved successfully {} ", roleDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Role created successfully");
        map.put("data", roleDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RoleDto> updateRole(RoleDto roleDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        RoleDto roleDtoUpdated = roleService.updateRole(roleDto);
        log.info("Entity Role updated successfully {} ", roleDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Role updated successfully");
        map.put("data", roleDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
