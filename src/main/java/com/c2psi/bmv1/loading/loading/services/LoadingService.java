package com.c2psi.bmv1.loading.loading.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.LoadingDto;
import com.c2psi.bmv1.dto.PageofLoadingDto;

import java.util.List;

public interface LoadingService {
    LoadingDto saveLoading(LoadingDto loadingDto);
    LoadingDto updateLoading(LoadingDto loadingDto);
    Boolean deleteLoadingById(Long id);
    LoadingDto getLoadingById(Long id);
    List<LoadingDto> getListofLoading(FilterRequest filterRequest);
    PageofLoadingDto getPageofLoading(FilterRequest filterRequest);
    Boolean isLoadingExistWith(Long id);
    Boolean openLoading(Long id);
    Boolean closeLoading(Long id);
}
