package com.c2psi.bmv1.product.product.service;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.ProductDto;
import com.c2psi.bmv1.dto.PageofProductDto;

import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ProductDto formatDto);
    ProductDto updateProduct(ProductDto formatDto);
    Boolean deleteProductById(Long id);
    ProductDto getProductById(Long id);
    List<ProductDto> getListofProduct(FilterRequest filterRequest);
    PageofProductDto getPageofProduct(FilterRequest filterRequest);
    Boolean isProductExistWith(Long id);
}
