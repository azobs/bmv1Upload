package com.c2psi.bmv1.product.category.controllers;

import com.c2psi.bmv1.api.CategoryApi;
import com.c2psi.bmv1.dto.CategoryDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofCategoryDto;
import com.c2psi.bmv1.product.category.service.CategoryService;
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
public class CategoryController implements CategoryApi {
    final CategoryService categoryService;

    @Override
    public ResponseEntity<Boolean> deleteCategoryById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = categoryService.deleteCategoryById(id);
        log.info("Category deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Category deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDto> getCategoryById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        CategoryDto categoryDtoFound = categoryService.getCategoryById(id);
        log.info("Category found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Category found successfully");
        map.put("data", categoryDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getCategoryList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<CategoryDto> categoryDtoList = categoryService.getListofCategory(filterRequest);
        log.info("Category list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Category list found successfully");
        map.put("data", categoryDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofCategoryDto> getCategoryPage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofCategoryDto pageofCategoryDtoFound = categoryService.getPageofCategory(filterRequest);
        log.info("Category page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Category page found successfully");
        map.put("data", pageofCategoryDtoFound);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDto> saveCategory(CategoryDto categoryDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        CategoryDto categoryDtoSaved = categoryService.saveCategory(categoryDto);
        log.info("Category saved successfully");

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Category saved successfully");
        map.put("data", categoryDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryDto> updateCategory(CategoryDto categoryDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        CategoryDto categoryDtoUpdated = categoryService.updateCategory(categoryDto);
        log.info("Category updated successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Category updated successfully");
        map.put("data", categoryDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
