package com.c2psi.bmv1.loading.loadingdetails.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.LoadingdetailsDto;
import com.c2psi.bmv1.dto.PageofLoadingdetailsDto;

import java.util.List;

public interface LoadingdetailsService {
    LoadingdetailsDto saveLoadingdetails(LoadingdetailsDto loadingDto);
    LoadingdetailsDto updateLoadingdetails(LoadingdetailsDto loadingDto);
    Boolean deleteLoadingdetailsById(Long id);
    LoadingdetailsDto getLoadingdetailsById(Long id);
    List<LoadingdetailsDto> getListofLoadingdetails(FilterRequest filterRequest);
    PageofLoadingdetailsDto getPageofLoadingdetails(FilterRequest filterRequest);
    Boolean isLoadingdetailsExistWith(Long id);
}
