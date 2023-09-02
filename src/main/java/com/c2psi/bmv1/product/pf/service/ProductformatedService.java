package com.c2psi.bmv1.product.pf.service;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.ProductformatedDto;
import com.c2psi.bmv1.dto.PageofProductformatedDto;

import java.util.List;

public interface ProductformatedService {
    ProductformatedDto saveProductformated(ProductformatedDto formatDto);
    ProductformatedDto updateProductformated(ProductformatedDto formatDto);
    Boolean deleteProductformatedById(Long id);
    ProductformatedDto getProductformatedById(Long id);
    List<ProductformatedDto> getListofProductformated(FilterRequest filterRequest);
    PageofProductformatedDto getPageofProductformated(FilterRequest filterRequest);
    Boolean isProductformatedExistWith(Long id);
}
