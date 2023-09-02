package com.c2psi.bmv1.product.product.controllers;

import com.c2psi.bmv1.api.ProductApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.ProductDto;
import com.c2psi.bmv1.product.product.service.ProductService;
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
public class ProductController implements ProductApi {
    final ProductService productService;

    @Override
    public ResponseEntity<Boolean> deleteProductById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = productService.deleteProductById(id);
        log.info("Product deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Product deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        ProductDto productDtoFound = productService.getProductById(id);
        log.info("Product found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Product found successfully");
        map.put("data", productDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getProductList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<ProductDto> productDtoList = productService.getListofProduct(filterRequest);
        log.info("Product list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Product list found successfully");
        map.put("data", productDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofProductDto> getProductPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofProductDto pageofProductDtoFound = productService.getPageofProduct(filterRequest);
        log.info("Product page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Product page found successfully");
        map.put("data", pageofProductDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> saveProduct(ProductDto productDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ProductDto productDtoSaved = productService.saveProduct(productDto);
        log.info("Product saved successfully");

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Product saved successfully");
        map.put("data", productDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(ProductDto productDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ProductDto productDtoUpdated = productService.updateProduct(productDto);
        log.info("Product updated successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Product updated successfully");
        map.put("data", productDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
