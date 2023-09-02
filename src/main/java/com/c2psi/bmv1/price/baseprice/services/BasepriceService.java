package com.c2psi.bmv1.price.baseprice.services;

import com.c2psi.bmv1.dto.BasepriceDto;

public interface BasepriceService {
    BasepriceDto saveBaseprice(BasepriceDto basepriceDto);
    BasepriceDto updateBaseprice(BasepriceDto basepriceDto);
    Boolean deleteBasepriceById(Long id);
    BasepriceDto getBasepriceById(Long id);
    Boolean isBasepriceExistWith(Long id);
}
