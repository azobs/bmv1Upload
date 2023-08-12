package com.c2psi.bmv1.pos.enterprise.controllers;

import com.c2psi.bmv1.api.EnterpriseApi;
import com.c2psi.bmv1.dto.EnterpriseDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofEnterpriseDto;
import com.c2psi.bmv1.dto.UserbmDto;
import com.c2psi.bmv1.pos.enterprise.services.EnterpriseService;
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
public class EnterpriseController implements EnterpriseApi {
    final EnterpriseService enterpriseService;

    @Override
    public ResponseEntity<EnterpriseDto> saveEnterprise(EnterpriseDto enterpriseDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        EnterpriseDto enterpriseDtoSaved = enterpriseService.saveEnterprise(enterpriseDto);
        log.info("Entity Enterprise saved successfully {}", enterpriseDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Enterprise created successfully");
        map.put("data", enterpriseDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<EnterpriseDto> updateEnterprise(EnterpriseDto enterpriseDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        EnterpriseDto enterpriseDtoUpdated = enterpriseService.updateEnterprise(enterpriseDto);
        log.info("Entity Enterprise updated successfully {}", enterpriseDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Enterprise Updated successfully");
        map.put("data", enterpriseDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EnterpriseDto>> getEnterpriseList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<EnterpriseDto> enterpriseDtoList = enterpriseService.getListofEnterprise(filterRequest);
        log.info("Enterprise list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Enterprise list found successfully");
        map.put("data", enterpriseDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteEnterpriseById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = enterpriseService.deleteEnterpriseById(id);
        log.info("Enterprise deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Enterprise deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EnterpriseDto> getEnterpriseById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        EnterpriseDto enterpriseDto = enterpriseService.getEnterpriseById(id);
        log.info("Enterprise found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Enterprise found successfully");
        map.put("data", enterpriseDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofEnterpriseDto> getEnterprisePage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofEnterpriseDto enterpriseDtoPage = enterpriseService.getPageofEnterprise(filterRequest);
        log.info("Enterprise page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Enterprise page found successfully");
        map.put("data", enterpriseDtoPage);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
