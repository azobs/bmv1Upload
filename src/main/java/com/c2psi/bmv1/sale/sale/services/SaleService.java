package com.c2psi.bmv1.sale.sale.services;

import com.c2psi.bmv1.dto.SaleDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofSaleDto;

import java.util.List;

public interface SaleService {
    SaleDto saveSale(SaleDto saleDto);
    SaleDto updateSale(SaleDto saleDto);
    Boolean deleteSaleById(Long id);
    SaleDto getSaleById(Long id);
    List<SaleDto> getListofSale(FilterRequest filterRequest);
    PageofSaleDto getPageofSale(FilterRequest filterRequest);
    Boolean isSaleExistWith(Long id);
}
