package com.c2psi.bmv1.sale.backin.backin.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofBackinDto;
import com.c2psi.bmv1.dto.BackinDto;

import java.util.List;

public interface BackinService {
    BackinDto saveBackin(BackinDto backinDto);
    BackinDto updateBackin(BackinDto backinDto);
    Boolean deleteBackinById(Long id);
    BackinDto getBackinById(Long id);
    List<BackinDto> getListofBackin(FilterRequest filterRequest);
    PageofBackinDto getPageofBackin(FilterRequest filterRequest);
    Boolean isBackinExistWith(Long id);
}
