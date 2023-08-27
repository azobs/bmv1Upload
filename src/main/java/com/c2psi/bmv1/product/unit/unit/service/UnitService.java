package com.c2psi.bmv1.product.unit.unit.service;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofUnitDto;
import com.c2psi.bmv1.dto.UnitDto;

import java.util.List;

public interface UnitService {
    UnitDto saveUnit(UnitDto unitDto);
    UnitDto updateUnit(UnitDto unitDto);
    Boolean deleteUnitById(Long id);
    UnitDto getUnitById(Long id);
    Boolean isUnitExistWith(Long id);
    List<UnitDto> getListofUnit(FilterRequest filterRequest);
    PageofUnitDto getPageofUnit(FilterRequest filterRequest);
}
