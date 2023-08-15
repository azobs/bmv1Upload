package com.c2psi.bmv1.pos.enterprise.services;

import com.c2psi.bmv1.dto.EnterpriseDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofEnterpriseDto;

import java.util.List;

public interface EnterpriseService {

    EnterpriseDto saveEnterprise(EnterpriseDto enterpriseDto);
    EnterpriseDto updateEnterprise(EnterpriseDto enterpriseDto);
    Boolean deleteEnterpriseById(Long id);
    EnterpriseDto getEnterpriseById(Long id);
    List<EnterpriseDto> getListofEnterprise(FilterRequest filterRequest);
    PageofEnterpriseDto getPageofEnterprise(FilterRequest filterRequest);
    Boolean isEnterpriseExistWith(Long id);

}
