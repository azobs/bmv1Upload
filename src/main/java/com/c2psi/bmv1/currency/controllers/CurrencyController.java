package com.c2psi.bmv1.currency.controllers;

import com.c2psi.bmv1.api.CurrencyApi;
import com.c2psi.bmv1.currency.services.CurrencyService;
import com.c2psi.bmv1.dto.*;
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
public class CurrencyController implements CurrencyApi {
    final CurrencyService currencyService;

    @Override
    public ResponseEntity<Boolean> deleteCurrencyById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = currencyService.deleteCurrencyById(id);
        log.info("Currency deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Currency deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CurrencyDto> getCurrencyById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        CurrencyDto currencyDto = currencyService.getCurrencyById(id);
        log.info("Currency found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Currency found successfully");
        map.put("data", currencyDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CurrencyDto>> getCurrencyList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<CurrencyDto> currencyDtoList = currencyService.getListofCurrency(filterRequest);
        log.info("Currency list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Currency list found successfully");
        map.put("data", currencyDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofCurrencyDto> getCurrencyPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofCurrencyDto pageofCurrencyDto = currencyService.getPageofCurrency(filterRequest);
        log.info("Currency page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Currency page found successfully");
        map.put("data", pageofCurrencyDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CurrencyDto> saveCurrency(CurrencyDto currencyDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        CurrencyDto currencyDtoSaved = currencyService.saveCurrency(currencyDto);
        log.info("Entity Currency saved successfully");

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Currency created successfully");
        map.put("data", currencyDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CurrencyDto> updateCurrency(CurrencyDto currencyDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        CurrencyDto currencyDtoUpdated = currencyService.updateCurrency(currencyDto);
        log.info("Entity Currency updated successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Currency updated successfully");
        map.put("data", currencyDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
