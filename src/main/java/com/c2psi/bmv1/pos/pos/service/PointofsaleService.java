package com.c2psi.bmv1.pos.pos.service;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofPointofsaleDto;
import com.c2psi.bmv1.dto.PointofsaleDto;

import java.util.List;

public interface PointofsaleService {
    PointofsaleDto savePointofsale(PointofsaleDto pointofsaleDto);
    PointofsaleDto updatePointofsale(PointofsaleDto pointofsaleDto);
    Boolean deletePointofsaleById(Long id);
    PointofsaleDto getPointofsaleById(Long id);
    List<PointofsaleDto> getListofPointofsale(FilterRequest filterRequest);
    PageofPointofsaleDto getPageofPointofsale(FilterRequest filterRequest);

    Boolean isEnterpriseExistWith(Long id);
}
