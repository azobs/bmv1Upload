package com.c2psi.bmv1.sale.delivery.controllers;

import com.c2psi.bmv1.api.DeliveryApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.sale.delivery.delivery.services.DeliveryService;
import com.c2psi.bmv1.sale.delivery.deliverydetails.services.DeliverydetailsService;
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
public class DeliveryController implements DeliveryApi {
    final DeliveryService deliveryService;
    final DeliverydetailsService deliverydetailsService;

    @Override
    public ResponseEntity<Boolean> deleteDeliveryById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = deliveryService.deleteDeliveryById(id);
        log.info("Delivery deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Delivery deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteDeliverydetailsById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = deliverydetailsService.deleteDeliverydetailsById(id);
        log.info("Delivery details deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Delivery details deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeliveryDto> getDeliveryById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        DeliveryDto deliveryDto = deliveryService.getDeliveryById(id);
        log.info("Delivery found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Delivery found successfully");
        map.put("data", deliveryDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DeliveryDto>> getDeliveryList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<DeliveryDto> deliveryDtoList = deliveryService.getListofDelivery(filterRequest);
        log.info("Delivery list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Delivery list found successfully");
        map.put("data", deliveryDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofDeliveryDto> getDeliveryPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofDeliveryDto pageofDeliveryDto = deliveryService.getPageofDelivery(filterRequest);
        log.info("Delivery page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Delivery page found successfully");
        map.put("data", pageofDeliveryDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeliverydetailsDto> getDeliverydetailsById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        DeliverydetailsDto deliverydetailsDto = deliverydetailsService.getDeliverydetailsById(id);
        log.info("Delivery details found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Delivery details found successfully");
        map.put("data", deliverydetailsDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DeliverydetailsDto>> getDeliverydetailsList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<DeliverydetailsDto> deliverydetailsDtoList = deliverydetailsService.getListofDeliverydetails(filterRequest);
        log.info("Deliverydetails list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Deliverydetails list found successfully");
        map.put("data", deliverydetailsDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofDeliverydetailsDto> getDeliverydetailsPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofDeliverydetailsDto pageofDeliverydetailsDto = deliverydetailsService.getPageofDeliverydetails(filterRequest);
        log.info("Deliverydetails page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Deliverydetails page found successfully");
        map.put("data", pageofDeliverydetailsDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeliveryDto> saveDelivery(DeliveryDto deliveryDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        DeliveryDto deliveryDtoSaved = deliveryService.saveDelivery(deliveryDto);
        log.info("Entity Delivery saved successfully {}", deliveryDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Delivery created successfully");
        map.put("data", deliveryDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DeliverydetailsDto> saveDeliverydetails(DeliverydetailsDto deliverydetailsDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        DeliverydetailsDto deliverydetailsDtoSaved = deliverydetailsService.saveDeliverydetails(deliverydetailsDto);
        log.info("Entity Deliverydetails saved successfully {}", deliverydetailsDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Deliverydetails created successfully");
        map.put("data", deliverydetailsDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DeliveryDto> updateDelivery(DeliveryDto deliveryDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        DeliveryDto deliveryDtoUpdated = deliveryService.updateDelivery(deliveryDto);
        log.info("Entity Delivery updated successfully {}", deliveryDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Delivery updated successfully");
        map.put("data", deliveryDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DeliverydetailsDto> updateDeliverydetails(DeliverydetailsDto deliverydetailsDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        DeliverydetailsDto deliverydetailsDtoUpdated = deliverydetailsService.updateDeliverydetails(deliverydetailsDto);
        log.info("Entity Deliverydetails updated successfully {}", deliverydetailsDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Deliverydetails updated successfully");
        map.put("data", deliverydetailsDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
