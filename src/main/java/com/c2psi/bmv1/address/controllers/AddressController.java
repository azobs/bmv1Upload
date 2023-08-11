package com.c2psi.bmv1.address.controllers;

import com.c2psi.bmv1.address.services.AddressService;
import com.c2psi.bmv1.api.AddressApi;
import com.c2psi.bmv1.dto.AddressDto;
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
public class AddressController implements AddressApi {
    final AddressService addressService;
    @Override
    public ResponseEntity saveAddress(AddressDto addressDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        AddressDto addressDtoSaved = addressService.saveAddress(addressDto);
        log.info("Entity Address saved successfully {} ", addressDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Address created successfully");
        map.put("data", addressDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }
}
