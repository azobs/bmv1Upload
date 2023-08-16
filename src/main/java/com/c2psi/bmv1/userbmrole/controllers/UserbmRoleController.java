package com.c2psi.bmv1.userbmrole.controllers;

import com.c2psi.bmv1.api.UserbmRoleApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.userbmrole.services.UserbmRoleService;
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
public class UserbmRoleController implements UserbmRoleApi {
    final UserbmRoleService userbmRoleService;

    @Override
    public ResponseEntity<Boolean> deleteUserbmRoleById(Long id) {
        return UserbmRoleApi.super.deleteUserbmRoleById(id);
    }

    @Override
    public ResponseEntity<UserbmRoleDto> getUserbmRoleById(Long id) {
        return UserbmRoleApi.super.getUserbmRoleById(id);
    }

    @Override
    public ResponseEntity<List<UserbmRoleDto>> getUserbmRoleList(FilterRequest filterRequest) {
        return UserbmRoleApi.super.getUserbmRoleList(filterRequest);
    }

    @Override
    public ResponseEntity<PageofUserbmRoleDto> getUserbmRolePage(FilterRequest filterRequest) {
        return UserbmRoleApi.super.getUserbmRolePage(filterRequest);
    }

    @Override
    public ResponseEntity<UserbmRoleDto> saveUserbmRole(UserbmRoleDto userbmRoleDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        log.info("UserbmRole dto = {} ", userbmRoleDto);
        UserbmRoleDto userbmroleDtoSaved = userbmRoleService.saveUserbmRole(userbmRoleDto);
        log.info("Entity UserbmRole saved successfully {} ", userbmroleDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "UserbmRole created successfully");
        map.put("data", userbmroleDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

}
