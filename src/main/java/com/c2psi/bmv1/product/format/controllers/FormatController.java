package com.c2psi.bmv1.product.format.controllers;

import com.c2psi.bmv1.api.FormatApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.FormatDto;
import com.c2psi.bmv1.product.format.service.FormatService;
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
public class FormatController implements FormatApi {
    final FormatService formatService;

    @Override
    public ResponseEntity<Boolean> deleteFormatById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = formatService.deleteFormatById(id);
        log.info("Format deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Format deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FormatDto> getFormatById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        FormatDto formatDtoFound = formatService.getFormatById(id);
        log.info("Format found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Format found successfully");
        map.put("data", formatDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FormatDto>> getFormatList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<FormatDto> formatDtoList = formatService.getListofFormat(filterRequest);
        log.info("Format list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Format list found successfully");
        map.put("data", formatDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofFormatDto> getFormatPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofFormatDto pageofFormatDtoFound = formatService.getPageofFormat(filterRequest);
        log.info("Format page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Format page found successfully");
        map.put("data", pageofFormatDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FormatDto> saveFormat(FormatDto formatDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        FormatDto formatDtoSaved = formatService.saveFormat(formatDto);
        log.info("Format saved successfully");

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Format saved successfully");
        map.put("data", formatDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<FormatDto> updateFormat(FormatDto formatDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        FormatDto formatDtoUpdated = formatService.updateFormat(formatDto);
        log.info("Format updated successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Format updated successfully");
        map.put("data", formatDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
