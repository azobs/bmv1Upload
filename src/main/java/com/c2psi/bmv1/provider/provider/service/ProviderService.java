package com.c2psi.bmv1.provider.provider.service;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofProviderDto;
import com.c2psi.bmv1.dto.ProviderDto;

import java.util.List;

public interface ProviderService {
    ProviderDto saveProvider(ProviderDto providerDto);
    ProviderDto updateProvider(ProviderDto providerDto);
    Boolean deleteProviderById(Long id);
    ProviderDto getProviderById(Long id);
    List<ProviderDto> getListofProvider(FilterRequest filterRequest);
    PageofProviderDto getPageofProvider(FilterRequest filterRequest);
    Boolean isProviderExistWith(Long id);
}
