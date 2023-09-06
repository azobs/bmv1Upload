package com.c2psi.bmv1.sale.backin.backindetails.services;

import com.c2psi.bmv1.dto.BackindetailsDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofBackindetailsDto;
import com.c2psi.bmv1.sale.backin.backindetails.models.Backindetails;

import java.util.List;

public interface BackindetailsService {
    BackindetailsDto saveBackindetails(BackindetailsDto backindetailsDto);
    BackindetailsDto updateBackindetails(BackindetailsDto backindetailsDto);
    Boolean deleteBackindetailsById(Long id);
    BackindetailsDto getBackindetailsById(Long id);
    List<BackindetailsDto> getListofBackindetails(FilterRequest filterRequest);
    PageofBackindetailsDto getPageofBackindetails(FilterRequest filterRequest);
    Boolean isBackindetailsExistWith(Long id);
}
