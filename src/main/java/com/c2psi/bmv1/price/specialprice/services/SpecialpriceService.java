package com.c2psi.bmv1.price.specialprice.services;

import com.c2psi.bmv1.dto.SpecialpriceDto;

public interface SpecialpriceService {
    SpecialpriceDto saveSpecialprice(SpecialpriceDto specialpriceDto);
    SpecialpriceDto updateSpecialprice(SpecialpriceDto specialpriceDto);
    Boolean deleteSpecialpriceById(Long id);
    SpecialpriceDto getSpecialpriceById(Long id);
    Boolean isSpecialpriceExistWith(Long id);
}
