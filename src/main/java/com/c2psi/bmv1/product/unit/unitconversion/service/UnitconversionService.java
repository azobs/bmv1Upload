package com.c2psi.bmv1.product.unit.unitconversion.service;

import com.c2psi.bmv1.dto.*;

import java.util.List;

public interface UnitconversionService {
    UnitconversionDto saveUnitconversion(UnitconversionDto unitconversionDto);
    UnitconversionDto updateUnitconversion(UnitconversionDto unitconversionDto);
    Boolean deleteUnitconversionById(Long id);
    UnitconversionDto getUnitconversionById(Long id);
    Boolean isUnitconversionExistWith(Long id);
    List<UnitconversionDto> getListofUnitconversion(FilterRequest filterRequest);
    PageofUnitconversionDto getPageofUnitconversion(FilterRequest filterRequest);
}
