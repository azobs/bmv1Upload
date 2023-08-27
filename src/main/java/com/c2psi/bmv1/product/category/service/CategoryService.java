package com.c2psi.bmv1.product.category.service;

import com.c2psi.bmv1.dto.*;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto);
    Boolean deleteCategoryById(Long id);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getListofCategory(FilterRequest filterRequest);
    PageofCategoryDto getPageofCategory(FilterRequest filterRequest);
    Boolean isCategoryExistWith(Long id);
}
